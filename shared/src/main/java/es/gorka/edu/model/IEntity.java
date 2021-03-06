package es.gorka.edu.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public interface IEntity<T> extends Serializable {

	public void setId(T t);

	public T getId();
	
	default public String toDebug() {
		return ReflectionToStringBuilder.toString(this);
	}

}
