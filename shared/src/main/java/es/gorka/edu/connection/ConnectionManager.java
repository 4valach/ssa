package es.gorka.edu.connection;

import java.sql.Connection;

public interface ConnectionManager {

	Connection open(String jdbcUrl);

	void close(Connection conn);

}
