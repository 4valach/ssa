package es.gorka.edu.components;

public interface CloseAble {

	
	default void close() {
		System.exit(0);
	}
}
