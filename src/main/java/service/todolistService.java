package service;

import dbconnector.dbConnection;
import dbconnector.h2Connection;
import entity.Todo;

import java.sql.*;
import java.util.ArrayList;

public class todolistService {
    private dbConnection dbconn;

    todolistService(dbConnection dbconn) {
        this.dbconn = dbconn;
    }

    public ArrayList<Todo> allToDoList() {
        ArrayList<Todo> list = new ArrayList<>();

        try {
            String sql = "select * from todo";

            Connection conn = this.dbconn.connectionMake();

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Todo todo = new Todo(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("status"));

                list.add(todo);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int addTodo(String title, String description) {
        int result = 0;
        try {
            String sql = "insert into todo (title, description) values (?, ?)";

            Connection conn = this.dbconn.connectionMake();

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, description);

            result = pst.executeUpdate();

            pst.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int removeTodo(int id) {
        int result = 0;
        try {
            String sql = "delete from todo where id=?";

            Connection conn = this.dbconn.connectionMake();

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            result = pst.executeUpdate();

            pst.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int setStatus(int id, int status) {
        int result = 0;
        try {
            String sql = "update todo set status=? where id=?";

            Connection conn = this.dbconn.connectionMake();

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setInt(2, id);

            result = pst.executeUpdate();

            pst.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
