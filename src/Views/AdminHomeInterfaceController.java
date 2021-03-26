/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.User;
import Services.UserServices;
import Utils.DBConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class AdminHomeInterfaceController implements Initializable {

    @FXML
    private TableView<User> tableviewusers;
    @FXML
    private TableColumn<User, Integer> colid;
    @FXML
    private TableColumn<User, String> colname;
    @FXML
    private TableColumn<User, String> collastname;
    @FXML
    private TableColumn<User, String> colemail;
    @FXML
    private TableColumn<User, String> colpwd;
    @FXML
    private TableColumn<User, String> colgender;
    @FXML
    private TableColumn<User, String> colphoto;
    @FXML
    private TableColumn<User, Integer> colphone;
    @FXML
    private TableColumn<User, Date> colbirthday;
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
    private TextField tfphoto;
    @FXML
    private TextField tfphone;
    @FXML
    private TextField tfbirthday;
    @FXML
    private TextField tfid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public ObservableList<User> getUsersList(){
    ObservableList<User> userlist = FXCollections.observableArrayList();
    Connection conn = DBConnexion.getInstance().getCnx();
    String query = "SELECT * FROM users";
    Statement st;
    ResultSet rs;
            try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            User users;
            while(rs.next()){
            users = new User(rs.getInt("id"), rs.getString("name"), rs.getString("lastname"), rs.getString("email"),rs.getString("password"),rs.getString("gender"),rs.getString("photo"),rs.getInt("phone"),rs.getDate("birthday"));
            userlist.add(users);
            }    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    return userlist;
    }
    
    
            public void showUsers(){
        ObservableList<User> list = getUsersList();
        
        colid.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        collastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
        colemail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        colpwd.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        colgender.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        colphoto.setCellValueFactory(new PropertyValueFactory<User, String>("photo"));
        colphone.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone"));
        colbirthday.setCellValueFactory(new PropertyValueFactory<User, Date>("birthday"));
        if(tfid.getText()!=null){
        }

         
         tableviewusers.setItems(list);
        
        
    }
    private void executeQuery(String query) {
        Connection conn = DBConnexion.getInstance().getCnx();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void deleteuser(ActionEvent event) {
        String query = "DELETE FROM users WHERE id =" + tfid.getText() + "";
        executeQuery(query);
        showUsers();
    }
  @FXML
    private User viewUser(ActionEvent event) {
   User user = null;
    Connection conn = DBConnexion.getInstance().getCnx();
    String query = "SELECT * FROM users WHERE id =" + tfid.getText() + "";
    Statement st;
    ResultSet rs;
            try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            System.out.print(rs);
            
            while(rs.next()){
                  user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("lastname"), rs.getString("email"),rs.getString("password"),rs.getString("gender"),rs.getString("photo"),rs.getInt("phone"),rs.getDate("birthday"));
                  tfname.setText(user.getName());
                  tflastname.setText(user.getLastname());
                  tfemail.setText(user.getEmail());
                  tfpwd.setText(user.getPassword());
                  tfgender.setText(user.getGender());
                  /*tfphone.setText(Integer.parseInt(user.getPhone()));*/
                  tfphoto.setText(user.getPhoto());
                  tfbirthday.setText(user.getBirthday().toString());
                          

            }    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    return user;
    }
    
    @FXML
    private void updateuser(ActionEvent event) {
      UserServices ms = new UserServices();
      User u = new User(tfname.getText(),tflastname.getText(),tfemail.getText(),tfpwd.getText(),tfgender.getText(),tfphoto.getText(),Integer.parseInt(tfphone.getText()),Date.valueOf(tfbirthday.getText()));
      ms.UpdateUser(Integer.parseInt(tfid.getText()),u);
      JOptionPane.showMessageDialog(null, "User has been updated");
    }
    
}
