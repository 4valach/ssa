package es.gorka.edu.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * sample homepage
 *
 * @author Stefan Kloe
 *
 */
public class MainPage extends WebPage {

    public MainPage() {
		add(new Label("title", "Listado de errores"));
		add(new BookmarkablePageLink<String>("linkHome", HomePage.class));
    }

}
