package es.gorka.edu.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.pages.PageExpiredErrorPage;

public class ExpiredPage extends PageExpiredErrorPage {

	public ExpiredPage() {
		add(new Label("messageErrorPanel", getString("session.expired")));
		getSession().invalidate();
		Page homePage = null;
		try {
			homePage = getApplication().getHomePage().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		redirectToInterceptPage(homePage);
	}

}
