package dbconnector;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class h2Connection implements dbConnection{
    @Override
    public Connection connectionMake() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/workspace/tools/h2/base/dopey",
                "dev", "1234");

        return conn;
    }
}
