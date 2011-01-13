package com.finavi.portal.page.user;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.finavi.model.User;
import com.finavi.portal.page.base.AutheticatedPage;
import com.finavi.portal.service.FinaviService;

public class AgentRegistrationPage extends AutheticatedPage {

	public AgentRegistrationPage() {
		Form<User> form = new Form<User>("agentRegistrationForm",
				new CompoundPropertyModel<User>(new User())) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				User u = FinaviService.getUserService().getByEmail(
						getModelObject().getEmail());
				if (u != null) {
					User agent = FinaviService.getUserService()
							.switchToAgent(u);
					// setResponsePage(ScoringTablePage.class);
					info("Registrácia agenta " + u.getEmail()
							+ " prebehla úspešne");
				} else {
					error("Nepodarilo sa zaregistrovať agenta. Emailová adresa nie je v databáze. Najskôr je potrebné, aby sa pouzivateľ "
							+ getModelObject().getEmail() + " zaregistroval.");
				}

			}
		};

		RequiredTextField<String> email = new RequiredTextField<String>("email");
		email.add(new AbstractBehavior() {
			@Override
			public void onRendered(Component component) {

				component.getResponse().write(
						"<span class=\"required\">*</span>");
			}
		});
		email.add(EmailAddressValidator.getInstance());
		form.add(email);
		add(form);

	}

}
