package es.gorka.edu.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.service.UserService;

public class SignInPage extends BaseUserPage {

	@SpringBean
	private UserService userService;

	public SignInPage() {
		add(new Label("title", getString("title")));
		Form form = new Form<UserDTO>("formLogin", new CompoundPropertyModel(userService.newEntity())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				submitNewUser(getModelObject());
			}
		};
		fillUserForm(form);

		add(form);

		add(new BookmarkablePageLink<String>("homeLink", HomePage.class));
	}

	private void submitNewUser(UserDTO userDto) {
		userService.insertNewUserDto(userDto);
	}


}
