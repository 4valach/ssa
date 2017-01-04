package es.gorka.edu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import es.gorka.edu.assembler.Assembler;
import es.gorka.edu.connection.AbstractConnectionManager;
import es.gorka.edu.dto.SnippetDTO;
import es.gorka.edu.model.Snippet;

public class SnippetServiceImpl implements SnippetRepository {

	private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class.getName());

	@Autowired
	private AbstractConnectionManager conManager;

	@Autowired
	private Assembler<SnippetDTO, Snippet> asesembler;

	@Override
	public void insert(SnippetDTO snippetDto) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Snippet snippet = new Snippet();
		asesembler.toDto(snippetDto, snippet);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("INSERT INTO SNIPPET(userName,text) " + "VALUES (?, ?)");
			preparedStatement.setString(1, snippet.getUserName());
			preparedStatement.setString(2, snippet.getText());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}

	}

	@Override
	public SnippetDTO selectById(SnippetDTO snippetDto) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Snippet user = new Snippet();
		user = asesembler.toEntity(snippetDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM SNIPPET WHERE username = ?");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.executeQuery();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return asesembler.toDto(new SnippetDTO(), user);
	}

	@Override
	public List<SnippetDTO> selectAll() {
		return null;
	}

	@Override
	public SnippetDTO delete(SnippetDTO snippetDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Snippet user = new Snippet();
		user = asesembler.toEntity(snippetDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM SNIPPET WHERE username = ?");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.executeQuery();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return asesembler.toDto(new SnippetDTO(), user);
	}

	@Override
	public SnippetDTO update(SnippetDTO snippetDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Snippet user = new Snippet();
		user = asesembler.toEntity(snippetDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("UPDATE Snippet SET text = ? WHERE username = ?");
			preparedStatement.setString(1, user.getText());
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.executeQuery();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return snippetDto;

	}

}
