package es.gorka.edu.service;

import java.util.ArrayList;
import java.util.List;

import es.gorka.edu.models.Author;
import es.gorka.edu.models.Book;

public class BookService {

	public List searchAll(Book book) {
		List list = new ArrayList();
		
		if (book.getNameBook()!=null)
		{
		Book bookTest1 = new Book();
		bookTest1.setNameBook("primero");
		
		Book bookTest2 = new Book();
		bookTest2.setNameBook("segundo libro");
		
		Book bookTest3 = new Book();
		bookTest3.setNameBook("tercer libro");
		
		Book bookTest4 = new Book();
		bookTest4.setNameBook("cuarto libro");
		
		
		list.add(bookTest1);
		list.add(bookTest2);
		list.add(bookTest3);
		list.add(bookTest4);
		}
	

		return list;
	}

}
