/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.msoe.se2800.h4.AdministrationFeatures;

/**
 *
 * @author Andrew - I wrote this class in high school and it looks like a good reference for relearning some database queries
 */
import java.sql.*;
import java.util.ArrayList;

public class DatabaseFunctions {

    private boolean bConnected = false;

    public DatabaseFunctions(){

    }

    public boolean getStatus(){
        return bConnected;
    }


    Connection con;

    Statement stmt = null;
    ResultSet rs = null;
    String SQL;
    String result;

    public void connectToDatabase(){

        try{
            String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver
            Class.forName(driverName);

            // Create a connection to the database
            String serverName = "localhost";
            String mydatabase = "myfirstdatabase";
            String url = "jdbc:mysql://" + serverName +  "/" + mydatabase; // a JDBC url
            String username = "xxxxxx";
            String password = "xxxxxx";
            con = DriverManager.getConnection(url, username, password);

            stmt = con.createStatement();
            bConnected = true;
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("SQL Exception: "+ e.toString());
            //printError("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: "+ cE.toString());
           // printError("Class Not Found Exception: " + cE.toString());
        }
    }

    public ArrayList getTable(String sort){
        ArrayList<String[]> table = new ArrayList();
        try{
                String tName = "";
                String tWins = "";
                String tLosses = "";
                String tGames = "";
                SQL = "SELECT * FROM wargamedata ORDER BY " + sort;
                rs = stmt.executeQuery(SQL);

                while (rs.next()){
                    //get info from the 'name' column in the returned/current row(rs)
                    tName = rs.getString("name");
                    tWins = "" + rs.getInt("wins");
                    tLosses = "" + rs.getInt("losses");
                    tGames = "" + rs.getInt("gamesplayed");
                    String[] newRow = {tName, tWins, tLosses, tGames};
                    table.add(newRow);
               }
             } catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());
            }
        return table;
    }

    public void closeConnection(){
        if (bConnected == true){
           try{
                con.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());
            }
        }
    }

//    public void updateDatabase(int winner, Player player1, Player player2){
        public void updateDatabase(int winner){
        if (bConnected){
            try{
                int wins = 0;
                int losses = 0;
                int gamesplayed = 0;
                int key;
                //check if player1 is already in the database
                boolean alreadyThere = false;
//                SQL = "SELECT * FROM wargamedata WHERE name = '" + player1.getName() + "'";
                rs = stmt.executeQuery(SQL);
                //System.out.println(rs.next());
                while (rs.isClosed()==false && rs.next() == true){
                    wins = rs.getInt("wins"); //get player1 win record
                    losses = rs.getInt("losses"); //get player1 loss record
                    gamesplayed = rs.getInt("gamesplayed"); //get gamesplayed for player 1
                    key = rs.getInt("playerID"); //get the key for player 1 record
                    if (winner == 1)
                        wins++; //add to the wins if player 1 won
                    else
                        losses++; //add to the losses if player 2 won
                    gamesplayed++; //update games played

                    //update the database
                    SQL = "UPDATE wargamedata SET wins = '" + wins + "' WHERE playerID = '" + key + "'";
                    stmt.executeUpdate(SQL);
                    SQL = "UPDATE wargamedata SET losses = '" + losses + "' WHERE playerID = '" + key + "'";
                    stmt.executeUpdate(SQL);
                    SQL = "UPDATE wargamedata SET gamesplayed = '" + gamesplayed + "' WHERE playerID = '" + key + "'";
                    stmt.executeUpdate(SQL);
                    alreadyThere = true;
                }

                if (alreadyThere == false){
                    if (winner == 1){
                        wins = 1;
                        losses = 0;
                    }
                    else{
                        wins = 0;
                        losses = 1;
                    }
                    gamesplayed = 1;
//                    SQL = "INSERT INTO wargamedata (name, wins, losses, gamesplayed) VALUES('" +
//                            player1.getName() + "', '" + wins + "', '" + losses + "', '" + gamesplayed + "')";
                    stmt.execute(SQL);
                }

                alreadyThere = false;
//                SQL = "SELECT * FROM wargamedata WHERE name = '" + player2.getName() + "'";
                rs = stmt.executeQuery(SQL);
                while (rs.isClosed() == false && rs.next() == true){
                    wins = rs.getInt("wins"); //get player1 win record
                    losses = rs.getInt("losses"); //get player1 loss record
                    gamesplayed = rs.getInt("gamesplayed"); //get gamesplayed for player 1
                    key = rs.getInt("playerID"); //get the key for player 1 record
                    if (winner == 2)
                        wins++; //add to the wins if player 2 won
                    else
                        losses++; //add to the losses if player 1 won
                    gamesplayed++; //update games played

                    //update the database
                    SQL = "UPDATE wargamedata SET wins = '" + wins + "' WHERE playerID = '" + key + "'";
                    stmt.executeUpdate(SQL);
                    SQL = "UPDATE wargamedata SET losses = '" + losses + "' WHERE playerID = '" + key + "'";
                    stmt.executeUpdate(SQL);
                    SQL = "UPDATE wargamedata SET gamesplayed = '" + gamesplayed + "' WHERE playerID = '" + key + "'";
                    stmt.executeUpdate(SQL);
                    alreadyThere = true;
                }

                if (alreadyThere == false){
                    if (winner == 2){
                        wins = 1;
                        losses = 0;
                    }
                    else{
                        wins = 0;
                        losses = 1;
                    }
                    gamesplayed = 1;
//                    SQL = "INSERT INTO wargamedata (name, wins, losses, gamesplayed) VALUES('" +
//                            player2.getName() + "', '" + wins + "', '" + losses + "', '" + gamesplayed + "')";
                    stmt.execute(SQL);
                }


            } catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());
            }
        }
    }


}
