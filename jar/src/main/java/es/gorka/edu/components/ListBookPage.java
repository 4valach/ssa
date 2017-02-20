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
	
	private static final long serialVersionUID = -1935854748907274886L;

	@SpringBean
	BookService service;

	private static final Logger logger = LogManager.getLogger(ListBookPage.class.getName());

	private String nameBookActual = null;

	private List listBook = Collections.emptyList();

	public ListBookPage(PageParameters parameters) {
		nameBookActual = parameters.get("currentSearchTerm").toString();
		logger.debug("Cargando la pagina con el parametro " + nameBookActual);
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
				pageParameters.add("currentSearchTerm", ((Book) getModelObject()).getNameBook());
				setResponsePage(ListBookPage.class, pageParameters);
			}
		};
		form.add(new TextField("nameBook"));
		add(form);
	}
	
	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}


	private void addListAuthorView() {
		Book book = new Book();// service.newEntity()
		book.setNameAuthor(nameBookActual);
		listBook = service.searchAll(book);
		ListView listview = new ListView("author-group", listBook) {
			@Override
			protected void populateItem(ListItem item) {
				Book book = (Book) item.getModelObject();
				item.add(new Label("nameBook", book.getNameBook()));
			}
		};
		add(listview);
	}


	
}