package domain.template.callback;

import domain.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface queryTemplateCallback {
    ArrayList<Todo> callback(Statement st, ResultSet rs) throws SQLException;
}
