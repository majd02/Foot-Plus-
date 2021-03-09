/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Utils.DBConnexion;
import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class LoginMemberController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpwd;
    @FXML
    private Button loginbtn;
    @FXML
    private Button signupbtn;

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
    
     public void loginAction(ActionEvent event) throws Exception{
        String email = tfemail.getText();
        String password = tfpwd.getText();
    
  
    
  
                    PreparedStatement st = (PreparedStatement) c.prepareStatement("Select User_Email, password from membres where User_Email=? and password=?");
   try {
                    st.setString(1, email);
                    st.setString(2, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        //Scene ah = new Scene();
                        //ah.setTitle("Welcome");
                        JOptionPane.showMessageDialog(null, "You have successfully logged in");
                         Stage primaryStage = new Stage();
                         
         Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
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
    
    
    @FXML
    private void SignUp(ActionEvent event){
   
         Stage primaryStage = new Stage();
         Parent root;
         try {
             root = FXMLLoader.load(getClass().getResource("AddMembre.fxml"));
             Scene scene = new Scene(root);
            
            primaryStage.setTitle("Sign Up Page");
            primaryStage.setScene(scene);
            primaryStage.show();
         } catch (IOException ex) {
             Logger.getLogger(LoginMemberController.class.getName()).log(Level.SEVERE, null, ex);
         }
}

    @FXML
    private void Login(ActionEvent event) {
    }
}