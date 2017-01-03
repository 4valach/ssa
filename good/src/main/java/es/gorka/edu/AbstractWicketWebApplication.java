package es.gorka.edu;


import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.pages.AccessDeniedPage;
import org.apache.wicket.markup.html.pages.InternalErrorPage;
import org.apache.wicket.util.time.Duration;

import es.gorka.edu.components.ExpiredPage;

/**
 * The web application class also serves as spring boot starting point by using
 * spring boot's EnableAutoConfiguration annotation and providing the main
 * method.
 *
 */
abstract class AbstractWicketWebApplication extends AuthenticatedWebApplication {


    @Override
    protected void init() {
        super.init();
		getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
		getApplicationSettings().setPageExpiredErrorPage(ExpiredPage.class);
		getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);
		getApplicationSettings().setInternalErrorPage(InternalErrorPage.class);

    }


}
