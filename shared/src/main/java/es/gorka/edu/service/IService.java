package es.gorka.edu.service;

import es.gorka.edu.dto.UserDTO;

public interface IService<T> {

	public T newEntity();

	boolean insertNewUserDto(UserDTO userDto);

}
