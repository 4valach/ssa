package es.gorka.edu.repository;

import java.util.List;

public interface Dao<T> {

	void insert(T entity);

	T selectById(T t);

	T selectOneByEntity(T t);

	List<T> selectAnyByEntity(T t);

	List<T> selectAll();

	T delete(T t);

	T update(T entity);
}
