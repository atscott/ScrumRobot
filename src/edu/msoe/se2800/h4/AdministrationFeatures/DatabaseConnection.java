package edu.msoe.se2800.h4.AdministrationFeatures;

import java.sql.*;

/**
 * User: scottat
 * Date: 1/15/13
 * Time: 7:49 PM
 */
public class DatabaseConnection {
    private static DatabaseConnection ourInstance = new DatabaseConnection();

    private final String DB_NAME = "sample.db";
    private final String TABLE_NAME = "users";
    Connection connection;

    public static DatabaseConnection getInstance() {
        return ourInstance;
    }

    private DatabaseConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean IsConnected() {
        boolean active = false;
        if (connection != null) {
            try {
                active = !connection.isClosed();
            } catch (SQLException e) {
                active = false;
            }
        }

        return active;
    }

    public boolean ValidateUser(String username, String password) throws SQLException {
        boolean isValid = false;
        Statement statement = connection.createStatement();

        String SQL = "SELECT password FROM " + TABLE_NAME + " WHERE username = '" + username + "'";
        ResultSet rs = statement.executeQuery(SQL);
        while (rs.next() && !isValid) {
            if (rs.getString("password").equals(password)) {
                isValid = true;
            }
        }
        statement.close();

        return isValid;
    }


}
