package org.Lukashman.Pages;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.util.string.Strings;

import javafx.scene.control.PasswordField;

@RequireHttps
public class SignInPage extends WebPage {
	
	private String username;
	private String password;
	
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
					continueToOriginalDestination();
				}
				
			}	
		};
		
		form.setDefaultModel(new CompoundPropertyModel(this));
		
		form.add(new TextField<>("username"));
		form.add(new PasswordTextField("password"));
		
		add(form);
	}

}
