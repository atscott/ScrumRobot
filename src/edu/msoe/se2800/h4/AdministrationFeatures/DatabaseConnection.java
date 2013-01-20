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

    private final String DB_NAME = "resources/userDB.mdb";
    private final String TABLE_NAME = "users";
    private Database db;

    public static DatabaseConnection getInstance() {
        return ourInstance;
    }

    private DatabaseConnection() {
        try {
            db = Database.open(new File(DB_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean IsConnected() {
        return db != null;
    }

    public boolean ValidateUser(String username, String password) throws IOException {

        return false;
    }

    public static void main(String[] args) throws IOException {
        DatabaseConnection.getInstance().test();
    }

    private void test() throws IOException {
        System.out.println(db.getTable(TABLE_NAME).display());
    }


}
