package org.Lukashman.Security;

import org.Lukashman.DB.UserDAOImpl;
import org.Lukashman.Model.User;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class AuthClass extends AuthenticatedWebSession {

	private UserDAOImpl userDAO;
	private static User user = new User();
	
	public AuthClass(Request request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean authenticate(String username, String password) {
		userDAO = new UserDAOImpl();
		user = userDAO.getOne(username);
		return username.equals(user.getUsername()) && password.equals(user.getPassword());
	}

	@Override
	public Roles getRoles() {
		 
		Roles roles = new Roles();
		
		if (user.getRole().equals("User")){
			roles.add(Roles.USER);
		}
		
		if (user.getRole().equals("Admin")){
			roles.add(Roles.USER);
			roles.add(Roles.ADMIN);
		}
		
		return new Roles();
	}

}
