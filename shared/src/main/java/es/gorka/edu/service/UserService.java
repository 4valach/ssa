package es.gorka.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.gorka.edu.dto.UserDTO;
import es.gorka.edu.repository.UserRepository;

@Service
public class UserService implements IService<UserDTO> {

	@Autowired
	@Qualifier(value = "userService")
	UserRepository repository;
	
	@Override
	public UserDTO newEntity() {
		return new UserDTO();
	}

	public boolean insertNewEntityDto(UserDTO userDto) {
		repository.insert(userDto);
		return true;
	}

}
