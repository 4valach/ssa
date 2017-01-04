package es.gorka.edu.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.service.IService;

public class SignUpPage extends BaseUserPage {

	private static final Logger logger = LogManager.getLogger(SignUpPage.class.getName());

	@SpringBean(name = "userService")
	private IService userService;

	public SignUpPage() {
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
		if (userService.insertNewEntityDto(userDto)) {
			logger.debug("El usuario se ha guardado correctamente");
			getFeedbackMessages().add(this, "El usuario se ha guardado correctamente", FeedbackMessage.INFO);
		}
	}


}
