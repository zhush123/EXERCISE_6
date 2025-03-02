import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StockController implements Initializable {

    ObservableList<Stock> stocklist = FXCollections.observableArrayList();


    @FXML
    private Button returnbutton;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btncreate;

    @FXML
    private Button btndelete;

    @FXML
    private Label homelabel;

    @FXML
    private TableColumn<Stock, Integer> idcol;

    @FXML
    private TableColumn<Stock, Integer> pricecol;

    @FXML
    private TableColumn<Stock, String> productcol;

    @FXML
    private TableColumn<Stock, Integer> stockquantitycol;

    @FXML
    private TextField pricetextfield;

    @FXML
    private TextField producttextfield;

    @FXML
    private TextField stockquantitytextfield;

    

    @FXML
    private TableView<Stock> stocktable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeCol();
        displayStocks();
    }
    
    public void displayAdminName(String username) {
        homelabel.setText("Admin " + username);

    }

    private void initializeCol() {
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productcol.setCellValueFactory(new PropertyValueFactory<>("product"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockquantitycol.setCellValueFactory(new PropertyValueFactory<>("stockquantity"));
    }

    private void displayStocks() {
        stocklist.clear();

        ResultSet result = DatabaseHandler.getStocks();

        try {
            while (result.next()) {
                int id = result.getInt("id");
                String product = result.getString("product");
                int price = result.getInt("price");
                int stockquantity = result.getInt("stockquantity");

                Stock newstock = new Stock(id, product, price, stockquantity);
                stocklist.add(newstock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        stocktable.setItems(stocklist);

        
    }

    @FXML
    private void createProduct(ActionEvent event) {

        String product = producttextfield.getText();
        String price = pricetextfield.getText();
        String stockquantity = stockquantitytextfield.getText();
        
        product = product.trim();
        price = price.trim();
        stockquantity = stockquantity.trim();

        if(product.length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please enter the product name");
            alert.showAndWait();

        }

        if (price.length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please enter the price");
            alert.showAndWait();
        }

        if (stockquantity.length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("add stock quantity");
            alert.showAndWait();
    
            }
            Stock stock = new Stock(0, product, Integer.parseInt(price), Integer.parseInt(stockquantity));

            if (DatabaseHandler.addStock(stock)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Product added!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Cannot Add new Product");
                alert.showAndWait();
                
            }
    
            displayStocks();
        }

        @FXML
    private void deleteStock(ActionEvent event){
        
        Stock stock = stocktable.getSelectionModel().getSelectedItem();

        String product = stock.getProduct();

        if (DatabaseHandler.deleteStock(stock)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Product " + product + " deleted");
            alert.showAndWait();
        
    
        }
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Cannot delete product " + product);
            alert.showAndWait();
    
        }
        displayStocks();

    }

    @FXML
        private void updateStock(ActionEvent event) {

            String product = producttextfield.getText();
            String price = pricetextfield.getText();
            String stockquantity = stockquantitytextfield.getText();
        
            product = product.trim();
            price = price.trim();
            stockquantity = stockquantity.trim();

            if(product.length() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Empty Stock");
                alert.showAndWait();
    
            }
    
            if (price.length() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Add a price");
                alert.showAndWait();
    
            }

            if (stockquantity.length() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Add stock quantity");
                alert.showAndWait();
        
                }
                Stock stock = new Stock(0, product, Integer.parseInt(price), Integer.parseInt(stockquantity));
                
        if (DatabaseHandler.updateStock(stock)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Product updated!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please Try again");
            alert.showAndWait();
            
        }

        displayStocks();

    }

    @FXML
    public void returnbuttonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) returnbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.close();
        
    }

}






