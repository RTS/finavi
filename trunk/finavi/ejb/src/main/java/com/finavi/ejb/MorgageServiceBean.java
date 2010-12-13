package com.finavi.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.finavi.ejb.eao.ScoringEAO;
import com.finavi.ejb.thirdparty.Finance;
import com.finavi.ejb.thirdparty.MathEvaluator;
import com.finavi.ejb.thirdparty.ZeroFunction;
import com.finavi.model.Bank;
import com.finavi.model.LivingWages;
import com.finavi.model.Loan;
import com.finavi.model.LoanConditions;
import com.finavi.model.Scoring;
import com.finavi.model.ScoringRequest;
import com.finavi.model.User;

@Local(value = MorgageServiceLocal.class)
@Remote(value = MorgageServiceRemote.class)
@Stateless
public class MorgageServiceBean implements MorgageServiceLocal {
	@PersistenceContext
	private EntityManager em;

	@EJB
	private UserServiceLocal userService;

	@EJB
	private BankServiceLocal bankService;

	private ScoringEAO eao;

	private List<Bank> banks;

	@Override
	public List<Scoring> calculateScorings(ScoringRequest request) {
		clearPreviousScorings(request.getApplicant());
		this.banks = bankService.getAll();
		List<Scoring> scorings = new ArrayList<Scoring>();
		for (Bank bank : banks) {
			Scoring scoring = getScoring(bank, request);
			scorings.add(scoring);
		}
		User u = request.getApplicant();
		Set<Scoring> set = new HashSet<Scoring>();
		set.addAll(scorings);
		u.setScorings(set);
		em.merge(u);
		return scorings;
		
	}

	private void clearPreviousScorings(User u) {
		Query q = em.createQuery("delete from Scoring  where applicant = :user");
		q.setParameter("user", u);
		q.executeUpdate();
		em.flush();
	}

	private Scoring getScoring(Bank bank, ScoringRequest request) {
		Scoring scoring = new Scoring();
		Loan loan = bank.getLoan();
		scoring.setBank(bank);
		scoring.setApplicant(request.getApplicant());
		if (loan.getMaxAmount() < request.getLoanAmount()) {
			scoring.setDenialReason("Žiadaná suma je vyššia ako je maximálny možný úver: "
					+ loan.getMaxAmount() + "€.");
			return scoring;
		}
		if (loan.getMinAmount() >request.getLoanAmount()) {
			scoring.setDenialReason("Žiadaná suma je nižšia ako je minimálny možný úver: "
					+ loan.getMinAmount() + "€.");
			return scoring;
		}
		if (loan.getMaxYears() < request.getRepaymentPeriod()) {
			scoring.setDenialReason("Žiadaná doba prekračuje maximálnu hranicu: "
					+ loan.getMaxYears() + " rokov.");
			return scoring;
		}
		if (loan.getMinYears() > request.getRepaymentPeriod()) {
			scoring.setDenialReason("Žiadana doba je nižšia ako minimálna hranica: "
					+ loan.getMinYears() + " rokov.");
			return scoring;
		}
		Set<LoanConditions> conditions = loan.getConditions();
		boolean matchingFixation = false;
		LoanConditions matchedConditions = null;
		for (LoanConditions c : conditions) {
			if (c.getFixation() == request.getFixation()) {
				matchingFixation = true;
				matchedConditions = c;
			}
		}
		if (!matchingFixation) {
			scoring.setDenialReason("Banka neposkytuje fixáciu na požadovanú dobu: "
					+ request.getFixation() + " rokov.");
			return scoring;
		} else {
			double totalIncome = request.isCoApplicant() ? request
					.getIncomeOfApplicant() + request.getIncomeOfCoApplicant()
					: request.getIncomeOfApplicant();
			double bilance = calculateBilance(request);
			if (bilance <= 0) {
				scoring.setDenialReason("Zadané príjmy sú nižšie ako výdaje. Ǔver nie je možné poskytnúť.");
				return scoring;
			}
			String livingWagesExpression = getMatchingLivingWages(loan, request);
			MathEvaluator m = new MathEvaluator(livingWagesExpression);
			m.addVariable("x", totalIncome);
			double minLivingWage = 0d;
			try {
				minLivingWage = m.getValue();
			} catch (Exception e) {
				scoring.setDenialReason("Nepodarilo sa vyhodnotiť životné minimum.");
				return scoring;
			}
			if (minLivingWage > bilance) {
				scoring.setDenialReason("Vaša finančná bilancia je nižšia ako vypočítané životné minimum");
				return scoring;
			}
			double maxPossibleMonthlyPayment = bilance - minLivingWage;
			double monthlyPayment = calculateMontlyPayment(loan, request,
					matchedConditions);
			if (monthlyPayment > maxPossibleMonthlyPayment) {
				scoring.setDenialReason("Vypočítaná mesačná splátka "
						+ monthlyPayment
						+ "€ je vyššia ako vaša maximálna splátka "
						+ maxPossibleMonthlyPayment + "€.");
				return scoring;
			}
			scoring.setApproved(true);
			scoring.setMonthlyPayment(monthlyPayment);
			scoring.setRpmn(00);
			scoring.setInterestRate(matchedConditions.getInterestRateNoClient());
			scoring.setAccountFee(loan.getAccountManagementFee());
			if(loan.getLoanFee().isBasedOnLoanAmount()){
				scoring.setLoanProcessCharge(loan.getLoanFee().getFee()*request.getLoanAmount());
			}else {
				scoring.setLoanProcessCharge(loan.getLoanFee().getFee());
			}
			
		}
		return scoring;
	}

