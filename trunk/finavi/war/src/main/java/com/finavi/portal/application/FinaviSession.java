package com.finavi.portal.application;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import com.finavi.model.User;

public class FinaviSession extends WebSession{

	private static final long serialVersionUID = 1L;

	private User loggedUser;

	public static FinaviSession get() { return (FinaviSession) Session.get();
	}

	public FinaviSession(Request request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public boolean isAuthenticated() {
		return (loggedUser==null)?false:true;
	}

}
