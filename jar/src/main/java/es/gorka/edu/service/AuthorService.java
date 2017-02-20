package es.gorka.edu.service;

import java.util.ArrayList;
import java.util.List;

import es.gorka.edu.models.Author;

public class AuthorService {

	public List searchAll(Author author) {
		List list = new ArrayList();
		
		if (author.getNameAuthor()!=null)
		{
		Author authorTest1 = new Author();
		authorTest1.setNameAuthor("primero");
		
		Author authorTest2 = new Author();
		authorTest2.setNameAuthor("segundo");
		
		Author authorTest3 = new Author();
		authorTest3.setNameAuthor("tercero");
		
		Author authorTest4 = new Author();
		authorTest4.setNameAuthor("cuarto");
		
		list.add(authorTest1);
		list.add(authorTest2);
		list.add(authorTest3);
		list.add(authorTest4);
		}
	

		return list;
	}

}