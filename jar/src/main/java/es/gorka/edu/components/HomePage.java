package es.gorka.edu.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.agilecoders.wicket.core.markup.html.bootstrap.heading.Heading;
import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.service.IService;

/**
 * sample page to show mounting and spring integration
 *
 * @author Stefan Kloe
 *
 */
public class HomePage extends BaseUserPage {

	private static final Logger logger = LogManager.getLogger(HomePage.class.getName());

	@SpringBean(name = "userService")
	private IService<UserDTO> userService;

	public HomePage() {
		add(new Heading("title", getString("title")));
		Form<UserDTO> form = new Form<UserDTO>("formLogin",
				new CompoundPropertyModel<UserDTO>(userService.newEntity())) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				PageParameters pageParameters = new PageParameters();
				setResponsePage(MainPage.class, pageParameters);
			}
		};
		fillUserForm(form);
		add(new BookmarkablePageLink<String>("instructionLink", Instruction.class));
		add(new BookmarkablePageLink<String>("signUpLink", SignUpPage.class));

		add(form);
	}

}
