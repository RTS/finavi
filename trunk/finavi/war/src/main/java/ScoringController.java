import java.util.ArrayList;
import java.util.List;

import com.finavi.model.Scoring;


public class ScoringController extends FinaviController {
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
	
	private List<Scoring> scorings = new ArrayList<Scoring>();
	
	public ScoringController(){
		super();
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

	public List<Scoring> getScorings() {
		return scorings;
	}

	public void setScorings(List<Scoring> scorings) {
		this.scorings = scorings;
	}
	
}
