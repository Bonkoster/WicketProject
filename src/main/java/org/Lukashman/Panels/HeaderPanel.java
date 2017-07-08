package org.Lukashman.Panels;

import org.Lukashman.Pages.SignInPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

public class HeaderPanel extends Panel {

	public HeaderPanel(String id) {
		super(id);
		add(new Label("head","Monster"));
		
		if(!AuthenticatedWebSession.get().isSignedIn()){
			add(new Link<Void>("signin") {
				
				@Override
				public void onClick() {
					setResponsePage(SignInPage.class);
					
				}
			});
		} else {
			addOrReplace(new Label("signin","You logged up"));
		}
	}

}
