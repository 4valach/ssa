package es.gorka.edu;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebInitializer implements ServletContextInitializer {

    private static final String PARAM_APP_BEAN = "applicationBean";

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
		FilterRegistration filter = sc.addFilter("wicket-filter", "org.apache.wicket.protocol.http.WicketFilter");
        filter.setInitParameter("applicationFactoryClassName",
				"org.apache.wicket.spring.SpringWebApplicationFactory");
        filter.setInitParameter(PARAM_APP_BEAN, "wicketWebApplication");
		// This line is the only surprise when comparing to the equivalent
        // web.xml. Without some initialization seems to be missing.
		filter.setInitParameter("filterMappingUrlPattern", "/*");
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

}
