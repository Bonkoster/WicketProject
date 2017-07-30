package org.Lukashman.Panels;

import org.Lukashman.Pages.SignInPage;
import org.Lukashman.Pages.SignUpPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

public class HeaderPanel extends Panel {

	public String userName = "";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public HeaderPanel(String id) {
		super(id);
		
		WebMarkupContainer web = new WebMarkupContainer("sign");
		WebMarkupContainer logout = new WebMarkupContainer("logout");
		
		if(!AuthenticatedWebSession.get().isSignedIn()){
			web.add(new Link<Void>("signin") {
				
				@Override
				public void onClick() {
					setResponsePage(SignInPage.class);
					
				}
			});
			
			web.add(new Link<Void>("signup"){

				@Override
				public void onClick() {
					setResponsePage(SignUpPage.class);
					
				}
				
			});
			logout.setVisible(false);
		} else {		
			web.setVisible(false);
			logout.setVisible(true);
			String name = getUserName();
			logout.add(new Link<Void>("logu"){

				@Override
				public void onClick() {
					AuthenticatedWebSession.get().signOut();
					
				}
				
			});
			logout.add(new Label("person", "You logged in as " + name));
			
		}
		add(web);
		add(logout);
	} 
	}

