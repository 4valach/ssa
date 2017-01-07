package es.gorka.edu.components;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import es.gorka.edu.dto.SnippetDTO;

abstract class AbstractBoardPage extends BaseAuthWebPage {

	protected void addListViewFromList(List<SnippetDTO> list) {
		ListView listview = new ListView<SnippetDTO>("code-btn-group", list) {
			protected void populateItem(ListItem<SnippetDTO> item) {
				SnippetDTO modelObject = item.getModelObject();
				item.add(new Label("label", modelObject.getUserName()));
				Label label = new Label("text", modelObject.getText());
				label.setEscapeModelStrings(Xss.bug);
				item.add(label);
			}
		};
		add(listview);
	}

	protected boolean validModel(SnippetDTO modelObject) {
		String userName = modelObject.getUserName();
		try {
			Short.parseShort(userName);
		} catch (Exception e) {
			error("campo numérico muy grande");
			return false;
		}
		return true;

	}
}
