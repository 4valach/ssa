package es.gorka.edu.model;

import java.io.Serializable;

public interface IEntity<T> extends Serializable {

	public void setId(T t);

	public T getId();
	
}
