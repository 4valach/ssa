package es.gorka.edu.components;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.service.UserService;

/**
 * sample page to show mounting and spring integration
 *
 * @author Stefan Kloe
 *
 */
public class HomePage extends BaseUserPage {

    @SpringBean
	private UserService userService;

	public HomePage() {
		add(new Label("title", getString("title")));
		Form form = new Form("formLogin", new CompoundPropertyModel(userService.newEntity()));
		fillUserForm(form);
		add(new BookmarkablePageLink<String>("instructionLink", Instruction.class));
		add(new BookmarkablePageLink<String>("signInLink", SignInPage.class));

		add(form);
    }

}
