package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DATABASE_USER = "katauser";
    private static final String DATABASE_PASSWORD = "katauser";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
