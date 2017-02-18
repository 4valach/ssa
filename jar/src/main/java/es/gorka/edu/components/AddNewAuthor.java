package es.gorka.edu.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;
import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.models.Author;
import es.gorka.edu.service.UserService;

@MountPath("home.html")
public class AddNewAuthor extends WebPage  {

	private static final Logger logger = LogManager.getLogger(AddNewAuthor.class.getName());

	


		Form form = new Form("formAddAuthor", new CompoundPropertyModel(new Author()));
		
		
			form.add(new Label("nameAthorLabel", getString("author.name")));
			form.add(new Label("dateBirthLabel", getString("date.birth")));
			form.add(new RequiredTextField("nameAuthor"));
			DatetimePicker datetimePicker = new DatetimePicker( "dateBirth","yyyy-MM-dd");
			form.add(datetimePicker)
			FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
			feedbackPanel.setOutputMarkupId(true);
			add(feedbackPanel);
			
			add(form);
		
		
		
		
	}


}
