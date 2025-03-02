import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserEditController {

    @FXML
    private TextField addresstextfield;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;

    @FXML
    private TextField contacttextfield;

    @FXML
    private TextField nametextfield;

    @FXML
    private TextField passwordtextfield;

    @FXML
    private Button returnbutton;


    public void returnbuttonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserAccount.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) returnbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void updateUser(ActionEvent event) {

        String username = nametextfield.getText();
        String password = passwordtextfield.getText();
        String city = addresstextfield.getText();
        String contact = contacttextfield.getText();
        
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
        alert.setContentText("Empty City");
        alert.showAndWait();
        return;
        }
    
        if (contact.length() == 0) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Empty Contact");
        alert.showAndWait();
        return;
        
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
        return;
        

        }
        

}

}
