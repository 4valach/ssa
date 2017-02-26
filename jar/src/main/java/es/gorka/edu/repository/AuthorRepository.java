package es.gorka.edu.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
		// TODO Auto-generated method stub
		return null;
	}

}
