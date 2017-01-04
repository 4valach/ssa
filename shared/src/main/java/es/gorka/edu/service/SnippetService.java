package es.gorka.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.gorka.edu.dto.SnippetDTO;
import es.gorka.edu.repository.SnippetRepository;

@Service
public class SnippetService implements IService<SnippetDTO> {

	@Autowired
	@Qualifier(value = "snippetService")
	SnippetRepository repository;

	@Override
	public SnippetDTO newEntity() {
		return new SnippetDTO();
	}

	@Override
	public boolean insertNewEntityDto(SnippetDTO snippetDTO) {
		repository.insert(snippetDTO);
		return true;
	}

}
