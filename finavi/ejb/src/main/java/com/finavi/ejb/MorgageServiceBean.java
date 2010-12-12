package com.finavi.ejb;

import java.util.ArrayList;
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
import com.finavi.ejb.thirdparty.MathEvaluator;
import com.finavi.model.Bank;
import com.finavi.model.LivingWages;
import com.finavi.model.Loan;
import com.finavi.model.LoanConditions;
import com.finavi.model.Scoring;
import com.finavi.model.ScoringRequest;
import com.finavi.model.User;

@Local(value=MorgageServiceLocal.class)
@Remote(value=MorgageServiceRemote.class)
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
	public List<Scoring> calculateScorings(ScoringRequest request){
		this.banks = bankService.getAll();
		String denialReason;
		for (Bank bank:banks){
			Scoring scoring = getScoring(bank, request);
		}
		return null;
	}
	
	private Scoring getScoring(Bank bank, ScoringRequest request) {
		Scoring scoring = new Scoring();
		Loan loan = bank.getLoan();
		scoring.setBank(bank);
		//scoring.setClient(request.getApplicant());
		if(loan.getMaxAmount()>request.getLoanAmount()){
			scoring.setDenialReason("Žiadaná suma je vyššia ako je maximálny možný úver: "+loan.getMaxAmount()+"€.");
			return scoring;
		}
		if (loan.getMinAmount()<request.getLoanAmount()){
			scoring.setDenialReason("Žiadaná suma je nižšia ako je minimálny možný úver: "+loan.getMinAmount()+"€.");
			return scoring;
		}
		if (loan.getMaxYears()<request.getRepaymentPeriod()){
			scoring.setDenialReason("Žiadaná doba prekračuje maximálnu hranicu: "+loan.getMaxYears()+" rokov.");
			return scoring;
		}
		if (loan.getMinYears()>request.getRepaymentPeriod()){
			scoring.setDenialReason("Žiadana doba je nižšia ako minimálna hranica: "+loan.getMinYears()+" rokov.");
			return scoring;
		}
		Set<LoanConditions> conditions = loan.getConditions();
		boolean matchingFixation = false;
		LoanConditions matchedConditions=null;
		for (LoanConditions c: conditions){
			if (c.getFixation()==request.getFixation()){
				matchingFixation = true;
				matchedConditions = c;
			}
		}
		if (!matchingFixation){
			scoring.setDenialReason("Banka neposkytuje fixáciu na požadovanú dobu: "+request.getFixation()+" rokov.");
			return scoring;
		}else{
			double totalIncome=request.isCoApplicant()?request.getIncomeOfApplicant()+request.getIncomeOfCoApplicant():request.getIncomeOfApplicant();
			double bilance = calculateBilance(request);
			if (bilance<=0){
				scoring.setDenialReason("Zadané príjmy sú nižšie ako výdaje. Ǔver nie je možné poskytnúť.");
				return scoring;
			}
			String livingWagesExpression =getMatchingLivingWages(loan, request);
			MathEvaluator m = new MathEvaluator(livingWagesExpression);
			m.addVariable("x", totalIncome);
			double minLivingWage = 0d;
			try {
				minLivingWage=m.getValue();
			}catch (Exception e){
				scoring.setDenialReason("Nepodarilo sa vyhodnotiť životné minimum.");
				return scoring;
			}
			if (minLivingWage>bilance){
				scoring.setDenialReason("Vaša finančná bilancia je nižšia ako vypočítané životné minimum");
				return scoring;
			}
			double maxPossibleMonthlyPayment=bilance-minLivingWage;
			double monthlyPayment = calculateMontlyPayment(loan, request,matchedConditions);
			if (monthlyPayment>maxPossibleMonthlyPayment){
				scoring.setDenialReason("Vypočítná mesačná splátka " +monthlyPayment+"€ je vyššia ako vaša maximálna splátka "+maxPossibleMonthlyPayment+"€.");
				return scoring;
			}
			
		}
		return scoring;
	}

	private double calculateMontlyPayment(Loan loan, ScoringRequest request, LoanConditions conditions) {
		double interestRate = 0d;
		if (request.getApplicant().getBank()!=null&& request.getApplicant().getBank().getName().equals(loan.getBank().getName())){
			interestRate= conditions.getInterestRateWhenClient();
		}else {
			interestRate = conditions.getInterestRateNoClient();
		}
		double exp = 12*request.getRepaymentPeriod();
		interestRate = interestRate/12;
		double monthlyPayment = ((interestRate)+(interestRate/(Math.pow(1+interestRate, exp)-1)))*request.getLoanAmount();
		return monthlyPayment;
	}

	private double calculateBilance(ScoringRequest request) {
		double bilance = 0d;
		if (request.isCoApplicant()){
			bilance=request.getIncomeOfApplicant()+request.getIncomeOfCoApplicant()-request.getExpensesOfApplicant()-request.getExpensesOfCoApplicant();
		}else{
			bilance=request.getIncomeOfApplicant()-request.getExpensesOfApplicant();
		}
		return bilance;
	}
	
	private String getMatchingLivingWages(Loan loan, ScoringRequest request){
		LivingWages wages = loan.getLivingWagesRates();
		if(request.getNumberOfAdultPersons()==1){
			switch (request.getNumberOfChildren()) {
			case 0:
				return wages.getSingleExpr();
			case 1:
				return wages.getSingleWithOneChildExpr();
			default:
				return null;
			}
		}
		if(request.getNumberOfAdultPersons()==2){
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

	private ScoringEAO getEAO(){
		if (this.eao==null){
			this.eao= new ScoringEAO(this.em);
		}
		return eao;
	}

	@Override
	public List<Scoring> getActualScoringsOfUser(User u) {
		List<Scoring> list = new ArrayList<Scoring>();
		Query q = em.createQuery("from Scoring s where s.applicant = :user");
		q.setParameter("user", u);
		list = (List<Scoring>)q.getResultList();
		return list;
	}


}
