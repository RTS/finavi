package com.finavi.model;

import java.io.Serializable;

public class ScoringRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private User applicant;

	private Long age;

	private Double incomeOfApplicant;

	private Double expensesOfApplicant;

	private Double loanAmount;

	private long repaymentPeriod;

	private Long fixation;

	private Double realPropertyValue;

	private String familyStatus;

	private Integer numberOfChildren;

	private Integer numberOfDependentChildren;

	private String realPropertyType;

	private Integer numberOfAdultPersons;

	private Boolean coApplicant;

	private Double incomeOfCoApplicant;

	private Double expensesOfCoApplicant;

	private String highestEducation;

	private String employment;

	private String currentHousing;

	public ScoringRequest() {
	}

	public ScoringRequest(User applicant, Long age, Double incomeOfApplicant,
			Double expensesOfApplicant, Double loanAmount,
			long repaymentPeriod, Long fixation, Double realPropertyValue,
			String familyStatus, Integer numberOfChildren,
			Integer numberOfDependentChildren, String realPropertyType,
			Integer numberOfAdultPersons, Boolean coApplicant,
			Double incomeOfCoApplicant, Double expensesOfCoApplicant,
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Double getIncomeOfApplicant() {
		return incomeOfApplicant;
	}

	public void setIncomeOfApplicant(Double incomeOfApplicant) {
		this.incomeOfApplicant = incomeOfApplicant;
	}

	public Double getExpensesOfApplicant() {
		return expensesOfApplicant;
	}

	public void setExpensesOfApplicant(Double expensesOfApplicant) {
		this.expensesOfApplicant = expensesOfApplicant;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public long getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(long repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public Long getFixation() {
		return fixation;
	}

	public void setFixation(Long fixation) {
		this.fixation = fixation;
	}

	public Double getRealPropertyValue() {
		return realPropertyValue;
	}

	public void setRealPropertyValue(Double realPropertyValue) {
		this.realPropertyValue = realPropertyValue;
	}

	public String getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}

	public Integer getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(Integer numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public Integer getNumberOfDependentChildren() {
		return numberOfDependentChildren;
	}

	public void setNumberOfDependentChildren(Integer numberOfDependentChildren) {
		this.numberOfDependentChildren = numberOfDependentChildren;
	}

	public String getRealPropertyType() {
		return realPropertyType;
	}

	public void setRealPropertyType(String realPropertyType) {
		this.realPropertyType = realPropertyType;
	}

	public Integer getNumberOfAdultPersons() {
		return numberOfAdultPersons;
	}

	public void setNumberOfAdultPersons(Integer numberOfAdultPersons) {
		this.numberOfAdultPersons = numberOfAdultPersons;
	}

	public Boolean getCoApplicant() {
		return coApplicant;
	}

	public void setCoApplicant(Boolean coApplicant) {
		this.coApplicant = coApplicant;
	}

	public Double getIncomeOfCoApplicant() {
		return incomeOfCoApplicant;
	}

	public void setIncomeOfCoApplicant(Double incomeOfCoApplicant) {
		this.incomeOfCoApplicant = incomeOfCoApplicant;
	}

	public Double getExpensesOfCoApplicant() {
		return expensesOfCoApplicant;
	}

	public void setExpensesOfCoApplicant(Double expensesOfCoApplicant) {
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
