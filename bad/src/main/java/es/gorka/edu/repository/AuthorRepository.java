package es.gorka.edu.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import es.gorka.edu.assembler.Assembler;
import es.gorka.edu.connection.AbstractConnectionManager;
import es.gorka.edu.dto.AuthorDTO;
import es.gorka.edu.dto.BookDTO;
import es.gorka.edu.model.Author;
import es.gorka.edu.model.Book;
import es.gorka.edu.util.UtilConnection;

public class AuthorRepository implements Dao<AuthorDTO> {

	@Autowired
	private Assembler<AuthorDTO, Author> asesemblerAuthor;

	@Autowired
	private Assembler<BookDTO, Book> asesemblerBook;

	private static final Logger logger = LogManager.getLogger(AuthorRepository.class.getName());

	private AbstractConnectionManager conManager;

	public void insert(AuthorDTO author) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = conManager.open();
			preparedStatement = connection
					.prepareStatement("INSERT INTO AUTHOR (name,book) VALUES (?, ?)");
			preparedStatement.setString(1, author.getName());

			List<BookDTO> books = author.getBooks();
			for (BookDTO bookDto : books) {
				Book book = new Book();
				book = asesemblerBook.toEntity(bookDto, book);
				preparedStatement.setLong(2, book.getId());
				preparedStatement.executeUpdate();
			}

			logger.debug("INSERT INTO AUTHOR (name,book) VALUES (?, ?)");

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
	}

	public AuthorDTO selectById(AuthorDTO authorDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Author author = new Author();

		asesemblerAuthor.toEntity(authorDTO, author);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id = ?");
			preparedStatement.setLong(1, author.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				author.setId(resultSet.getLong("id"));
				author.setName(resultSet.getString("name"));
				Book book = new Book();
				book.setId(resultSet.getLong("book"));
				ArrayList<Book> listBooks = new ArrayList<>();
				listBooks.add(book);
				author.setBooks(listBooks);
			}
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(resultSet);
			conManager.close(preparedStatement);
			conManager.close(connection);
		}

		return asesemblerAuthor.toDto(authorDTO, author);
	}

	public List<AuthorDTO> selectAll() {
		List<AuthorDTO> authors = new ArrayList<AuthorDTO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = UtilConnection.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM author");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Author author = new Author();
				author.setId(resultSet.getLong("id"));
				author.setName(resultSet.getString("name"));
				Book book = new Book();
				book.setId(resultSet.getLong("book"));
				ArrayList<Book> listBooks = new ArrayList<>();
				listBooks.add(book);
				author.setBooks(listBooks);
				authors.add(asesemblerAuthor.toDto(new AuthorDTO(), author));
			}

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(resultSet);
			conManager.close(preparedStatement);
			conManager.close(connection);
		}

		return authors;
	}

	public AuthorDTO delete(AuthorDTO authorDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = UtilConnection.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
			preparedStatement.setLong(1, authorDTO.getId());
			preparedStatement.executeUpdate();

			logger.debug("DELETE FROM person WHERE id = ?");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return authorDTO;
	}

	public AuthorDTO update(AuthorDTO authorDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("UPDATE author SET " + "name = ? WHERE id = ?");

			preparedStatement.setString(1, authorDto.getName());
			preparedStatement.setLong(2, authorDto.getId());
			preparedStatement.executeUpdate();

			logger.debug("UPDATE author SET " + "name = ? WHERE id = ?");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return authorDto;
	}

	public AbstractConnectionManager getConManager() {
		return conManager;
	}

	public void setConManager(AbstractConnectionManager conManager) {
		this.conManager = conManager;
	}
}
