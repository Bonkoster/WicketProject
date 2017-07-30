package org.Lukashman.Pages;

import java.io.Serializable;

import org.Lukashman.DB.UserDAOImpl;
import org.Lukashman.Model.User;
import org.Lukashman.Panels.HeaderPanel;
import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import javafx.scene.control.PasswordField;

@RequireHttps
public class SignInPage extends WebPage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private HeaderPanel header;
	
	public SignInPage() {
		add(header = new HeaderPanel("header"));
	}
	
	
	@Override
	protected void onInitialize() {
		super.onInitialize();	
		StatelessForm<Void> form = new StatelessForm<Void>("form"){
			protected void onSubmit() {
				if (Strings.isEmpty(username) && Strings.isEmpty(password)) {
					return;
				}
				
				boolean AuthResult = AuthenticatedWebSession.get().signIn(username, password);
				
				if(AuthResult) {
					PageParameters params = new PageParameters();
					params.add("username", username);
					setResponsePage(HomePage.class,params);
				}
				
			}	
		};
		
		TextField<Void> userfield = new TextField<>("username");
		PasswordTextField passfield = new PasswordTextField("password");
		
		form.setDefaultModel(new CompoundPropertyModel(this));
		
		form.add(userfield);
		form.add(passfield);

		
		add(form);
	}

}
