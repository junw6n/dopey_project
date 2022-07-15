package dbconnector;

import java.sql.Connection;
import java.sql.SQLException;

public interface dbConnection {
    Connection connectionMake() throws ClassNotFoundException, SQLException;
}
