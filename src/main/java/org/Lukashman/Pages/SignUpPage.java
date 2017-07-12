package org.Lukashman.Pages;

import org.Lukashman.DB.UserDAOImpl;
import org.Lukashman.Model.User;
import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;
import org.springframework.beans.factory.annotation.Autowired;

public class SignUpPage extends WebPage {
	
	private static final long serialVersionUID = -2100817917319866515L;
	private String username;
	private String password;
	
	private static final Logger logger = Logger.getLogger(SignUpPage.class);

	@Autowired
	UserDAOImpl UserDAO;
	public static User user;
	
	public SignUpPage() {
		Form<?> form = new Form<Void>("form"){

			protected void onSubmit() {
				if (Strings.isEmpty(username) && Strings.isEmpty(password)) {
					return;
				}
				user = new User(username, password);
				System.out.println(user.getUsername() + user.getPassword());
				UserDAO.addOne(user);
				
				setResponsePage(HomePage.class);
				
			}
			
			@Override
			protected void onError() {
				// TODO Auto-generated method stub
				super.onError();
				System.out.println("Something wrong");
			}
		};
		
		form.setDefaultModel(new CompoundPropertyModel(this));
		
		form.add(new TextField<>("username"));
		form.add(new PasswordTextField("password"));
		
		add(form);
	}

}
