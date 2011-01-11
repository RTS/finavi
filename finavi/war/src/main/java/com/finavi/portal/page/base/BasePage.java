package com.finavi.portal.page.base;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.extensions.util.encoding.CharSetUtil;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.finavi.model.User;
import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.bank.BankEvidencePage;
import com.finavi.portal.page.user.ScoringRequestFormPage;
import com.finavi.portal.page.user.ScoringTablePage;
import com.finavi.portal.page.user.UserTablePage;
import com.finavi.portal.page.welcome.WelcomePage;

public class BasePage extends WebPage {

	protected FeedbackPanel mainFeedback = new FeedbackPanel("mainFeedback");
	protected List<String> highestEducationList = Arrays.asList(new String[] {
			"stredné", "stredné s maturitou", "vysokoškolské" });
	protected List<String> employmentList = Arrays.asList(new String[] {
			"Bankovníctvo", "Zdravotníctvo", "Verejná správa", "IT",
			"Telekomunikácie", "Priemysel", "Živnostník", "iné" });
	protected List<Integer> childList = Arrays.asList(new Integer[] { 0, 1, 2,
			3, 4, 5 });

	public BasePage() {

		mainFeedback.setOutputMarkupId(true);
		add(mainFeedback);

		IModel<?> model = new LoadableDetachableModel<Object>() {

			private static final long serialVersionUID = 1L;

			protected Object load() {
				if (FinaviSession.get().isAuthenticated()) {
					return FinaviSession.get().getLoggedUser().getName() + " "
							+ FinaviSession.get().getLoggedUser().getSurname()
							+ " "
							+ FinaviSession.get().getLoggedUser().getRoles()
							+ " ";
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
				if (FinaviSession.get().isAuthenticated()) {
					User u = FinaviSession.get().getLoggedUser();
					return User.isUserInRole(u, "agent");
				} else {
					return false;
				}
			}
		});

		add(new Link<String>("scoringRequestLink") {

			private static final long serialVersionUID = 1235674532L;

			public void onClick() {
				setRedirect(true);
				setResponsePage(ScoringRequestFormPage.class);
			}

			@Override
			public boolean isVisible() {
				if (FinaviSession.get().isAuthenticated()) {
					User u = FinaviSession.get().getLoggedUser();
					return User.isUserInRole(u, "user");
				} else {
					return false;
				}
			}
		});

		add(new Link<String>("bankTable") {

			private static final long serialVersionUID = 1235674532L;

			public void onClick() {
				setRedirect(true);
				setResponsePage(BankEvidencePage.class);
			}

			@Override
			public boolean isVisible() {
				if (FinaviSession.get().isAuthenticated()) {
					User u = FinaviSession.get().getLoggedUser();
					return User.isUserInRole(u, "agent");
				} else {
					return false;
				}
			}
		});

		add(new Link<String>("myScoringLink") {

			private static final long serialVersionUID = 1235674532L;

			public void onClick() {
				setRedirect(true);
				setResponsePage(ScoringTablePage.class);
			}

			@Override
			public boolean isVisible() {
				if (FinaviSession.get().isAuthenticated()) {
					User u = FinaviSession.get().getLoggedUser();
					return User.isUserInRole(u, "user");
				} else {
					return false;
				}
			}
		});
	}

	
	@Override
	protected void configureResponse()
	{
		getSession().setLocale(new Locale("sk", "SK"));
		super.configureResponse();

		final String encoding = "text/" + getMarkupType() + "; charset="
				+ "UTF-8";

		getResponse().setContentType(encoding);
	}

	
}
