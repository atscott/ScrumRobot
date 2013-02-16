
package edu.msoe.se2800.h4.administrationFeatures;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;

/**
 * User: scottat Date: 1/15/13 Time: 7:49 PM
 * This class allows for accessing the database file for user administration.
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
        username = username.toUpperCase();

        boolean valid = false;

        Table table = db.getTable(TABLE_NAME);
        Map<String, Object> row = Cursor.findRow(table,
                Collections.singletonMap("username", (Object) username));
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
     * Returns the password for the username provided
     * @param username the username of the user to get password of
     * @return the password of the user
     * @throws IOException If there was an error accessing the database
     */
    public String getUserPassword(String username) throws IOException {
        checkNotNull(username);
        username = username.toUpperCase();

        String pass = null;
        Table table = db.getTable(TABLE_NAME);
        Map<String, Object> row = Cursor.findRow(table,
                Collections.singletonMap("username", (Object) username));
        if (row != null) {
            pass = (String) row.get("password");
        }

        return pass;
    }

    /**
     * given the username of a user, gets the user's access type from the table
     *
     * @param username the username for the user
     * @return the user's access mode
     */
    public UserTypes getUserRole(String username) throws IOException {
        username = username.toUpperCase();
        UserTypes permission = UserTypes.OTHER;
        Table table = db.getTable(TABLE_NAME);
        Map<String, Object> row = Cursor.findRow(table,
                Collections.singletonMap("username", (Object) username));
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

    /**
     * @param role The role enum to convert to string
     * @return the string value that the table uses as the permission
     */
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

    /**
     * @param role role to search for
     * @return the list of users with the given role.
     * @throws IOException thrown if trouble accessing the table
     */
    public List<String> getUsernamesWithRole(UserTypes role) throws IOException {
        List<String> users = new ArrayList<String>();
        // get the string value of the permission that we're looking for
        String permissionToLookFor = getRoleAsString(role);
        Table table = db.getTable(TABLE_NAME);
        for (Map<String, Object> row : table) {
            // get the value of the permission in the current row
            String currentRowPermission = (String) row.get("permission");
            // if the row's permission is what we're looking for
            if (currentRowPermission != null && currentRowPermission.equals(permissionToLookFor)) {
                // get the username for the row and add it to the list
                users.add((String) row.get("username"));
            }

        }

        return users;
    }

    /**
     * @param username    The username of the user to update
     * @param newPassword the new password for the user
     * @param newRole     the user's new permission role
     * @throws IOException              thrown if error accessing table
     * @throws IllegalArgumentException thrown if trying to change the admin role from something
     *                                  other than admin.
     */
    public ResultInfo changeUserInfo(String username, String newPassword, UserTypes newRole)
            throws IOException, IllegalArgumentException {
        if (username == null || newPassword == null)
            throw new NullPointerException("Cannot use null arguments");
        username = username.toUpperCase();
        if (username.equals("ADMIN") && newRole != UserTypes.ADMIN)
            throw new IllegalArgumentException("Cannot make the admin not an administrator");


        ResultInfo result;
        Table table = db.getTable(TABLE_NAME);
        // get the row for user
        Cursor cursor = Cursor.createCursor(table);
        boolean found = cursor.findFirstRow(
                Collections.singletonMap("username", (Object) username));
        if (found) {
            cursor.setCurrentRowValue(table.getColumn("username"), (Object) username);
            cursor.setCurrentRowValue(table.getColumn("password"), (Object) newPassword);
            cursor.setCurrentRowValue(table.getColumn("permission"), (Object) getRoleAsString(newRole));
            result = new ResultInfo("User modified successfully", true);
        } else {
            result = new ResultInfo("Could not find user", false);
        }

        return result;
    }

    /**
     * @param username username for user
     * @param password the user's password
     * @param role     the role for the user
     * @throws IllegalArgumentException If there is a user with same username already in the table,
     *                                  this will get thrown
     */
    public ResultInfo addUser(String username, String password, UserTypes role)
            throws IOException {
        if (username == null || password == null)
            throw new NullPointerException("Cannot use null arguments");
        username = username.toUpperCase();
        ResultInfo result;
        Table table = db.getTable(TABLE_NAME);
        // get the row for user
        Cursor cursor = Cursor.createCursor(table);
        boolean found = cursor.findFirstRow(
                Collections.singletonMap("username", (Object) username));
        if (!found) {
            Map<String, Object> row = new HashMap<String, Object>();
            row.put("username", (Object) username);
            row.put("password", (Object) password);
            row.put("permission", (Object) getRoleAsString(role));
            table.addRow(table.asRow(row));
            result = new ResultInfo("User added successfully.", true);
        } else {
            result = new ResultInfo("User already exists", false);
        }

        return result;
    }

    /**
     * deletes user from table
     *
     * @param username user to delete
     * @throws IllegalArgumentException thrown if try to delete administrator.
     * @throws IOException              thrown if trouble accessing table
     */
    public ResultInfo deleteUser(String username) throws IllegalArgumentException, IOException {
        checkNotNull(username, "Cannot use null arguments");

        username = username.toUpperCase();
        if (username.equals("ADMIN")) {
            throw new IllegalArgumentException("Cannot delete admin");
        }

        Table table = db.getTable(TABLE_NAME);
        Cursor cur = Cursor.createCursor(table);
        ResultInfo result;
        // get the row for user
        boolean foundUser = cur.findFirstRow(Collections.singletonMap("username", (Object) username));
        if (foundUser) {
            cur.deleteCurrentRow();
            result = new ResultInfo("User deleted successfully", true);
        } else {
            result = new ResultInfo("Could not find user.", false);
        }

        return result;
    }


}
