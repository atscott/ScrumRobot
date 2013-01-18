package edu.msoe.se2800.h4;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class DatabaseSample {

    public static void main(String[] args) throws IOException {
        Database database = Database.open(new File("./resources/userDB.mdb"));
        Table users = database.getTable("users");
        System.out.println(users.display());
        
        Object name = "andrew";
        Map<String, Object> row = Cursor.findRow(users, Collections.singletonMap("username", name));
        String password = (String) row.get("password");
        System.out.println("password: " + password);
    }

}
