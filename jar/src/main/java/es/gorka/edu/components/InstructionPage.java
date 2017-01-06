package es.gorka.edu.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class InstructionPage extends WebPage {

	public InstructionPage() {
		add(new BookmarkablePageLink<String>("homeLink", HomePage.class));
	}

}
