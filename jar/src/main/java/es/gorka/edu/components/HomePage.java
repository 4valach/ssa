package es.gorka.edu.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.*;
import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;
import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.service.UserService;


public class HomePage extends WebPage {

	public HomePage()
	{
		
		BookmarkablePageLink NewAuthorPageLink = new BookmarkablePageLink("linkAddAuthor",AddNewAuthor.class);
		BookmarkablePageLink NewBookPageLink = new BookmarkablePageLink("linkAddBook",AddNewBook.class);
		BookmarkablePageLink ListAuthorPageLink = new BookmarkablePageLink("linkListAuthor",ListAuthorPage.class);
		BookmarkablePageLink ListBookPageLink = new BookmarkablePageLink("linkListBook",ListBookPage.class);

		add(ListAuthorPageLink);
		add(ListBookPageLink);
		add(NewBookPageLink);
		add(NewAuthorPageLink);
	}


}