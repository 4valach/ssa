package es.gorka.edu.components;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapLink;
import es.gorka.edu.dto.SnippetDTO;
import es.gorka.edu.service.SnippetService;

public class BoardPage extends BaseUserPage implements CloseAble {

	@SpringBean(name = "snippetService")
	SnippetService service;

	private static final Logger logger = LogManager.getLogger(BoardPage.class.getName());

	public BoardPage() {
		add(new Label("title", getString("title")));
		add(new BookmarkablePageLink("mainPageLink", MainPage.class));
		Link link = new BootstrapLink<String>("closeApp", Model.of("intentar matar la aplicacion")) {
			@Override
			public void onClick() {
				close();
			}

		};
		add(link);
		addForm();
		addLitView();
		addFeedBackPanel();
	}

	private void addFeedBackPanel() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackMessage");
		add(feedbackPanel);
	}

	private void addLitView() {
		List<SnippetDTO> list = service.listAll();
		for (SnippetDTO object : list) {
			logger.debug(object.toDebug());
		}
		ListView listview = new ListView<SnippetDTO>("code-btn-group", list) {
			protected void populateItem(ListItem<SnippetDTO> item) {
				SnippetDTO modelObject = item.getModelObject();
				item.add(new Label("label", modelObject.getUserName()));
				item.add(new Label("text", modelObject.getText()));
			}
		};
		add(listview);
	}

	private void addForm() {
		Form<SnippetDTO> form = new Form<SnippetDTO>("formBoard",
				new CompoundPropertyModel<SnippetDTO>(service.newEntity())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				service.insertNewEntityDto(getModelObject());
				logger.debug("Snippet insertado");
				setResponsePage(BoardPage.class);
			}
		};
		form.add(new Label("labelForm", Model.of("Introduzca texto probando los bugs")));
		form.add(new TextField<String>("userName"));
		form.add(new TextArea<String>("text"));
		add(form);
	}
}
