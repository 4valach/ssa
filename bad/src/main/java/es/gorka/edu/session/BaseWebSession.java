package es.gorka.edu.session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.types.Resource;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

abstract class BaseWebSession extends AuthenticatedWebSession {

	private static final Logger logger = LogManager.getLogger(BaseWebSession.class.getName());

	public BaseWebSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(final String username, final String password) {
		final String WICKET = "user";
		boolean valid = false;
		logger.debug("probando si es valido el usuario: "+username +" password: " +password);
		String userResource = new Resource("user.name.key").getDescription();
		String passResource = new Resource("user.pass.key").getDescription();
		logger.debug("userResource: "+userResource);
		logger.debug("passResource: "+passResource);
		valid = (WICKET.equals(username) && WICKET.equals(password))
				|| (userResource.equals(username) && passResource.equals(password));

		return valid;
	}

	@Override
	public Roles getRoles() {
		if (isSignedIn()) {

			// assing the role ( modify the code to add dynamic roles from
			// database)
			return new Roles(Roles.USER);
		}
		return null;
	}
}
