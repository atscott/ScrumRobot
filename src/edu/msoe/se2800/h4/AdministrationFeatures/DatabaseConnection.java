package edu.msoe.se2800.h4.AdministrationFeatures;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * User: scottat
 * Date: 1/15/13
 * Time: 7:49 PM
 */
public class DatabaseConnection {
    private static DatabaseConnection ourInstance = new DatabaseConnection();

    public final String DB_NAME = "resources/userDB.mdb";
    public final String TABLE_NAME = "users";
    private Database db;

    public static DatabaseConnection getInstance() {
        return ourInstance;
    }

    private DatabaseConnection() {
        tryConnect(DB_NAME);
    }

    /**
     * Tries to set the database to the
     *
     * @return
     */
    public boolean tryConnect(String dbName) {
        boolean success = false;
        try {
            db = Database.open(new File(dbName));
            success = true;
        } catch (IOException e) {
            db = null;
        }
        return success;
    }

    public boolean IsConnected() {
        return db != null;
    }

    public boolean ValidateUser(String username, String password) throws IOException {
        boolean valid = false;

        Table table = db.getTable(TABLE_NAME);
        Object name = username;
        Map<String, Object> row = Cursor.findRow(table, Collections.singletonMap("username", name));
        if (row != null) {
            String actualPassword = (String) row.get("password");
            if (password != null && password.equals(actualPassword)) {
                valid = true;
            }
        }

        return valid;
    }

    public static void main(String[] args) throws IOException {
        DatabaseConnection.getInstance().test();
    }

    private void test() throws IOException {
        System.out.println(db.getTable(TABLE_NAME).display());
    }


}

