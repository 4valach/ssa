package es.gorka.edu.dto;

import java.util.List;

public class AuthorDTO implements IEntityDto {

	private Long id;
	private String name;
	private List<BookDTO> books;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(List<BookDTO> books) {
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
