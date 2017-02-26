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
import es.gorka.edu.models.Book;
import es.gorka.edu.service.BookService;
import es.gorka.edu.service.UserService;

@MountPath("home.html")
public class AddNewBook extends WebPage  {

	
	@SpringBean
	BookService bookService;
	
	@SpringBean
	Book book;
	
	public AddNewBook() {
	Form<Book> formBook = new Form<Book>("formAddNewBook", new CompoundPropertyModel<Book>(book)){
	
	private static final long serialVersionUID = 42L;

		@Override
		protected void onSubmit()
		{
			super.onSubmit();
			boolean isInserted = bookService.insertNewBook(getModelObject());
			FeedbackMessage message;
			
			if(isInserted)
			{
				message = new FeedbackMessage(this, "libro insertado, se feliz", FeedbackMessage.INFO);	
			} 
			else
			{
				message = new FeedbackMessage(this, "No se pudo insertar, repasa que esta mal", FeedbackMessage.INFO);
			}
			
			getFeedbackMessages().add(message);
		}
	};
		
		formBook.add(new Label("nameBookLabel", getString("book.name")));
		formBook.add(new Label("nameAuthorLabel", getString("author.name")));
		formBook.add(new Label("ISBNLabel", getString("isbn.number")));

		formBook.add(new RequiredTextField("nameBook"));
		formBook.add(new RequiredTextField("nameAuthor"));
		formBook.add(new RequiredTextField("isbn"));

			
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
			
		add(formBook);
		
		
		
		
	}
}

