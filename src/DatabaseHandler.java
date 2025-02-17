import java.sql.*;

public class DatabaseHandler {

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
        String query = "SELECT * FROM admin WHERE UserName = '" + username + "' AND Password = '" + password + "'";
        
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

    public static ResultSet getUsers(){

        ResultSet result = null;

        try {
            String query = "SELECT * FROM admin";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean addUser(User user){

        try {
            String query = "INSERT INTO admin (UserName, Password) VALUES (?, ?)";
            pstatement = getDBConnection().prepareStatement(query);
            pstatement.setString(1, user.getUsername());
            pstatement.setString(2, user.getPassword());
            

            return (pstatement.executeUpdate() > 0);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean deleteUser(User user){

        try {
            
            pstatement = getDBConnection().prepareStatement("DELETE FROM admin WHERE Username=?");
            pstatement.setString(1, user.getUsername());
            
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean updateUser(User user){

        try {
            pstatement = getDBConnection().prepareStatement("UPDATE admin SET Username = ?, Password = ? WHERE Username = ?");
            pstatement.setString(1, user.getUsername());
            pstatement.setString(2, user.getPassword());
            pstatement.setString(3, user.getUsername());
            
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    
}
