import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

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
        String query = "SELECT * FROM admin WHERE Username = '" + username + "' AND Password = '" + password + "'";
        
        

        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {

                Session.userName = result.getString("userName");
                Session.city = result.getString("city");
                Session.contact = result.getString("contact");
                Session.id = result.getString("id");

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
            String query = "INSERT INTO admin (Username, Password, City, Contact) VALUES (?, ?, ?, ?)";
            pstatement = getDBConnection().prepareStatement(query);
            pstatement.setString(1, user.getUsername());
            pstatement.setString(2, user.getPassword());
            pstatement.setString(3, user.getCity());
            pstatement.setString(4, user.getContact());
            

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
            pstatement = getDBConnection().prepareStatement("UPDATE admin SET Username = ?, Password = ?, City = ?, Contact = ? WHERE Username = ?");
                pstatement.setString(1, user.getUsername());
                pstatement.setString(2, user.getPassword());
                pstatement.setString(3, user.getCity());
                pstatement.setString(4, user.getContact());
                pstatement.setString(5, user.getUsername());
                int res = pstatement.executeUpdate();
                if (res > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ResultSet getStocks(){
    
        getInstance();
        ResultSet result = null;
    
        try {
            String query = "SELECT * FROM stock";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
            return result;
        }

        public static boolean addStock(Stock stock){

            try {
                String query = "INSERT INTO stock (product, price, stockquantity) VALUES (?, ?, ?)";
                pstatement = getDBConnection().prepareStatement(query);
                pstatement.setString(1, stock.getProduct());
                pstatement.setInt(2, stock.getPrice());
                pstatement.setInt(3, stock.getStockquantity());
                
                return (pstatement.executeUpdate() > 0);
    
    
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return false;
        }

        public static boolean deleteStock(Stock stock){

            try {
                
                pstatement = getDBConnection().prepareStatement("DELETE FROM stock WHERE product=?");
                pstatement.setString(1, stock.getProduct());
                
                int res = pstatement.executeUpdate();
                if (res > 0) {
                    return true;
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return false;
        }

        public static boolean updateStock(Stock stock){

            try {
                pstatement = getDBConnection().prepareStatement("UPDATE stock SET product = ?, price = ?, stockquantity = stockquantity + ? WHERE product = ?");
                pstatement.setString(1, stock.getProduct());
                pstatement.setInt(2, stock.getPrice());
                pstatement.setInt(3, stock.getStockquantity());
                pstatement.setString(4, stock.getProduct());
                
                int res = pstatement.executeUpdate();
                if (res > 0) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;

            
        }

        public static void updateStock (String id, int quantity) {
            String query = "UPDATE stock SET stockquantity = stockquantity - ? WHERE id = ? ";
            Connection connection = null;
            PreparedStatement stmt = null;

            try {
                connection = getDBConnection();
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, quantity);
                stmt.setString(2, id);

                int affectedRows = stmt.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();

            }
        }


}



