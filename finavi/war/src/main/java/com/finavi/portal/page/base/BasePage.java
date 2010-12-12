package com.finavi.portal.page.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.welcome.WelcomePage;

public class BasePage extends WebPage{

	public BasePage() {

		IModel<?> model = new LoadableDetachableModel<Object>() {

			private static final long serialVersionUID = 1L;

			protected Object load() {
				if (FinaviSession.get().isAuthenticated()) {
					return FinaviSession.get().getLoggedUser().getName()+" "+FinaviSession.get().getLoggedUser().getSurname()+
					" "+FinaviSession.get().getLoggedUser().getRoles()+" ";
				} else {
					return "Neprihlásený používateľ";
				}
			}
		};

		Label loggedUser = new Label("loginUser", model);
		add(loggedUser);

		add(new Link<String>("logoutLink") {

			private static final long serialVersionUID = 1L;

			public void onClick() {
				getSession().invalidate();
				setRedirect(true);
				setResponsePage(WelcomePage.class);
			} 
			
			@Override
			public boolean isVisible() {
				return FinaviSession.get().isAuthenticated();
			}
		});

	}
}
