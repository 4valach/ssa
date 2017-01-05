package es.gorka.edu.service;

import java.util.List;

public interface IService<T> {

	public T newEntity();

	boolean insertNewEntityDto(T dto);

	List<T> listAll();

	T search(T dto);

}
