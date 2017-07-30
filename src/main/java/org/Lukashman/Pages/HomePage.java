package org.Lukashman.Pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.Lukashman.DB.UserDAO;
import org.Lukashman.DB.UserDAOImpl;
import org.Lukashman.Model.User;
import org.Lukashman.Panels.ContentPanel;
import org.Lukashman.Panels.HeaderPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HomePage extends WebPage implements Serializable {
		
	private static final long serialVersionUID = -4964696433835589639L;
	private HeaderPanel header;
	private ContentPanel content;
	
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	private UserDAOImpl UserDao = ctx.getBean("UserDAO",UserDAOImpl.class);
	
	private List<User> users;
	@Override
	protected void onConfigure() {
		super.onConfigure();
		users = UserDao.getAll();
	}

	public HomePage(PageParameters params) {
			add(header = new HeaderPanel("header"));
			add(content = new ContentPanel("content"));
			
			if(!params.isEmpty()){
				
				header.setUserName(params.get(0).toString());
			}
		}
}
