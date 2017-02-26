package es.gorka.edu.components;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
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
import es.gorka.edu.service.AuthorService;
import es.gorka.edu.service.UserService;

public class ListAuthorPage extends WebPage {
	
	private static final long serialVersionUID = -1935854748907274886L;

	@SpringBean
	AuthorService service;

	private static final Logger logger = LogManager.getLogger(ListAuthorPage.class.getName());

	private String nameAuthorActual = null;

	private List listAuthor = Collections.emptyList();
	private String date = null;

	public ListAuthorPage(PageParameters parameters) {
		nameAuthorActual = parameters.get("currentSearchTerm").toString();
		date = parameters.get("date").toString();
		initComponents();
	}


	public ListAuthorPage() {
		initComponents();
	}


	private void initComponents() {
		addForm();
		addFeedBackPanel();
		addListAuthorView();
	}

	private void addForm() {
		Form form = new Form("formListAuthor", new CompoundPropertyModel(new Author())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				listAuthor.clear();
				PageParameters pageParameters = new PageParameters();
				if(((Author) getModelObject()).getNameAuthor() != null)
				pageParameters.add("authorName", ((Author) getModelObject()).getNameAuthor());
				
				if(((Author) getModelObject()).getDateBirth() != null)
				pageParameters.add("date", ((Author) getModelObject()).getDateBirth());
				
				pageParameters.add("currentSearchTerm", ((Author) getModelObject()).getNameAuthor());
				setResponsePage(ListAuthorPage.class, pageParameters);
			}
		};
		form.add(new TextField("nameAuthor"));
		DateTextField  datetimePicker = new DateTextField ("dateOfBirth", "yyyy-MM-dd");
		form.add(datetimePicker);
		add(form);
	}
	
	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}


	private void addListAuthorView() {
		Author author = new Author();
		author.setNameAuthor(nameAuthorActual);
		
		if(nameAuthorActual != null)
		author.setNameAuthor(nameAuthorActual);
		
		if(date != null)
		{
		java.sql.Date sqlDate = java.sql.Date.valueOf(date);
		author.setDateBirth(sqlDate);
		}
		
		
		
		ListView listview = new ListView("author-group", listAuthor) {
			@Override
			protected void populateItem(ListItem item) {
				Author author = (Author) item.getModelObject();
				item.add(new Label("authorName", author.getNameAuthor()));
				item.add(new Label("dateOfBirth", author.getDateBirth()));
			}
		};
		add(listview);
	}


	
}