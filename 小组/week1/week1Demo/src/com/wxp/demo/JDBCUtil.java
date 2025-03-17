package com.wxp.demo;

import java.sql.*;

public class JDBCUtil {

    private static final String url = "jdbc:mysql://localhost:3306/db1?useSSL=false&characterEncoding=utf-8";
    private static final String username = "root";
    private static final String password = "wf810412";

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        try{
            if(conn != null){
                conn.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
