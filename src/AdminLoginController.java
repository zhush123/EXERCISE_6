import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class AdminLoginController {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button returnbutton;

    private Stage stage;
    private Scene scene;
    private Parent root;

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
    public void loginbuttonHandler(ActionEvent event) throws IOException{

        //System.out.println("WELCOME TO MY APP!!!");

        String uname = usernameTextField.getText();
        String pword = passwordTextField.getText();

        if(AdminDatabaseHandler.validateLogin(uname, pword)) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            
            root = loader.load();
        
            HomeController homeController = loader.getController();
            homeController.displayName(uname);


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        else
        {
            System.out.println("Login unsuccessful ");
        }


        
        
    }

    


}