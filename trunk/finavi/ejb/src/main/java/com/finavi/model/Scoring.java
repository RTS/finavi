package com.finavi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Scoring implements Serializable{

	private static final long serialVersionUID = 5523235066303002575L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToOne
	private User client;
	@OneToOne
	private Bank bank;
	
	private double rpmn;
	
	private double monthlyPayment;
	
	private double interestRate;
	
	private double accountFee;
	
	private double loanProcessCharge;
	
	private long age;
	
	private double incomeOfApplicant;
	
	private double expensesOfApplicant;
	
	private double loanAmount;
	
	private long repaymentPeriod;
	
	private long fixation;
	
	private double realPropertyValue;
	
	private String familyStatus;
	
	private int numberOfChildren;
	
	private int numberOfDependentChildren;
	
	private String agent;
	
	private String realPropertyType;
	
	private int numberOfAdultPersons;
	
	private boolean coApplicant;
	
	private double incomeOfCoApplicant;
	
	private double expensesOfCoApplicant;
	
	private String denialReason;
	
	public Scoring() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public double getRpmn() {
		return rpmn;
	}

	public void setRpmn(double rpmn) {
		this.rpmn = rpmn;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getAccountFee() {
		return accountFee;
	}

	public void setAccountFee(double accountFee) {
		this.accountFee = accountFee;
	}

	public double getLoanProcessCharge() {
		return loanProcessCharge;
	}

	public void setLoanProcessCharge(double loanProcessCharge) {
		this.loanProcessCharge = loanProcessCharge;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public double getIncomeOfApplicant() {
		return incomeOfApplicant;
	}

	public void setIncomeOfApplicant(double incomeOfApplicant) {
		this.incomeOfApplicant = incomeOfApplicant;
	}

	public double getExpensesOfApplicant() {
		return expensesOfApplicant;
	}

	public void setExpensesOfApplicant(double expensesOfApplicant) {
		this.expensesOfApplicant = expensesOfApplicant;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public long getFixation() {
		return fixation;
	}

	public void setFixation(long fixation) {
		this.fixation = fixation;
	}

	public double getRealPropertyValue() {
		return realPropertyValue;
	}

	public void setRealPropertyValue(double realPropertyValue) {
		this.realPropertyValue = realPropertyValue;
	}

	public String getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getRealPropertyType() {
		return realPropertyType;
	}

	public void setRealPropertyType(String realPropertyType) {
		this.realPropertyType = realPropertyType;
	}

	public int getNumberOfAdultPersons() {
		return numberOfAdultPersons;
	}

	public void setNumberOfAdultPersons(int numberOfAdultPersons) {
		this.numberOfAdultPersons = numberOfAdultPersons;
	}

	public boolean isCoApplicant() {
		return coApplicant;
	}

	public void setCoApplicant(boolean coApplicant) {
		this.coApplicant = coApplicant;
	}

	public double getIncomeOfCoApplicant() {
		return incomeOfCoApplicant;
	}

	public void setIncomeOfCoApplicant(double incomeOfCoApplicant) {
		this.incomeOfCoApplicant = incomeOfCoApplicant;
	}

	public double getExpensesOfCoApplicant() {
		return expensesOfCoApplicant;
	}

	public void setExpensesOfCoApplicant(double expensesOfCoApplicant) {
		this.expensesOfCoApplicant = expensesOfCoApplicant;
	}

	public long getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(long repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public int getNumberOfDependentChildren() {
		return numberOfDependentChildren;
	}

	public void setNumberOfDependentChildren(int numberOfDependentChildren) {
		this.numberOfDependentChildren = numberOfDependentChildren;
	}

	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}

	public String getDenialReason() {
		return denialReason;
	}
}
