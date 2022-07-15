package service.template;

import dbconnector.dbConnection;
import entity.Todo;
import service.template.callback.queryTemplateCallback;
import service.template.callback.updateTemplateCallback;

import java.sql.*;
import java.util.ArrayList;

public class template {
    private final dbConnection conn;

    public template(dbConnection dbconn) {
        this.conn = dbconn;
    }

    public ArrayList<Todo> queryTemplate(queryTemplateCallback callbackObj) {
        ArrayList<Todo> list = null;

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = this.conn.connectionMake();
            st = conn.createStatement();
            list = callbackObj.callback(st, rs);
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

    public int updateTemplate(updateTemplateCallback callbackObj) {
        int result = 0;

        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = this.conn.connectionMake();
            pst = callbackObj.callback(conn);
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
}
