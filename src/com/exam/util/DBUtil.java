package com.exam.util;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBUtil {
    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE",
            "onlinetest",
            "hari2005"
        );
        con.setAutoCommit(false);
        return con;
    }
}
