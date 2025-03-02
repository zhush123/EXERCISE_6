import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private TextField addressTextfield;

    @FXML
    private Label addressTextlabel;

    @FXML
    private TextField contactTextfield;

    @FXML
    private Label contactTextlabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button registerButton;

    @FXML
    private Button returnbutton;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameTextField;

    public void returnbuttonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) returnbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void loginbuttonHandler(ActionEvent event) {

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String city = addressTextfield.getText();
        String contactnumber = contactTextfield.getText();
        
        username = username.trim();
        password = password.trim();
        city = city.trim();
        contactnumber = contactnumber.trim();

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
        
        if (contactnumber.length() == 0) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Empty contact number");
        alert.showAndWait();
        return;

        }
        User user = new User("",username, password, city, contactnumber, "");

        if (DatabaseHandler.addUser(user)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("account created!");
            alert.showAndWait();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) registerButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
            

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Cannot create new user");
            alert.showAndWait();
            
        }
        

    }

}
