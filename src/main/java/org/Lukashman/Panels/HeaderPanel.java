package org.Lukashman.Panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

	public HeaderPanel(String id) {
		super(id);
		add(new Label("head","Monster"));
	}

}
