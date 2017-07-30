package org.Lukashman.Panels;

import org.Lukashman.Pages.HomePage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.validation.validator.UrlValidator;

public class ContentPanel extends Panel {

	private WebMarkupContainer formCont;
	private StatelessForm<Void> form;
	private TextField<String> textField;
	private TextField<String> urlField;
	
	public ContentPanel(String id) {
		super(id);
		
		formCont = new WebMarkupContainer("AddImage");
		
		form = new StatelessForm<>("FormAddImage");
		
		textField = new TextField<>("NameField");
		urlField = new TextField<>("LinkField");
		
		urlField.add(new UrlValidator());
		
		form.add(textField);
		form.add(urlField);
		
		formCont.add(form);
		
		if (!AuthenticatedWebSession.get().isSignedIn()){
			formCont.setVisible(false);
		}
		
		super.add(formCont);
		
	}

}
