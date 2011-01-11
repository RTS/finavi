package com.finavi.portal.page.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.finavi.model.User;
import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.base.BasePage;
import com.finavi.portal.service.FinaviService;

public class RegistrationPage extends BasePage{
	
	public RegistrationPage() {
		Form<User> form = new Form<User>("registrationForm",new CompoundPropertyModel<User>(new User())){

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				System.out.println(getModelObject().getCity());
				User u = FinaviService.getUserService().register(getModelObject());
				if (u!=null){
					((FinaviSession) getSession()).setLoggedUser(u);
					setResponsePage(ScoringTablePage.class);
				}
				
			}
		};
		
		TextField<String> name = new TextField<String>("name");
		TextField<String> surname = new TextField<String>("surname");
		DateTextField dateOfBirth = new DateTextField("dateOfBirth","dd.MM.yyyy");
		dateOfBirth.add(new DatePicker());
		dateOfBirth.add(new AbstractBehavior() {
			@Override
			public void onRendered(Component component) {
				component.getResponse().write("<span class=\"required\">*</span>");
			}
		});
		
//		TextField<Character> sex = new TextField<Character>("sex");
		List<Character> list2 = new ArrayList<Character>();
		list2.add(new Character('M'));
		list2.add(new Character('Z'));
		DropDownChoice<Character> sex = new DropDownChoice<Character>("sex", list2);
		sex.setNullValid(true);
		sex.setRequired(true);
		sex.add(new AbstractBehavior() {
			@Override
			public void onRendered(Component component) {
				component.getResponse().write("<span class=\"required\">*</span>");
			}
		});
		
		TextField<String> personalNumber = new TextField<String>("personalNumber");
		TextField<String> street = new TextField<String>("street");
		TextField<String> streetNumber = new TextField<String>("streetNumber");
		TextField<String> city = new TextField<String>("city");
		TextField<String> postalCode = new TextField<String>("postalCode");
		TextField<String> phone = new TextField<String>("phone");
		RequiredTextField<String> email = new RequiredTextField<String>("email");
		email.add(new AbstractBehavior() {
			@Override
			public void onRendered(Component component) {
				
				component.getResponse().write("<span class=\"required\">*</span>");
			}
		});
		email.add(EmailAddressValidator.getInstance());
		PasswordTextField password = new PasswordTextField("password");
		password.add(new AbstractBehavior() {
			@Override
			public void onRendered(Component component) {
				component.getResponse().write("<span class=\"required\">*</span>");
			}
		});
		DropDownChoice highestEducation = new DropDownChoice("highestEducation",
				highestEducationList);
		highestEducation.setNullValid(true);
		DropDownChoice employment = new DropDownChoice("employment",
				employmentList);
		employment.setNullValid(true);
		//TextField<Boolean> alreadyClientOfBank = new TextField<Boolean>("alreadyClientOfBank");
		
		form.add(name);
		form.add(surname);
		form.add(dateOfBirth);
		form.add(sex);
		form.add(personalNumber);
		form.add(street);
		form.add(streetNumber);
		form.add(city);
		form.add(postalCode);
		form.add(phone);
		form.add(email);
		form.add(password);
		form.add(highestEducation);
		form.add(employment);
		add(form);
		
	}
	
}
