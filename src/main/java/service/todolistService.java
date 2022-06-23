package service;

import entity.Todo;

import java.sql.*;
import java.util.ArrayList;

public class todolistService {
    // allToDoList
    // addToDo
    // removeToDo
    // modifyToDo
    public ArrayList<Todo> allToDoList() {
        ArrayList<Todo> list = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/workspace/tools/h2/base/dopey",
                    "dev", "1234");

            String sql = "select * from todo";

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
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/workspace/tools/h2/base/dopey",
                    "dev", "1234");

            String sql = "insert into todo (title, description) values (?, ?)";

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
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/workspace/tools/h2/base/dopey",
                    "dev", "1234");

            String sql = "delete from todo where id=?";

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
}
