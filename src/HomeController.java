import java.io.IOException;
import java.sql.ResultSet;
import java.util.Observable;
import javax.swing.Action;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class HomeController {

    ObservableList<User> userlist = FXCollections.observableArrayList();

    

    @FXML
    private Label homelabel;

    @FXML
    private TableView<User> mytable;

    @FXML
    private TableColumn<User, String> idcol;

    @FXML
    private TableColumn<User, String> passwordcol;

    @FXML
    private TableColumn<User, String> usernamecol;

    @FXML
    private TableColumn<User, String> citycol;

    @FXML
    private TableColumn<User, String> contactcol;

    @FXML
    private TableColumn<User, String> accountcreatedcol;

    @FXML
    private Button btncreate;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btnstock;

    @FXML
    private TextField nametextfield;

    @FXML
    private TextField passwordtextfield;

    @FXML
    private TextField addressTextfield;
    
    @FXML
    private TextField contactTextfield;

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


    public void displayName(String username){
        homelabel.setText("Admin " + username);
        initializeCol();
        displayUsers();
    
    }

    private void initializeCol(){

        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernamecol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordcol.setCellValueFactory(new PropertyValueFactory<>("password"));
        citycol.setCellValueFactory(new PropertyValueFactory<>("city"));
        contactcol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        accountcreatedcol.setCellValueFactory(new PropertyValueFactory<>("accountcreated"));
    }

    private void displayUsers(){
        userlist.clear();

        ResultSet result = DatabaseHandler.getUsers();

        try {
            while(result.next()){
                String id = result.getString("id");
                String username = result.getString("username");
                String password = result.getString("password");
                String city = result.getString("city");
                String contact = result.getString("contact");
                String accountcreated = result.getString("accountcreated");
                
                //Create new user instance
                User newuser = new User(id, username, password, city, contact, accountcreated);

                //Add to array list
                userlist.add(newuser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        mytable.setItems(userlist);

    }
    
    @FXML
    private void createUser(ActionEvent event) {

        String username = nametextfield.getText();
        String password = passwordtextfield.getText();
        String city = addressTextfield.getText();
        String contact = contactTextfield.getText();
        
        username = username.trim();
        password = password.trim();
        city = city.trim();
        contact = contact.trim();

        if(username.length() == 0) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Empty username");
        alert.showAndWait();
        return;
        

        }

        if (password.length() == 0) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Empty password");
        alert.showAndWait();
        return;

        }

        if (city.length() == 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Empty city");
            alert.showAndWait();
            return;
    
            }

            if (contact.length() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Contact is Empty");
                alert.showAndWait();
                return;
        
                }

        User user = new User("",username, password, city, contact, "");

        if (DatabaseHandler.addUser(user)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("account created!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Cannot create new user");
            alert.showAndWait();
            
        }

        displayUsers();

    }


    @FXML
    private void deleteUser(ActionEvent event){
        
        User user = mytable.getSelectionModel().getSelectedItem();

        String username = user.getUsername();

        if (DatabaseHandler.deleteUser(user)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("User " + username + " deleted");
            alert.showAndWait();
        
    
        }
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Cannot delete user " + username);
            alert.showAndWait();
    
        }
        displayUsers();

    }

    @FXML
    private void updateUser(ActionEvent event) {

        String username = nametextfield.getText();
        String password = passwordtextfield.getText();
        String city = addressTextfield.getText();
        String contact = contactTextfield.getText();
        
        username = username.trim();
        password = password.trim();
        city = city.trim();
        contact = contact.trim();

        if(username.length() == 0) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Empty username");
        alert.showAndWait();
    
        }
    
        if (password.length() == 0) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Empty password");
        alert.showAndWait();
    
        }
        
        if (city.length() == 0) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Empty City");
        alert.showAndWait();
        }

        if (contact.length() == 0) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Empty Contact");
        alert.showAndWait();
        
        }
        
        User user = new User("",username, password, city, contact, "");


        if (DatabaseHandler.updateUser(user)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("user updated!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Cannot update user");
            alert.showAndWait();
            
        }

        displayUsers();

    }

    @FXML
    private void stockButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Stock.fxml"));
        AnchorPane root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();


            
    }
    


}