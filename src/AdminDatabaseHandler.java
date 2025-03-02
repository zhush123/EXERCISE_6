// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class AdminDatabaseHandler {

//     private static AdminDatabaseHandler handler = null;

//     private AdminDatabaseHandler() {

//     }

//     public static AdminDatabaseHandler getInstance() {
//         if (handler == null) {
//             handler = new AdminDatabaseHandler();
//         }
//         return handler;
//     }

//     public static Connection getDBConnection() {
//         Connection connection = null;
//         String dburl = "jdbc:mysql://127.0.0.1:3306/adminUsers";
//         String userName = "root";
//         String password = "password";

//         try {
//             connection = DriverManager.getConnection(dburl, userName, password);
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }

//         return connection;
//     }

import java.sql.*;

public class AdminDatabaseHandler {

    private static DatabaseHandler handler = null;
    private static Statement stmt = null;
    private static PreparedStatement pstatement = null;

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public static Connection getDBConnection()
    {
        Connection connection = null;
        String dburl = "jdbc:mysql://127.0.0.1:3306/admin";
        String userName = "root";
        String password = "password";

        try
        {
            connection = DriverManager.getConnection(dburl, userName, password);

        } catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }
    
    public ResultSet execQuery(String query) {

        ResultSet result;
        try {
            stmt = getDBConnection().createStatement();
            result = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }

    public static boolean validateLogin(String username, String password){

        getInstance();
        String query = "SELECT * FROM adminUsers WHERE UserName = '" + username + "' AND Password = '" + password + "'";
        
        System.out.println(query);

        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public static ResultSet getUsers() {
        ResultSet result = null;
        String query = "SELECT * FROM adminUsers";
        try {
            Connection connection = getDBConnection();
            PreparedStatement pstatement = connection.prepareStatement(query);
            result = pstatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}