	private double calculateMontlyPayment(Loan loan, ScoringRequest request,
			LoanConditions conditions) {
		double interestRate = 0d;
		if (request.getApplicant().getBank() != null
				&& request.getApplicant().getBank().getName()
						.equals(loan.getBank().getName())) {
			interestRate = conditions.getInterestRateWhenClient();
		} else {
			interestRate = conditions.getInterestRateNoClient();
		}
		double exp = 12 * request.getRepaymentPeriod();
		interestRate = interestRate / 1200;
		double monthlyPayment = ((interestRate) + (interestRate / (Math.pow(
				1 + interestRate, exp) - 1))) * request.getLoanAmount();
		return monthlyPayment;
	}

	private double calculateBilance(ScoringRequest request) {
		double bilance = 0d;
		if (request.isCoApplicant()) {
			bilance = request.getIncomeOfApplicant()
					+ request.getIncomeOfCoApplicant()
					- request.getExpensesOfApplicant()
					- request.getExpensesOfCoApplicant();
		} else {
			bilance = request.getIncomeOfApplicant()
					- request.getExpensesOfApplicant();
		}
		return bilance;
	}

	private String getMatchingLivingWages(Loan loan, ScoringRequest request) {
		LivingWages wages = loan.getLivingWagesRates();
		if (request.getNumberOfAdultPersons() == 1) {
			switch (request.getNumberOfChildren()) {
			case 0:
				return wages.getSingleExpr();
			case 1:
				return wages.getSingleWithOneChildExpr();
			default:
				return null;
			}
		}
		if (request.getNumberOfAdultPersons() == 2) {
			switch (request.getNumberOfChildren()) {
			case 0:
				return wages.getCoupleExpr();
			case 1:
				return wages.getCoupleWithOneChildExpr();
			case 2:
				return wages.getCoupleWithTwoChildExpr();
			default:
				return null;
			}
		}
		return null;
	}

	private ScoringEAO getEAO() {
		if (this.eao == null) {
			this.eao = new ScoringEAO(this.em);
		}
		return eao;
	}

	@Override
	public List<Scoring> getActualScoringsOfUser(User u) {
		List<Scoring> list = new ArrayList<Scoring>();
		Query q = em.createQuery("select u.scorings from User u where u = :user");
		q.setParameter("user", u);
		list = (List<Scoring>) q.getResultList();
		return list;
	}

	private static double FINANCIAL_PRECISION = 0.0000001d;
	private static double FINANCIAL_MAX_ITERATIONS = 100;

	public static double Rate(double npr, double pmt, double Pv, double Fv,
			double guess, double error) {
		double rate = 0.01;

		if (guess > 0)
			rate = guess / 100;
		double a = 0.0;
		if (pmt == 0) {
			return (Math.pow(Fv / Pv, 1 / npr) - 1) * 100;
		} else {
			while (1 == 1) {
				a = Pv * rate * Math.pow((1 + rate), npr)
						/ (Math.pow(1 + rate, npr) - 1);
				System.out.println(a);
				if (Math.abs(a - pmt) < error) {
					return rate * 100;
				} else if (Math.abs(a) > pmt) {
					rate -= 0.00001;
				} else {
					rate += 0.00001;
				}
			}
		}

	}// function RATE()

	public static void main(String[] args) {
		System.out.println(MorgageServiceBean.Rate(12,1000,10000,12000,0,0.00001));
		System.out.println(Finance.rate(12, 1000, 10000, 0, 0));
	}

}
