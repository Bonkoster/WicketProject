package org.Lukashman.Pages;

import org.Lukashman.Panels.ContentPanel;
import org.Lukashman.Panels.HeaderPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
		
	HeaderPanel header;
	ContentPanel content;
	public HomePage() {
			add(header = new HeaderPanel("header"));
			add(content = new ContentPanel("content"));
			add(new Link<Void>("link") {

				@Override
				public void onClick() {
					setResponsePage(SecondPage.class);
					
				}
				
			});
		}
}
