package es.gorka.edu.service;

public interface IService<T> {

	public T newEntity();

	boolean insertNewEntityDto(T dto);

}
