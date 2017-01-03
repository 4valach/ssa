package es.gorka.edu.dto;

import java.util.Date;

public class BookDTO implements IEntityDto {

	private String title;
	private String isbn;
	private Integer yearEdition;
	private Date creationDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getYearEdition() {
		return yearEdition;
	}

	public void setYearEdition(Integer yearEdition) {
		this.yearEdition = yearEdition;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
