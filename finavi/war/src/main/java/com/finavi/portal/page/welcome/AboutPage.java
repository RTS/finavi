package com.finavi.portal.page.welcome;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;

import com.finavi.model.User;
import com.finavi.portal.page.base.BasePage;

public class AboutPage extends WelcomePage {
	public AboutPage() {
		super();
		final CompoundPropertyModel<User> model = new CompoundPropertyModel<User>(
				new User());

		Form<User> form = new Form<User>("aboutForm", model) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {

			}
		};

	}
}
