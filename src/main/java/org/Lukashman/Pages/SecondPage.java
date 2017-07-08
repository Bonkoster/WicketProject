package org.Lukashman.Pages;

import org.Lukashman.Panels.ContentPanel;
import org.Lukashman.Panels.HeaderPanel;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.https.RequireHttps;

@RequireHttps
public class SecondPage extends WebPage {
	
	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
		
		if(!AuthenticatedWebSession.get().isSignedIn()){
			app.restartResponseAtSignInPage();
		}
	}
	
	HeaderPanel header;
	ContentPanel content;
	public SecondPage() {
			add(header = new HeaderPanel("header"));
			add(content = new ContentPanel("content"));
				
			add(new Link<Void>("logOut") {

				@Override
				public void onClick() {
					// TODO Auto-generated method stub
					AuthenticatedWebSession.get().invalidate();
					setResponsePage(Application.get().getHomePage());
				}
			});
		}
}
