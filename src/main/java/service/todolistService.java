package service;

import dbconnector.dbConnection;
import dbconnector.h2Connection;
import entity.Todo;

import java.sql.*;
import java.util.ArrayList;

public class todolistService {
    private final dbConnection dbconn;

    todolistService(dbConnection dbconn) {
        this.dbconn = dbconn;
    }

    public ArrayList<Todo> allToDoList() {
        ArrayList<Todo> list = new ArrayList<>();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            String sql = "select * from todo";

            conn = this.dbconn.connectionMake();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                Todo todo = new Todo(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("status"));

                list.add(todo);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) { }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { }
            }
        }

        return list;
    }

    public int addTodo(String title, String description) {
        int result = 0;

        Connection conn = null;
        PreparedStatement pst = null;
        try {
            String sql = "insert into todo (title, description) values (?, ?)";

            conn = this.dbconn.connectionMake();

            pst = conn.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, description);

            result = pst.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return result;
    }

    public int removeTodo(int id) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            String sql = "delete from todo where id=?";

            conn = this.dbconn.connectionMake();

            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            result = pst.executeUpdate();

            pst.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return result;
    }

    public int setStatus(int id, int status) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            String sql = "update todo set status=? where id=?";

            conn = this.dbconn.connectionMake();

            pst = conn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setInt(2, id);

            result = pst.executeUpdate();

            pst.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return result;
    }
}
