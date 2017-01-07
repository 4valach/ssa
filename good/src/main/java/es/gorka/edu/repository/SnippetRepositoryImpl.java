package es.gorka.edu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.gorka.edu.assembler.Assembler;
import es.gorka.edu.connection.AbstractConnectionManager;
import es.gorka.edu.dto.SnippetDTO;
import es.gorka.edu.model.Book;
import es.gorka.edu.model.Snippet;

@Component
public class SnippetRepositoryImpl implements SnippetRepository {

	private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class.getName());

	@Autowired
	private AbstractConnectionManager conManager;

	@Autowired
	private Assembler<SnippetDTO, Snippet> asesembler;

	@Override
	public void insert(SnippetDTO snippetDto) {
		logger.debug(snippetDto.toDebug());
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Snippet snippet = new Snippet();
		asesembler.toEntity(snippetDto, snippet);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("INSERT INTO snippet(username,text) " + "VALUES (?, ?)");

			preparedStatement.setString(1, snippetDto.getUserName());
			preparedStatement.setString(2, snippetDto.getText());
			preparedStatement.execute();

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
			preparedStatement = connection.prepareStatement("SELECT username FROM snippet WHERE username = ?");
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
		List<SnippetDTO> snippets = new ArrayList<SnippetDTO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT username,text FROM snippet");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Snippet snippet = new Snippet();
				snippet.setUserName(resultSet.getString(1));
				snippet.setText(resultSet.getString(2));
				Book book = new Book();
				snippets.add(asesembler.toDto(new SnippetDTO(), snippet));
			}

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(resultSet);
			conManager.close(preparedStatement);
			conManager.close(connection);
		}

		return snippets;
	}

	@Override
	public SnippetDTO delete(SnippetDTO snippetDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Snippet user = new Snippet();
		user = asesembler.toEntity(snippetDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("DELETE FROM snippet WHERE username = ?");
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
			preparedStatement = connection.prepareStatement("UPDATE snippet SET text = ? WHERE username = ?");
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

	@Override
	public SnippetDTO selectOneByEntity(SnippetDTO snippetDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Snippet snippet = new Snippet();
		snippet = asesembler.toEntity(snippetDto, snippet);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM snippet WHERE username = ? or text = ?");
			preparedStatement.setString(1, snippet.getUserName());
			preparedStatement.setString(2, snippet.getText());
			preparedStatement.executeQuery();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return asesembler.toDto(new SnippetDTO(), snippet);
	}

	@Override
	public List<SnippetDTO> selectAnyByEntity(SnippetDTO snippetDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<SnippetDTO> list = new ArrayList<>();
		Snippet snippet = new Snippet();
		snippet = asesembler.toEntity(snippetDto, snippet);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM snippet WHERE username = ? or text = ?");
			preparedStatement.setString(1, snippet.getUserName());
			preparedStatement.setString(2, snippet.getText());
			preparedStatement.executeQuery();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		list.add(asesembler.toDto(new SnippetDTO(), snippet));
		return list;
	}

}
