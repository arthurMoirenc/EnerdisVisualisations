package com.example;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:C:/Users/amoirenc/Desktop/bddEnerdis/bddEnerdisVisual.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
