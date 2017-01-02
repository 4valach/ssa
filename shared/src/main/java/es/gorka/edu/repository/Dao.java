package es.gorka.edu.repository;

import java.util.List;

public interface Dao<T> {

	void createPersonTable();

	void insert(T person);

	T selectById(int id);

	List<T> selectAll();

	void delete(int id);

	void update(T entity, int id);
}
