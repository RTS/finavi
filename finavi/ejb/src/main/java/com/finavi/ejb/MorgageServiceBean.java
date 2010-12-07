package com.finavi.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.finavi.ejb.eao.ScoringEAO;
import com.finavi.model.Bank;
import com.finavi.model.Loan;
import com.finavi.model.Scoring;
import com.finavi.model.ScoringRequest;

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
		scoring.setClient(request.getApplicant());
		if(loan.getMaxAmount()>request.getLoanAmount()){
			scoring.setDenialReason("Ziadana suma je vyssia ako je maximalny mozny uver: "+loan.getMaxAmount());
			return scoring;
		}
		if (loan.getMinAmount()<request.getLoanAmount()){
			scoring.setDenialReason("Ziadana suma je nizzia ako je minimalny mozny uver: "+loan.getMinAmount());
			return scoring;
		}
		if (loan.getMaxYears()<request.getRepaymentPeriod()){
			scoring.setDenialReason("Ziadana doba prekracuje maximalnu hranicu: "+loan.getMaxYears());
			return scoring;
		}
		if (loan.getMinYears()>request.getRepaymentPeriod()){
			scoring.setDenialReason("Ziadana doba je nizzia ako minimalna hranicu: "+loan.getMinYears());
			return scoring;
		}
		
		return scoring;
	}

	private ScoringEAO getEAO(){
		if (this.eao==null){
			this.eao= new ScoringEAO(this.em);
		}
		return eao;
	}


}
