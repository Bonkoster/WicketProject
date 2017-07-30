package org.Lukashman.Security;

import java.beans.Transient;
import java.util.function.ToDoubleBiFunction;

import org.Lukashman.DB.UserDAOImpl;
import org.Lukashman.Model.User;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AuthClass extends AuthenticatedWebSession {

	private static ClassPathXmlApplicationContext cp = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	
	private static ApplicationContext ctx = cp;
	UserDAOImpl UserDao = ctx.getBean("UserDAO",UserDAOImpl.class);
	
	private User user;
	
	public AuthClass(Request request) {
		super(request);
	}

	@Override
	protected boolean authenticate(String username, String password) {
		user = UserDao.getOne(username);
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
		
		return roles;
	}
}
