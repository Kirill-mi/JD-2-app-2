package by.kirill.pympproject.dao.connection;

import java.sql.SQLException;

public class ConnectionPoolException extends Exception {

    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionPoolException(SQLException e) {
        super(e);
    }
}
