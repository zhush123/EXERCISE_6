import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrderHandler implements Initializable{

    ObservableList<Stock> stocklist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Stock, Integer> idcol;

    @FXML
    private TableColumn<Stock, Integer> pricecol;

    @FXML
    private TableColumn<Stock, String> productcol;

    @FXML
    private TableColumn<Stock, Integer> stockquantitycol;

    @FXML
    private TextField idtextfield;

    @FXML
    private Button orderButton;


    @FXML
    private TextField quantitytextfield;

    @FXML
    private TableView<Stock> stocktable;

    @FXML
    private Button returnbutton;

    

    public void returnbuttonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) returnbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void getOrder(ActionEvent event) throws IOException {
        String id = idtextfield.getText();
        int quantity = Integer.parseInt(quantitytextfield.getText());

        DatabaseHandler.updateStock(id, quantity);
        displayStocks();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thank you!");
        alert.setHeaderText("Your order is being prepared for shipping");
        alert.setContentText("Thank you for supporting AquaFlow, Please come again!");
        alert.showAndWait();

}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCol();
        displayStocks();
        
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

    }

