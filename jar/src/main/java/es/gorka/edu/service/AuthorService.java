package es.gorka.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.spring.injection.annot.SpringBean;

import es.gorka.edu.models.Author;
import es.gorka.edu.models.Book;
import es.gorka.edu.repository.AuthorRepository;

public class AuthorService {

	@SpringBean
	AuthorRepository repository;
	
	public boolean insertNewAuthor(Author author)
	{
		repository.insertNewAuthor(author);
		
		return true;
	}

	public ArrayList findAuthors(Author author) {
		return repository.findAuthor(author);
	}
}