package com.finavi.portal.page.welcome;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.finavi.model.User;
import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.base.BasePage;
import com.finavi.portal.page.user.ScoringTablePage;
import com.finavi.portal.page.user.UserTablePage;
import com.finavi.portal.service.FinaviService;

public class WelcomePage extends BasePage {

	public WelcomePage() {

		final CompoundPropertyModel<User> model = new CompoundPropertyModel<User>(
				new User());

		Form<User> form = new Form<User>("loginForm", model) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return !FinaviSession.get().isAuthenticated();
			}

			@Override
			protected void onSubmit() {
				User user = null;
				try {
					user = FinaviService.getUserService().login(
							model.getObject().getEmail(),
							model.getObject().getPassword());
					((FinaviSession) getSession()).setLoggedUser(user);
					clearInput();

					if (User.isUserInRole(user, "user")) {
						setResponsePage(ScoringTablePage.class);
					}
					if (User.isUserInRole(user, "agent")) {
						setResponsePage(UserTablePage.class);
					}
				} catch (Exception e) {
					this.error("Nesprávne meno alebo heslo!");
				}
			}
		};
		TextField<String> loginField = new TextField<String>("email");
		PasswordTextField passwordField = new PasswordTextField("password");

		form.add(loginField);
		form.add(passwordField);
		add(form);
	}

}
