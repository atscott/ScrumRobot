package edu.msoe.se2800.h4.AdministrationFeatures;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Index;
import com.healthmarketscience.jackcess.Table;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: scottat
 * Date: 1/15/13
 * Time: 7:49 PM
 */
public class DatabaseConnection {

    public static enum UserTypes {
        OBSERVER,
        ADMIN,
        PROGRAMMER,
        OTHER
    }

    private static DatabaseConnection ourInstance = new DatabaseConnection();

    public final String DB_NAME = "resources/userDB.mdb";
    public final String TABLE_NAME = "users";
    private Database db;
    private static String lastSuccessfullyValidatedUser;

    public static DatabaseConnection getInstance() {
        return ourInstance;
    }

    private DatabaseConnection() {
        tryConnect(DB_NAME);
    }

    /**
     * This can be used to get the currently logged in user unless validateUser ever gets called when a user does not
     * actually log in.
     *
     * @return the username of the last validated user
     */
    public String getLastSuccessfullyValidatedUser() {
        return lastSuccessfullyValidatedUser;
    }

    /**
     * Tries to set the database to the given path.
     *
     * @return true if the database was opened successfully
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

    /**
     * Validates given username, password combination against currently opened table
     *
     * @param username
     * @param password
     * @return true if username and password match the table
     * @throws IOException
     */
    public boolean ValidateUser(String username, String password) throws IOException {
        if (db == null) {
            throw new IOException("Database not connected");
        }

        boolean valid = false;

        Table table = db.getTable(TABLE_NAME);
        Map<String, Object> row = Cursor.findRow(table, Collections.singletonMap("username", (Object) username));
        if (row != null) {
            String actualPassword = (String) row.get("password");
            if (password != null && password.equals(actualPassword)) {
                valid = true;
            }
        }

        if (valid) {
            lastSuccessfullyValidatedUser = username;
        }

        return valid;
    }

    /**
     * given the username of a user, gets the user's access type from the table
     *
     * @param username the username for the user
     * @return the user's access mode
     */
    public UserTypes getUserRole(String username) throws IOException {
        UserTypes permission = UserTypes.OTHER;
        Table table = db.getTable(TABLE_NAME);
        Map<String, Object> row = Cursor.findRow(table, Collections.singletonMap("username", (Object) username));
        if (row != null) {
            String role = (String) row.get("permission");
            if (role != null) {
                if (role.equals("administrator")) {
                    permission = UserTypes.ADMIN;
                } else if (role.equals("observer")) {
                    permission = UserTypes.OBSERVER;
                } else if (role.equals("programmer")) {
                    permission = UserTypes.PROGRAMMER;
                }

            }

        }

        return permission;
    }

    private String getRoleAsString(UserTypes role) {
        String roleString;
        switch (role) {
            case OBSERVER:
                roleString = "observer";
                break;
            case ADMIN:
                roleString = "administrator";
                break;
            case PROGRAMMER:
                roleString = "programmer";
                break;
            default:
                roleString = "";
        }
        return roleString;
    }

    public List<String> getUsernamesWithRole(UserTypes role) throws IOException {
        List<String> users = new ArrayList<String>();
        //get the string value of the permission that we're looking for
        String permissionToLookFor = getRoleAsString(role);
        Table table = db.getTable(TABLE_NAME);
        for (Map<String, Object> row : table) {
            //get the value of the permission in the current row
            String currentRowPermission = (String) row.get((Object) "permission");
            //if the row's permission is what we're looking for
            if(currentRowPermission != null && currentRowPermission.equals(permissionToLookFor)){
                //get the username for the row and add it to the list
                users.add((String) row.get((Object) "username"));
            }

        }

        return users;
    }

}

