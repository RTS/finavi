package com.finavi.portal.application;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;

import com.finavi.portal.page.welcome.WelcomePage;

public class FinaviApplication extends WebApplication{

	@Override
	public Class<? extends WelcomePage> getHomePage() {
		return WelcomePage.class;
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return new FinaviSession(request);
	}

}
