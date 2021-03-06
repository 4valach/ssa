package es.gorka.edu.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gorka.edu.connection.AbstractConnectionManager;
import es.gorka.edu.models.Author;
import es.gorka.edu.models.Book;

@Service
public class BookRepository {
	
	
	@Autowired
	private AbstractConnectionManager conManager;
	
	public void insertNewBook(Book book) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try 
		{
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("INSERT INTO BOOk(nameBook, ISBN, nameAuthor) " + "VALUES (?, ?, ?)");
			preparedStatement.setString(1, book.getNameBook());
			preparedStatement.setString(2, book.getIsbn());
			preparedStatement.setString(3, book.getNameAuthor());
			preparedStatement.executeUpdate();
			conManager.close(preparedStatement);

		} catch (Exception e) {
			
		throw new RuntimeException(e);
		}

	}


	public ArrayList findBooks(Book book) {
		ArrayList list = new ArrayList();
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM BOOK WHERE nameBook LIKE ? OR ISBN LIKE ? OR nameAuthor LIKE ?");
			preparedStatement.setString(1, "%" + book.getNameBook() + "%");
			preparedStatement.setString(3, "%" + book.getNameAuthor() + "%");
			preparedStatement.setString(2, "%" + book.getIsbn() + "%");
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Book book1 = new Book();
				book1.setNameBook(resultSet.getString(1));
				book1.setIsbn(resultSet.getString(2));
				book1.setNameAuthor(resultSet.getString(3));
				list.add(book1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}

