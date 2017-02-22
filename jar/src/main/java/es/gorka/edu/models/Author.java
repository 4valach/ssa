package es.gorka.edu.models;

import java.sql.Date;

public class Author {
	private String nameAuthor;
	private Date dateBirth;
	
	
	public String getNameAuthor() {
		return nameAuthor;
	}
	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	


}
