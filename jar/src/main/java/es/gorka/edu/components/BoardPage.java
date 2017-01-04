package es.gorka.edu.components;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.dto.SnippetDTO;
import es.gorka.edu.service.IService;

public class BoardPage extends BaseUserPage {

	@SpringBean(name = "snippetService")
	IService<SnippetDTO> service;

	private static final Logger logger = LogManager.getLogger(HomePage.class.getName());

	public BoardPage() {
		add(new BookmarkablePageLink("mainPageLink", MainPage.class));
		Form<SnippetDTO> form = new Form<SnippetDTO>("formBoard",
				new CompoundPropertyModel<SnippetDTO>(service.newEntity()));
		List list = Arrays.asList(new String[] { "a", "b", "c" });

		ListView listview = new ListView("code-btn-group", list) {
			protected void populateItem(ListItem item) {
				item.add(new Label("label", item.getModel()));
			}
		};
		add(listview);
		// TODO Auto-generated constructor stub
	}
}
