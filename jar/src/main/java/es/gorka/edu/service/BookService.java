package es.gorka.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gorka.edu.models.Author;
import es.gorka.edu.models.Book;
import es.gorka.edu.repository.BookRepository;

@Service
public class BookService {
		@Autowired
		BookRepository repository;
		
		public boolean insertNewBook(Book book) {
			repository.insertNewBook(book);
			return true;
		}
		
		public ArrayList findBooks(Book book) {
			return repository.findBooks(book);
		}
		
	

}