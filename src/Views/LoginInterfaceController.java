/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.User;
import Services.UserServices;
import Utils.DBConnexion;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class LoginInterfaceController implements Initializable {

    @FXML
    private AnchorPane image;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tflastname;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpwd;
    @FXML
    private TextField tfgender;
    @FXML
    private TextField tfphone;
    @FXML
    private TextField tfbirthday;
    @FXML
    private Button tfphoto;
    @FXML
    private TextField pwdtf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    Connection c = DBConnexion.getInstance().getCnx();
    Stage dialogStage = new Stage();
    Scene scene;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private void uploadphoto(ActionEvent event) {
        FileChooser F = new FileChooser();
        F.setTitle("Choisir une image");
        F.getExtensionFilters().addAll();
        File f = F.showOpenDialog(image.getScene().getWindow());
        if(f != null){
            tfphoto.setText(f.toString());
        }
    }

    @FXML
    private void signup(ActionEvent event) throws SQLException {
      System.out.println("press");
      UserServices ms = new UserServices();
      ms.AddUser(new User(tfname.getText(),tflastname.getText(),tfemail.getText(),tfpwd.getText(),tfgender.getText(),tfphoto.getText(),Integer.parseInt(tfphone.getText()),Date.valueOf(tfbirthday.getText())));
      JOptionPane.showMessageDialog(null, "User has been Created");
    }

    @FXML
    private void signin(ActionEvent event) throws IOException, SQLException, Exception {
                        
        String email = emailtf.getText();
        String password = pwdtf.getText();
         PreparedStatement st = (PreparedStatement) c.prepareStatement("Select email, password from users where email=? and password=?");
   try {
                    st.setString(1, email);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                       // dispose();
                        //Scene ah = new Scene();
                        //ah.setTitle("Welcome");
                        JOptionPane.showMessageDialog(null, "You have successfully logged in");
                         Stage primaryStage = new Stage();
                         
               Parent root = FXMLLoader.load(getClass().getResource("/Views/AdminHomeInterface.fxml"));
               Scene scene = new Scene(root);
            
            primaryStage.setTitle("Acceuil");
            primaryStage.setScene(scene);
            primaryStage.show();
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Username & Password");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
    }
    
}