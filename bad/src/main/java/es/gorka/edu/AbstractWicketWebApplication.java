package es.gorka.edu;


import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;

abstract class AbstractWicketWebApplication extends AuthenticatedWebApplication {

    @Override
    protected void init() {
        super.init();
    }

}
