package es.gorka.edu.components;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.resource.CssResourceReference;

import es.gorka.edu.WicketWebApplication;

/**
 * sample homepage
 *
 * @author Stefan Kloe
 *
 */
public class Homepage extends WebPage {

    public Homepage() {
		add(new Label("title", "Comprobar errores"));
		add(new BookmarkablePageLink<String>("link", IndexPage.class));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        CssResourceReference cssResourceReference = new CssResourceReference(
                WicketWebApplication.class, "example.css");
        response.render(CssHeaderItem.forReference(cssResourceReference));
    }
}
