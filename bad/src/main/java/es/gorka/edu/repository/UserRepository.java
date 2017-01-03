package es.gorka.edu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import es.gorka.edu.assembler.Assembler;
import es.gorka.edu.connection.AbstractConnectionManager;
import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.model.User;

public class UserRepository implements Dao<UserDTO> {

	private static final Logger logger = LogManager.getLogger(UserRepository.class.getName());

	@Autowired
	private AbstractConnectionManager conManager;

	@Autowired
	private Assembler<UserDTO, User> asesembler;

	@Override
	public void insert(UserDTO userDto) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		User user = new User();
		asesembler.toDto(userDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection
					.prepareStatement("INSERT INTO user (user,password)" + "VALUES (?, ?)");
			preparedStatement.setString(1, user.getUser());
			preparedStatement.setString(2, user.getPassword());
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
	public UserDTO selectById(UserDTO userDto) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		User user = new User();
		user = asesembler.toEntity(userDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name = ?");
			preparedStatement.setString(1, user.getUser());
			preparedStatement.executeQuery();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return asesembler.toDto(new UserDTO(), user);
	}

	@Override
	public List<UserDTO> selectAll() {
		return null;
	}

	@Override
	public UserDTO delete(UserDTO userDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		User user = new User();
		user = asesembler.toEntity(userDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name = ?");
			preparedStatement.setString(1, user.getUser());
			preparedStatement.executeQuery();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return asesembler.toDto(new UserDTO(), user);
	}

	@Override
	public UserDTO update(UserDTO userDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		User user = new User();
		user = asesembler.toEntity(userDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("UPDATE user SET password = ? WHERE name = ?");
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getUser());
			preparedStatement.executeQuery();

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return userDto;

	}

}
