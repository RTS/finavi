package com.finavi.portal.page.user;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.finavi.model.ScoringRequest;
import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.base.AutheticatedPage;
import com.finavi.portal.page.welcome.WelcomePage;
import com.finavi.portal.service.FinaviService;

public class ScoringRequestFormPage extends AutheticatedPage{

	public ScoringRequestFormPage() {
		
		Form<ScoringRequest> form = new Form<ScoringRequest>("scoringRequestForm", new CompoundPropertyModel<ScoringRequest>(new ScoringRequest())){
			
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
		TextField<Double> incomeOfApplicant = new TextField<Double>("incomeOfApplicant");
		TextField<Double> expensesOfApplicant = new TextField<Double>("expensesOfApplicant");
		TextField<Double> loanAmount = new TextField<Double>("loanAmount");
		TextField<Long> repaymentPeriod = new TextField<Long>("repaymentPeriod");
		TextField<Long> fixation = new TextField<Long>("fixation");
		TextField<Double> realPropertyValue = new TextField<Double>("realPropertyValue");
		TextField<String> familyStatus = new TextField<String>("familyStatus");
		TextField<Integer> numberOfChildren = new TextField<Integer>("numberOfChildren");
		TextField<Integer> numberOfDependentChildren = new TextField<Integer>("numberOfDependentChildren");
		TextField<String> realPropertyType = new TextField<String>("realPropertyType");
		TextField<Integer> numberOfAdultPersons = new TextField<Integer>("numberOfAdultPersons");
		CheckBox coApplicant = new CheckBox("coApplicant");
		
		
		TextField<Double> incomeOfCoApplicant = new TextField<Double>("incomeOfCoApplicant");
		TextField<Double> expensesOfCoApplicant = new TextField<Double>("expensesOfCoApplicant");
		TextField<String> highestEducation = new TextField<String>("highestEducation");
		TextField<String> employment = new TextField<String>("employment");
		TextField<String> currentHousing = new TextField<String>("currentHousing");
		
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
		
		form.add(new FeedbackPanel("feedbackPanel"));
		
		add(form);
		
	}

}
