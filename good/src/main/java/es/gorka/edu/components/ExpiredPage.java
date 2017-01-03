package es.gorka.edu.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.pages.PageExpiredErrorPage;

public class ExpiredPage extends PageExpiredErrorPage {

	public ExpiredPage() {
		add(new Label("messageErrorPanel", getString("session.expired")));
		getSession().invalidate();
		Class newInstance = getApplication().getHomePage().getClass().newInstance();
		redirectToInterceptPage(newInstance);
	}

}
