package es.gorka.edu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gorka.edu.model.User;
import es.gorka.edu.repository.Dao;

public class UserRepository implements Dao<User> {

	private static final Logger logger = LogManager.getLogger(UserRepository.class.getName());

	@Override
	public void createPersonTable() {

	}

	@Override
	public void insert(User person) {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = UtilConnection.getConnection();
			preparedStatement = connection
					.prepareStatement("INSERT INTO person (user,password)" + "VALUES (?, ?)");
			preparedStatement.setString(1, person.getLoginName());
			preparedStatement.setString(2, person.getPassword());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public User selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User entity, int id) {
		// TODO Auto-generated method stub

	}

}
