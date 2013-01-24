package edu.msoe.se2800.h4.AdministrationFeatures;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

/**
 * User: scottat
 * Date: 1/21/13
 * Time: 10:43 AM
 */
public class DatabaseConnectionTest {

    @BeforeMethod
    public void setupDB(){
        DatabaseConnection.getInstance().tryConnect(DatabaseConnection.getInstance().DB_NAME);
    }

    @Test
    public void testBadDB() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        assertEquals(db.tryConnect("lkwjerlksj.slekjr"), false);
    }

    @Test
    public void testValidDB(){
        assertTrue(DatabaseConnection.getInstance().tryConnect(DatabaseConnection.getInstance().DB_NAME));
    }

    /**
     *
     */
    @Test
    public void testValidUser() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        boolean retVal = db.ValidateUser("admin", "admin");
        assertEquals(retVal, true);
    }

    @Test
    public void testInvalidUser() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        boolean retVal = db.ValidateUser("lksjdflkjwelrkj", "kwjerlkjwerlkjwer");
        assertEquals(retVal, false);
    }

    @Test
    public void testValidUserBadPass() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        boolean retVal = db.ValidateUser("admin", "kwjerlkjwerlkjwer");
        assertEquals(retVal, false);
    }

    @Test
    public void getOtherUsersWhichIsEmpty() throws IOException {
        List<String> users = DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.OTHER);
        assertTrue(users.size() == 0);
    }

    @Test
    public void getAdminUsers() throws IOException{
        List<String> users = DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.ADMIN);
        //really, the only thing we know for sure is that the admin user is in the list
        assertTrue(users.contains("admin"));
    }
}
