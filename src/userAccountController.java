import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class userAccountController implements Initializable {

    @FXML
    private Text citylabel;

    @FXML
    private Text contactlabel;

    @FXML
    private Button editprofile;

    @FXML
    private Button returnbutton;

    @FXML
    private Text usernamelabel;

    @FXML
    private Text usernamelabel1;

    @FXML
    private Text idlabel;

    @FXML
    private Text pricelabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        usernamelabel.setText(Session.userName);
        usernamelabel1.setText(Session.userName);
        citylabel.setText(Session.city);
        contactlabel.setText(Session.contact);
    }

    @FXML
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
    public void editprofileHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEdit.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) returnbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}