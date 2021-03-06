package service;

import dbconnector.dbConnection;
import entity.Todo;
import service.template.callback.queryTemplateCallback;
import service.template.callback.updateTemplateCallback;
import service.template.template;

import java.sql.*;
import java.util.ArrayList;

public class todolistService {
    private final template tem;

    public todolistService(dbConnection conn) {
        this.tem = new template(conn);
    }

    public ArrayList<Todo> allToDoList() {
        return this.tem.queryTemplate(new queryTemplateCallback() {
            @Override
            public ArrayList<Todo> callback(Statement st, ResultSet rs) throws SQLException {
                ArrayList<Todo> list = new ArrayList<>();
                String sql = "select * from todo";

                rs = st.executeQuery(sql);

                while (rs.next()) {
                    Todo todo = new Todo(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getInt("status"));

                    list.add(todo);
                }
                return list;
            }
        });
    }

    public int addTodo(String title, String description) {
        return this.tem.updateTemplate(new updateTemplateCallback() {
            @Override
            public PreparedStatement callback(Connection conn) throws SQLException {
                String sql = "insert into todo (title, description) values (?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, title);
                pst.setString(2, description);
                return pst;
            }
        });
    }

    public int removeTodo(int id) {
        return this.tem.updateTemplate(new updateTemplateCallback() {
            @Override
            public PreparedStatement callback(Connection conn) throws SQLException {
                String sql = "delete from todo where id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, id);
                return pst;
            }
        });
    }

    public int setStatus(int id, int status) {
        return this.tem.updateTemplate(new updateTemplateCallback() {
            @Override
            public PreparedStatement callback(Connection conn) throws SQLException {
                String sql = "update todo set status=? where id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, id);
                return pst;
            }
        });
    }
}
