package domain.template.callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface updateTemplateCallback {
    PreparedStatement callback(Connection conn) throws SQLException;
}
