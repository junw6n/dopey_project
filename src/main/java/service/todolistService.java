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

            String query = "select * from todo";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

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
}
