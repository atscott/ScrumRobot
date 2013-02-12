package edu.msoe.se2800.h4.administrationFeatures;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * User: scottat
 * Date: 1/21/13
 * Time: 10:43 AM
 */
@Test(groups={"requiresSequential"})
public class DatabaseConnectionTest {

    @BeforeMethod
    public void setupDB(){
        DatabaseConnection.getInstance().tryConnect(DatabaseConnection.getInstance().DB_NAME);
    }

    @Test(description = "This tests to see that trying to connect to a database that does not exist returns false")
    public void testBadDB() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        assertEquals(db.tryConnect("lkwjerlksj.slekjr"), false);
    }

    @Test(description = "Tests to see that we can connect to the database")
    public void testValidDB(){
        assertTrue(DatabaseConnection.getInstance().tryConnect(DatabaseConnection.getInstance().DB_NAME));
    }

    @Test(description = "tests to make sure you can log in with valid credentials")
    public void testValidUser() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        boolean retVal = db.ValidateUser("admin", "admin");
        assertEquals(retVal, true);
    }

    @Test(description = "Tests to make sure you cannot create a user that already exists")
    public void testCreateAlreadyExistingUser() throws IOException{
        DatabaseConnection db = DatabaseConnection.getInstance();
        ResultInfo retVal = db.addUser("admin", "admin", DatabaseConnection.UserTypes.ADMIN);
        assertEquals(retVal.wasSuccess(), false);
    }

    @Test(description = "Tests to make sure you cannot log in with a user that does not exist")
    public void testInvalidUser() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        boolean retVal = db.ValidateUser("lksjdflkjwelrkj", "kwjerlkjwerlkjwer");
        assertEquals(retVal, false);
    }

    @Test(description = "Tests to make sure you cannot log in with the wrong password ")
    public void testValidUserBadPass() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        boolean retVal = db.ValidateUser("admin", "kwjerlkjwerlkjwer");
        assertEquals(retVal, false);
    }

    @Test(description = "Check to make sure no user has the role of other. This is just how our program should work")
    public void getOtherUsersWhichIsEmpty() throws IOException {
        List<String> users = DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.OTHER);
        assertTrue(users.size() == 0);
    }

    @Test(description = "Because ")
    public void getAdminUsersTest() throws IOException{
        List<String> users = DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.ADMIN);
        //really, the only thing we know for sure is that the admin user is in the list
        assertTrue(users.contains("ADMIN"));
    }

    @Test
    public void getUserRoleTest() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        //add dummy user
        ResultInfo result = db.addUser("qazwsdxedfcrfgvtgb", "sdf", DatabaseConnection.UserTypes.OBSERVER);
        assertEquals(db.getUserRole("qazwsdxedfcrfgvtgb"), DatabaseConnection.UserTypes.OBSERVER);
        //remove dummy user
        result = db.deleteUser("qazwsdxedfcrfgvtgb");
    }

    @Test(description = "Tests to make sure that a user can be added and deleted")
    public void testAddAndDeleteUser() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        //add dummy user
        ResultInfo result = db.addUser("qazwsdxedfcrfgvtgb", "sdf", DatabaseConnection.UserTypes.OBSERVER);
        assertTrue(result.wasSuccess());
        //remove dummy user
        result = db.deleteUser("qazwsdxedfcrfgvtgb");
        assertTrue(result.wasSuccess());
    }

    @Test(description = "Tests to make sure that you can get the correct password for a user")
    public void testGetUserPassword() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        //add dummy user
        ResultInfo result = db.addUser("qazwsdxedfcrfgvtgb", "sdf", DatabaseConnection.UserTypes.OBSERVER);
        assertEquals(db.getUserPassword("qazwsdxedfcrfgvtgb"), "sdf");
        //remove dummy user
        result = db.deleteUser("qazwsdxedfcrfgvtgb");
    }



    @Test(description = "This tests to make sure that a failure result is returned if the user does not exist")
    public void testChangeNonExistantUser() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        ResultInfo success = db.changeUserInfo("wertyui", "ertyui", DatabaseConnection.UserTypes.ADMIN);
        assertEquals(success.wasSuccess(), false);
    }

    @Test(description = "this tests to make sure you can change the password of a user")
    public void changePasswordTest() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        //add a dummy user
        db.addUser("qazwsxedcrfv", "test", DatabaseConnection.UserTypes.ADMIN);
        ResultInfo result = db.changeUserInfo("qazwsxedcrfv", "ddd", DatabaseConnection.UserTypes.ADMIN);
        assertTrue(result.wasSuccess());
        //verify you can log in with new credentials
        boolean retVal = db.ValidateUser("qazwsxedcrfv", "ddd");
        assertEquals(retVal, true);
        //delete the dummy
        db.deleteUser("qazwsxedcrfv");
    }

    @Test(description = "This is testing to see that the role of a user can be changed")
    public void changeRole() throws IOException{
        DatabaseConnection db = DatabaseConnection.getInstance();
        //add a dummy user
        db.addUser("qazwsxedcrfv", "test", DatabaseConnection.UserTypes.ADMIN);
        ResultInfo result = db.changeUserInfo("qazwsxedcrfv", "test", DatabaseConnection.UserTypes.OBSERVER);
        assertTrue(result.wasSuccess());
        DatabaseConnection.UserTypes type = db.getUserRole("qazwsxedcrfv") ;
        assertEquals(type, DatabaseConnection.UserTypes.OBSERVER);
        //delete the dummy
        db.deleteUser("qazwsxedcrfv");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, description = "This is testing to see that an exception" +
            " is thrown if you try to change the admin role to anything other than admin")
    public void changeAdminRole() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        ResultInfo result = db.changeUserInfo("admin", db.getUserPassword("admin"), DatabaseConnection.UserTypes.OBSERVER);
        assertEquals(result.wasSuccess(), false);
    }
}
