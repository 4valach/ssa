package es.gorka.edu.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class AccessDeniedPage extends WebPage {

	public AccessDeniedPage() {
		add(new Label("messageErrorPanel", getString("access.denied")));
	}

}
