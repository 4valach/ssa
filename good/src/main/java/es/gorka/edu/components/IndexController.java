package es.gorka.edu.components;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
	public String error(HttpServletResponse response) {
		try {
			response.sendRedirect("error.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error.html";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}