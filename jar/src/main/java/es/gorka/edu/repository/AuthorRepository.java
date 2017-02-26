package es.gorka.edu.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gorka.edu.connection.AbstractConnectionManager;
import es.gorka.edu.models.Author;

@Service
public class AuthorRepository {
	
	
	@Autowired
	private AbstractConnectionManager conManager;
	
	public void insertNewAuthor(Author author) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try 
		{
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("INSERT INTO AUTHOR(nameAuthor, dateOfBirth) " + "VALUES (?, ?)");
			preparedStatement.setString(1, author.getNameAuthor());
			preparedStatement.setDate(2, author.getDateBirth());
			preparedStatement.executeUpdate();
			conManager.close(preparedStatement);

		} catch (Exception e) {
			
		throw new RuntimeException(e);
		}

	}

	public ArrayList findAuthor(Author author) {
		ArrayList<Author> list = new ArrayList<Author>();
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM AUTHOR WHERE nameAuthor LIKE ? OR dateOfBirth = ?");
			preparedStatement.setString(1, "%" + author.getNameAuthor() + "%");
			preparedStatement.setDate(2, author.getDateBirth());
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Author author1 = new Author();
				author1.setNameAuthor(resultSet.getString(1));
				author1.setDateBirth(resultSet.getDate(2));
				list.add( author1);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}
