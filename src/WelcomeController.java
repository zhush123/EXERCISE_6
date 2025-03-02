import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private Button homebutton;

    @FXML
    private Button signupbutton;

    @FXML
    private Button btncomingsoon;

    @FXML
    public void HomeButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserLogin.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) homebutton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    @FXML
    public void SignUpHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) signupbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void ComingSoonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Feature Under Development");
        alert.setHeaderText("We're currently working on this feature to make it even better!");
        alert.setContentText("it’s not quite ready yet, but we’re on it. Thanks so much for your patience—stay tuned for updates!");
        alert.showAndWait();
    }

}