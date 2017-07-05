package org.Lukashman.Main;

import org.Lukashman.Pages.HomePage;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.mapper.CryptoMapper;
import org.apache.wicket.protocol.http.WebApplication;

public class WicketProject extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();		
		setRootRequestMapper(new CryptoMapper(getRootRequestMapper(),this));
		mountPage("/main", HomePage.class);
		
	}
}
