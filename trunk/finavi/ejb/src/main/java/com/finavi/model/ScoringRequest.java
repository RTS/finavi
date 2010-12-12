package com.finavi.model;

import java.io.Serializable;

public class ScoringRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private User applicant;
	
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
	
	private String realPropertyType;
	
	private int numberOfAdultPersons;
	
	private boolean coApplicant;
	
	private double incomeOfCoApplicant;
	
	private double expensesOfCoApplicant;
	
	private String highestEducation;
	
	private String employment;
	
	private String currentHousing;
	
	public ScoringRequest() {
	}
	
	public ScoringRequest(User applicant, long age, double incomeOfApplicant,
			double expensesOfApplicant, double loanAmount,
			long repaymentPeriod, long fixation, double realPropertyValue,
			String familyStatus, int numberOfChildren,
			int numberOfDependentChildren, String realPropertyType,
			int numberOfAdultPersons, boolean coApplicant,
			double incomeOfCoApplicant, double expensesOfCoApplicant,
			String highestEducation, String employment, String currentHousing) {
		super();
		this.applicant = applicant;
		this.age = age;
		this.incomeOfApplicant = incomeOfApplicant;
		this.expensesOfApplicant = expensesOfApplicant;
		this.loanAmount = loanAmount;
		this.repaymentPeriod = repaymentPeriod;
		this.fixation = fixation;
		this.realPropertyValue = realPropertyValue;
		this.familyStatus = familyStatus;
		this.numberOfChildren = numberOfChildren;
		this.numberOfDependentChildren = numberOfDependentChildren;
		this.realPropertyType = realPropertyType;
		this.numberOfAdultPersons = numberOfAdultPersons;
		this.coApplicant = coApplicant;
		this.incomeOfCoApplicant = incomeOfCoApplicant;
		this.expensesOfCoApplicant = expensesOfCoApplicant;
		this.highestEducation = highestEducation;
		this.employment = employment;
		this.currentHousing = currentHousing;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
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

	public long getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(long repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
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

	public int getNumberOfDependentChildren() {
		return numberOfDependentChildren;
	}

	public void setNumberOfDependentChildren(int numberOfDependentChildren) {
		this.numberOfDependentChildren = numberOfDependentChildren;
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

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public String getCurrentHousing() {
		return currentHousing;
	}

	public void setCurrentHousing(String currentHousing) {
		this.currentHousing = currentHousing;
	}
	
}
