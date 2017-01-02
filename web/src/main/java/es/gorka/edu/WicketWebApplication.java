package es.gorka.edu;


import org.apache.catalina.startup.Bootstrap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import es.gorka.edu.components.Homepage;
import es.gorka.edu.components.IndexPage;
import es.gorka.edu.service.UserService;

/**
 * The web application class also serves as spring boot starting point by using
 * spring boot's EnableAutoConfiguration annotation and providing the main
 * method.
 *
 */
@Component
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class WicketWebApplication extends WebApplication {

	private static final Logger logger = LogManager.getLogger(WicketWebApplication.class.getName());


    @Autowired
	private UserService userService;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * spring boot main method to build context
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(WicketWebApplication.class, args);

    }

    /**
     * provides page for default request
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return Homepage.class;
    }

    /**
     * <ul>
     * <li>making the wicket components injectable by activating the
     * SpringComponentInjector</li>
     * <li>mounting the test page</li>
     * <li>logging spring service method output to showcase working
     * integration</li>
     * </ul>
     */
    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this, applicationContext));
		mountPage("/index.html", IndexPage.class);
		Bootstrap.install(this);
		logger.info("initializated webpage");
    }

}
