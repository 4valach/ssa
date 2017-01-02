package es.gorka.edu.components;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.WicketWebApplication;
import es.gorka.edu.service.UserService;

/**
 * sample page to show mounting and spring integration
 *
 * @author Stefan Kloe
 *
 */
public class IndexPage extends WebPage {

    /* spring integration the wicket way */
    @SpringBean
	private UserService userService;

	public IndexPage() {
        add(new Label("title", "this is a mounted page"));
        add(new BookmarkablePageLink<String>("link", Homepage.class));
        /* showcase spring integration in wicket component */
		add(new Label("serviceText", userService.getText()));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        CssResourceReference cssResourceReference = new CssResourceReference(
                WicketWebApplication.class, "example.css");
        response.render(CssHeaderItem.forReference(cssResourceReference));
    }

}
