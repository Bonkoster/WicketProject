package org.Lukashman.Pages;

import java.util.ArrayList;
import java.util.List;

import org.Lukashman.DB.UserDAOImpl;
import org.Lukashman.Model.User;
import org.Lukashman.Panels.ContentPanel;
import org.Lukashman.Panels.HeaderPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.beans.factory.annotation.Autowired;

public class HomePage extends WebPage {
		
	HeaderPanel header;
	ContentPanel content;
	@Autowired
	UserDAOImpl UserDAO;
	List<User> users;
	@Override
	protected void onConfigure() {
		// TODO Auto-generated method stub
		super.onConfigure();
		
				
	}

	public HomePage() {
			add(header = new HeaderPanel("header"));
			add(content = new ContentPanel("content"));
		}
}
