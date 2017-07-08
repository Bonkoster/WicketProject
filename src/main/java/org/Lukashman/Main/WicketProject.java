package org.Lukashman.Main;

import org.Lukashman.Pages.HomePage;
import org.Lukashman.Pages.SecondPage;
import org.Lukashman.Pages.SignInPage;
import org.Lukashman.Security.AuthClass;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketProject extends AuthenticatedWebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();		
		setRootRequestMapper(new CryptoMapper(getRootRequestMapper(),this));
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
		mountPage("/main", HomePage.class);
		mountPage("/second", SecondPage.class);
		
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {

		return SignInPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		
		return AuthClass.class;
	}
}
