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
import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.model.User;

@Component
public class UserRepositoryImpl implements UserRepository {

	private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class.getName());

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
			preparedStatement = connection.prepareStatement("INSERT INTO user(name,password) " + "VALUES (?, ?)");
			preparedStatement.setString(1, user.getName());
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
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name = ?");
			preparedStatement.setString(1, userDto.getName());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
			}

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(resultSet);
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return asesembler.toDto(new UserDTO(), user);
	}

	@Override
	public List<UserDTO> selectAll() {
		List<UserDTO> users = new ArrayList<UserDTO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM user");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
				users.add(asesembler.toDto(new UserDTO(), user));
			}

		} catch (Exception e) {
			logger.error(e);
		} finally {
			conManager.close(resultSet);
			conManager.close(preparedStatement);
			conManager.close(connection);
		}

		return users;
	}

	@Override
	public UserDTO delete(UserDTO userDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = new User();
		user = asesembler.toEntity(userDto, user);
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name = ?");
			preparedStatement.setString(1, user.getName());
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
			preparedStatement.setString(2, user.getName());
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

	public boolean existUser(UserDTO userDto) {
		UserDTO selectOneByEntity = selectOneByEntity(userDto);
		return null != selectOneByEntity.getName();

	}

	@Override
	public UserDTO selectOneByEntity(UserDTO userDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = new User();
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name = ?");
			preparedStatement.setString(1, userDto.getName());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
			}

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
	public List<UserDTO> selectAnyByEntity(UserDTO userDto) {
		Connection connection = null;
		ResultSet resultSet = null;
		List<UserDTO> list = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		try {
			connection = conManager.open();
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name = ?");
			preparedStatement.setString(1, userDto.getName());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setName(resultSet.getString("name"));
				list.add(asesembler.toDto(new UserDTO(), user));
			}

		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
		return list;
	}

	@Override
	public List<UserDTO> searchAll(UserDTO userDto) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<UserDTO> list = new ArrayList<>();
		try {
			connection = conManager.open();
			String sql = "SELECT name FROM USER WHERE name = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userDto.getName());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserDTO userDtoTemp = new UserDTO();
				userDtoTemp.setName(resultSet.getString("name"));
				list.add(userDtoTemp);
			}
			return list;
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		} finally {
			conManager.close(preparedStatement);
			conManager.close(connection);
		}
	}

}
