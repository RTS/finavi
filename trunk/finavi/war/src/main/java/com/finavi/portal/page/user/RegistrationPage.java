package com.finavi.portal.page.user;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.finavi.model.User;
import com.finavi.portal.page.base.BasePage;
import com.finavi.portal.service.FinaviService;

public class RegistrationPage extends BasePage{
	
	public RegistrationPage() {
		Form<User> form = new Form<User>("registrationForm",new CompoundPropertyModel<User>(new User())){

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				System.out.println(getModelObject().getCity());
				System.out.println(FinaviService.getUserService().register(getModelObject()));
			}
		};
		
		TextField<String> name = new TextField<String>("name");
		TextField<String> surname = new TextField<String>("surname");
		DateTextField dateOfBirth = new DateTextField("dateOfBirth","dd.MM.yyyy");
		TextField<Character> sex = new TextField<Character>("sex");
		TextField<String> personalNumber = new TextField<String>("personalNumber");
		TextField<String> street = new TextField<String>("street");
		TextField<String> streetNumber = new TextField<String>("streetNumber");
		TextField<String> city = new TextField<String>("city");
		TextField<String> postalCode = new TextField<String>("postalCode");
		TextField<String> phone = new TextField<String>("phone");
		TextField<String> email = new TextField<String>("email");
		PasswordTextField password = new PasswordTextField("password");
		TextField<String> highestEducation = new TextField<String>("highestEducation");
		TextField<String> employment = new TextField<String>("employment");
		TextField<Boolean> alreadyClientOfBank = new TextField<Boolean>("alreadyClientOfBank");
		
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
		form.add(alreadyClientOfBank);
		
		add(form);
		
	}
	
}
