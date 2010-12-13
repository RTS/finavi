package com.finavi.portal.page.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.finavi.model.User;
import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.user.UserTablePage;
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
		
		add(new Link<String>("userTable") {

			private static final long serialVersionUID = 1123152453L;

			public void onClick() {
				setRedirect(true);
				setResponsePage(UserTablePage.class);
			} 
			
			@Override
			public boolean isVisible() {
				if (FinaviSession.get().isAuthenticated()){
					User u = FinaviSession.get().getLoggedUser();
					return User.isUserInRole(u, "agent");
				}else {
					return false;
				}
			}
		});
		
		add(new Link<String>("scoringRequestLink") {

			private static final long serialVersionUID = 1235674532L;

			public void onClick() {
				setRedirect(true);
				setResponsePage(WelcomePage.class);
			} 
			
			@Override
			public boolean isVisible() {
				return FinaviSession.get().isAuthenticated();
			}
		});

		
		add(new Link<String>("bankTable") {

			private static final long serialVersionUID = 1235674532L;

			public void onClick() {
				setRedirect(true);
				setResponsePage(WelcomePage.class);
			} 
			
			@Override
			public boolean isVisible() {
				if (FinaviSession.get().isAuthenticated()){
					User u = FinaviSession.get().getLoggedUser();
					return User.isUserInRole(u, "agent");
				}else {
					return false;
				}
			}
		});

	}
}
