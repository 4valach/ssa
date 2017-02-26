package es.gorka.edu.components;


import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.models.Author;
import es.gorka.edu.models.Book;
import es.gorka.edu.service.AuthorService;
import es.gorka.edu.service.BookService;
import es.gorka.edu.service.UserService;

public class ListBookPage extends WebPage {
	
	private static final long serialVersionUID = 42L;

	@SpringBean
	BookService service;
	
	
	private static final Logger logger = LogManager.getLogger(ListBookPage.class.getName());

	private String title = null;
	private String author = null;
	private String isbn = null;
	
	
	private List listBook = Collections.emptyList();

	public ListBookPage(PageParameters parameters) {
		title = parameters.get("title").toString();
		author = parameters.get("author").toString();
		isbn = parameters.get("isbn").toString();
		
		initComponents();
	}


	public ListBookPage() {
		initComponents();
	}


	private void initComponents() {
		addForm();
		addFeedBackPanel();
		addListAuthorView();
	}

	private void addForm() {
		Form form = new Form("formListBook", new CompoundPropertyModel(new Book())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				listBook.clear();
				PageParameters pageParameters = new PageParameters();
				if(((Book) getModelObject()).getNameBook() != null)
					pageParameters.add("title", ((Book) getModelObject()).getNameBook());
				
				if(((Book) getModelObject()).getIsbn() != null)
					pageParameters.add("isbn", ((Book) getModelObject()).getIsbn());
				
				if(((Book) getModelObject()).getNameAuthor() != null)
					pageParameters.add("author", ((Book) getModelObject()).getNameAuthor());
				
				setResponsePage(ListBookPage.class, pageParameters);
			}
		};
		form.add(new TextField("nameBook"));
		form.add(new TextField("ISBN"));
		form.add(new TextField("nameAuthor"));
		add(form);
	}
	
	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}


	private void addListAuthorView() {
		Book book = new Book();
		if(title != null)
			book.setNameBook(title);
		if(isbn != null)
			book.setIsbn(isbn);
		if(author != null)
			book.setNameAuthor(author);
		listBook = service.findBooks(book);
		ListView<Book> listview = new ListView<Book>("book-group", listBook) {
			
			private static final long serialVersionUID = 42L;

			@Override
			protected void populateItem(ListItem<Book> item) {
				Book book = item.getModelObject();
				item.add(new Label("title", book.getNameBook()));
				item.add(new Label("isbn", book.getIsbn()));
				item.add(new Label("author", book.getNameAuthor()));
			}

		};
		add(listview);
	}


	
}