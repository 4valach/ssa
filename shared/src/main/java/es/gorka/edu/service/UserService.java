package es.gorka.edu.service;

import org.springframework.stereotype.Service;

import es.gorka.edu.dto.UserDTO;

@Service
public class UserService implements IService<UserDTO> {

	@Override
	public UserDTO newEntity() {
		return new UserDTO();
	}

}
