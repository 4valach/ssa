package es.gorka.edu.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.service.UserService;

public class SignIn extends BaseUserPage {

	@SpringBean
	private UserService userService;

	public SignIn() {
		add(new Label("title", getString("title")));
		Form form = new Form("formLogin", new CompoundPropertyModel(userService.newEntity()));
		fillUserForm(form);

		add(form);
	}


}
