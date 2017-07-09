package org.Lukashman.Pages;

import org.Lukashman.DB.UserDAOImpl;
import org.Lukashman.Model.User;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;
import org.springframework.beans.factory.annotation.Autowired;

public class SignUpPage extends WebPage {
	

	private String username;
	private String password;

	@Autowired
	UserDAOImpl UserDAO;
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		StatelessForm<Void> form = new StatelessForm<Void>("form"){
			/**
			 * 
			 */
			private static final long serialVersionUID = -1512144617186872898L;

			protected void onSubmit() {
				if (Strings.isEmpty(username) && Strings.isEmpty(password)) {
					return;
				}
				
				User user = new User(username, password);
				UserDAO.addOne(user);
				
				boolean AuthResult = AuthenticatedWebSession.get().signIn(username, password);
				
				if(AuthResult) {
					setResponsePage(HomePage.class);
				}
				
			}	
		};
		
		form.setDefaultModel(new CompoundPropertyModel(this));
		
		form.add(new TextField<>("username"));
		form.add(new PasswordTextField("password"));
		
		add(form);
	}

}
