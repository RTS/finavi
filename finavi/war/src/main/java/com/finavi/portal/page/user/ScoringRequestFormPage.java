package com.finavi.portal.page.user;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.finavi.model.ScoringRequest;
import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.base.AutheticatedPage;
import com.finavi.portal.service.FinaviService;

public class ScoringRequestFormPage extends AutheticatedPage {

	private List<String> familyStatusList = Arrays.asList(new String[] {
			"slobodný", "ženatý", "rozvedený" });
	private List<String> highestEducationList = Arrays.asList(new String[] {
			"stredné", "stredné s maturitou", "vysokoškolské" });
	private List<String> realPropertyTypeList = Arrays.asList(new String[] {
			"byt", "dom", "iné" });
	private List<String> employmentList = Arrays.asList(new String[] {"Bankovníctvo", "Zdravotníctvo", "Verejná správa", "IT", "Telekomunikácie", "Priemysel", "Živnostník", "iné" });
	
	
	public ScoringRequestFormPage() {

		Form<ScoringRequest> form = new Form<ScoringRequest>(
				"scoringRequestForm",
				new CompoundPropertyModel<ScoringRequest>(
						getNewScoringRequest())) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				ScoringRequest request = getModelObject();
				request.setApplicant(FinaviSession.get().getLoggedUser());
				FinaviService.getMorgageService().calculateScorings(request);
				setResponsePage(ScoringTablePage.class);
			}

		};

		TextField<Long> age = new TextField<Long>("age");
		RequiredTextField<Double> incomeOfApplicant = new RequiredTextField<Double>(
				"incomeOfApplicant");
		RequiredTextField<Double> expensesOfApplicant = new RequiredTextField<Double>(
				"expensesOfApplicant");
		RequiredTextField<Double> loanAmount = new RequiredTextField<Double>("loanAmount");
		TextField<Long> repaymentPeriod = new TextField<Long>("repaymentPeriod");
		RequiredTextField<Long> fixation = new RequiredTextField<Long>("fixation");
		RequiredTextField<Double> realPropertyValue = new RequiredTextField<Double>(
				"realPropertyValue");
		DropDownChoice familyStatus = new DropDownChoice("familyStatus",
				familyStatusList);
		
		TextField<Integer> numberOfChildren = new TextField<Integer>(
				"numberOfChildren");
		TextField<Integer> numberOfDependentChildren = new TextField<Integer>(
				"numberOfDependentChildren");
		DropDownChoice realPropertyType = new DropDownChoice("realPropertyType",
				realPropertyTypeList);
		TextField<Integer> numberOfAdultPersons = new TextField<Integer>(
				"numberOfAdultPersons");
		CheckBox coApplicant = new CheckBox("coApplicant");

		TextField<Double> incomeOfCoApplicant = new TextField<Double>(
				"incomeOfCoApplicant");
		TextField<Double> expensesOfCoApplicant = new TextField<Double>(
				"expensesOfCoApplicant");
		DropDownChoice highestEducation = new DropDownChoice("highestEducation",
				highestEducationList);
		DropDownChoice employment = new DropDownChoice("employment",
				employmentList);
		employment.setNullValid(true);
		TextField<String> currentHousing = new TextField<String>(
				"currentHousing");

		form.add(age);
		form.add(incomeOfApplicant);
		form.add(expensesOfApplicant);
		form.add(loanAmount);
		form.add(repaymentPeriod);
		form.add(repaymentPeriod);
		form.add(fixation);
		form.add(realPropertyValue);
		form.add(familyStatus);
		form.add(numberOfChildren);
		form.add(numberOfDependentChildren);
		form.add(realPropertyType);
		form.add(numberOfAdultPersons);
		form.add(coApplicant);
		form.add(incomeOfCoApplicant);
		form.add(expensesOfCoApplicant);
		form.add(highestEducation);
		form.add(employment);
		form.add(currentHousing);

		add(form);

	}

	private ScoringRequest getNewScoringRequest() {
		ScoringRequest scoringRequest = new ScoringRequest();
		scoringRequest.setFamilyStatus(familyStatusList.get(0));
		scoringRequest.setHighestEducation(highestEducationList.get(0));
		scoringRequest.setRealPropertyType(realPropertyTypeList.get(0));
		scoringRequest.setEmployment(null);
		
		return scoringRequest;
	}

}
