package es.gorka.edu.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class InternalErrorPage extends WebPage {

	public InternalErrorPage() {
		add(new Label("messageErrorPanel", getString("page.not.found")));
	}

}
