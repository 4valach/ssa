package es.gorka.edu.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
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
import es.gorka.edu.service.AuthorService;


	

public class AddNewAuthor extends WebPage  {
	
	
	
	@SpringBean
	AuthorService authorService;

	@SpringBean
	Author author;

	private static final Logger logger = LogManager.getLogger(AddNewAuthor.class.getName());

	


	Form<Author> formAuthor = new Form<Author>("formAddNewAuthor", new CompoundPropertyModel<Author>(author)){

		private static final long serialVersionUID = 42L;

		@Override
		protected void onSubmit()
		{
			super.onSubmit();
			boolean isInserted = authorService.insertNewAuthor(getModelObject());
			FeedbackMessage message;
			
			if(isInserted)
			{
				message = new FeedbackMessage(this, "Autor insertado, se feliz", FeedbackMessage.INFO);	
			} 
			else
			{
				message = new FeedbackMessage(this, "No se pudo insertar, repasa que esta mal", FeedbackMessage.INFO);
			}
			
			getFeedbackMessages().add(message);
		}
	};
		
		
			
			formAuthor.add(new Label("nameAthorLabel", getString("author.name")));
			formAuthor.add(new Label("dateBirthLabel", getString("date.birth")));
			formAuthor.add(new RequiredTextField("nameAuthor"));
			DateTextField  datetimePicker = new DateTextField( "dateBirth","yyyy-MM-dd");
			formAuthor.add(datetimePicker)
			FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
			feedbackPanel.setOutputMarkupId(true);
			add(feedbackPanel);
			
			add(formAuthor);
		
		
		
		
	}


}
