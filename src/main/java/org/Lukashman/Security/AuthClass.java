package org.Lukashman.Security;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class AuthClass extends AuthenticatedWebSession {

	public AuthClass(Request request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean authenticate(String username, String password) {
		String wicket = "wicket";
		return username.equals(wicket) && password.equals(wicket);
	}

	@Override
	public Roles getRoles() {
		// TODO Auto-generated method stub
		return new Roles();
	}

}
