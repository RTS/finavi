package com.finavi.portal.application;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;

import com.finavi.portal.page.bank.BankEvidencePage;
import com.finavi.portal.page.base.AutheticatedPage;
import com.finavi.portal.page.user.AgentRegistrationPage;
import com.finavi.portal.page.user.RegistrationPage;
import com.finavi.portal.page.user.ScoringRequestFormPage;
import com.finavi.portal.page.user.UserTablePage;
import com.finavi.portal.page.welcome.AboutPage;
import com.finavi.portal.page.welcome.WelcomePage;

public class FinaviApplication extends WebApplication {

	public FinaviApplication() {
		mountBookmarkablePage("/registracia", RegistrationPage.class);
		mountBookmarkablePage("/ziadostHypoteky", ScoringRequestFormPage.class);
		mountBookmarkablePage("/about", AboutPage.class);
		mountBookmarkablePage("/pouzivatelia", UserTablePage.class);
		mountBookmarkablePage("/banky", BankEvidencePage.class);
		mountBookmarkablePage("/agent", AgentRegistrationPage.class);
	}

	@Override
	public Class<? extends WelcomePage> getHomePage() {
		return WelcomePage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new FinaviSession(request);
	}

	@Override
	protected void init() {
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		getMarkupSettings().setStripXmlDeclarationFromOutput(false);
		getSecuritySettings().setAuthorizationStrategy(
				new SimplePageAuthorizationStrategy(AutheticatedPage.class,
						WelcomePage.class) {

					@Override
					protected boolean isAuthorized() {
						return FinaviSession.get().isAuthenticated();
					}
				});
	}

}
