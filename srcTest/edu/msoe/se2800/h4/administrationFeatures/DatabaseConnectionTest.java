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
    public void getAdminUsersTest() throws IOException{
        List<String> users = DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.ADMIN);
        //really, the only thing we know for sure is that the admin user is in the list
        assertTrue(users.contains("admin"));
    }

    @Test(description = "Tests to make sure that a user can be added and deleted")
    public void testAddAndDeleteUser() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        //add dummy user
        DatabaseConnection.ResultInfo result = db.addUser("qazwsdxedfcrfgvtgb", "sdf", DatabaseConnection.UserTypes.OBSERVER);
        assertTrue(result.wasSuccess());
        //remove dummy user
        result = db.deleteUser("qazwsdxedfcrfgvtgb");
        assertTrue(result.wasSuccess());
    }

    @Test(description = "Tests to make sure that you can get the correct password for a user")
    public void testGetUserPassword() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        //add dummy user
        DatabaseConnection.ResultInfo result = db.addUser("qazwsdxedfcrfgvtgb", "sdf", DatabaseConnection.UserTypes.OBSERVER);
        assertEquals(db.getUserPassword("qazwsdxedfcrfgvtgb"), "sdf");
        //remove dummy user
        result = db.deleteUser("qazwsdxedfcrfgvtgb");
    }



    @Test(description = "This tests to make sure that a failure result is returned if the user does not exist")
    public void testChangeNonExistantUser() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        DatabaseConnection.ResultInfo success = db.changeUserInfo("wertyui", "ertyui", DatabaseConnection.UserTypes.ADMIN);
        assertEquals(success.wasSuccess(), false);
    }

    @Test(description = "this tests to make sure you can change the password of a user")
    public void changePasswordTest() throws IOException {
        DatabaseConnection db = DatabaseConnection.getInstance();
        //add a dummy user
        db.addUser("qazwsxedcrfv", "test", DatabaseConnection.UserTypes.ADMIN);
        DatabaseConnection.ResultInfo result = db.changeUserInfo("qazwsxedcrfv", "ddd", DatabaseConnection.UserTypes.ADMIN);
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
        DatabaseConnection.ResultInfo result = db.changeUserInfo("qazwsxedcrfv", "test", DatabaseConnection.UserTypes.OBSERVER);
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
        DatabaseConnection.ResultInfo result = db.changeUserInfo("admin", db.getUserPassword("admin"), DatabaseConnection.UserTypes.OBSERVER);
        assertEquals(result.wasSuccess(), false);
    }
}
