/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.User;
import Entities.sub;
import Entities.stade;
import Services.SubServices;
import Services.UserServices;
import Utils.DBConnexion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class ClientHomeInterfaceController implements Initializable {

    @FXML
    private TextField tfuid;
    @FXML
    private TextField tfdated;
    @FXML
    private TextField tfdatef;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfphoto;
    @FXML
    private TextField tfnumsub;
    @FXML
    private Button subscribe;
    @FXML
    private Button updatesub;
    @FXML
    private Button deletesub;

    @FXML
    private MenuButton mbstade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<stade> stadeList =getStadeList();
        System.out.println(stadeList);
         for (int i=0;i<stadeList.size();i++){
            MenuItem menuItem = new MenuItem(String.valueOf(stadeList.get(i).getNom()));
            menuItem.setOnAction(event1);
              mbstade.getItems().addAll(menuItem);
         }
          
       

    }    
    
  EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
               
                mbstade.setText(((MenuItem)e.getSource()).getText());
                setPrixAndPhoto(((MenuItem)e.getSource()).getText());
                
            }
        };
  
        // add action events to the menuitems
       
     @FXML
        public ObservableList<stade> getStadeList(){
    ObservableList<stade> stadeList = FXCollections.observableArrayList();
    Connection conn = DBConnexion.getInstance().getCnx();
    String query = "SELECT * FROM stade";
    Statement st;
    ResultSet rs;
            try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            stade stade;
            while(rs.next()){
                
            stade = new stade(rs.getInt("id_stade"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("prixph"),rs.getString("contact"),rs.getString("image"));
            stadeList.add(stade);
            }    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    return stadeList;
    }
        
    @FXML
    public void setPrixAndPhoto(String nom){
    ObservableList<stade> stadeList = FXCollections.observableArrayList();
    Connection conn = DBConnexion.getInstance().getCnx();
    String query ="select * from stade where nom ='"+nom+"'";
    Statement st;
    ResultSet rs;
            try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            stade stade;
            while(rs.next()){
                
            stade = new stade(rs.getInt("id_stade"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("prixph"),rs.getString("contact"),rs.getString("image"));
            stadeList.add(stade);
            tfprix.setText(String.valueOf(stadeList.get(0).getPrixph()));
            tfphoto.setText(stadeList.get(0).getImage());
            }    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    
    }

    @FXML
    private void subscribe(ActionEvent event) throws SQLException {
      System.out.println("press");
      SubServices ms = new SubServices();
      ms.AddSub(new sub(Integer.parseInt(tfuid.getText()),mbstade.getText(),Date.valueOf(tfdated.getText()),Date.valueOf(tfdatef.getText()),Float.parseFloat(tfprix.getText()),tfphoto.getText()));
      JOptionPane.showMessageDialog(null, "Subscribed successfully");
      Stage stage = new Stage();
      QRCodegen( stage);
      
    }
    @FXML
        public void QRCodegen(Stage primaryStage) {
        // GENERATE QR CODE
        ByteArrayOutputStream out = QRCode.from("LT Jerry0022").to(ImageType.PNG).withSize(200, 200).stream();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        // SHOW QR CODE
        BorderPane root = new BorderPane();
        Image image = new Image(in);
        ImageView view = new ImageView(image);
        view.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");
        root.setCenter(view);
        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void updatesub(ActionEvent event) {
      SubServices ms = new SubServices();
      sub u = new sub(Integer.parseInt(tfuid.getText()),mbstade.getText(),Date.valueOf(tfdated.getText()),Date.valueOf(tfdatef.getText()),Float.parseFloat(tfprix.getText()),tfphoto.getText());
      ms.UpdateSub(Integer.parseInt(tfnumsub.getText()),u);
      JOptionPane.showMessageDialog(null, "User has been updated");
    }

    @FXML
    private void deletesub(ActionEvent event) {
        String query = "DELETE FROM subs WHERE num_sub =" + tfnumsub.getText() + "";
        executeQuery(query);

    }

    @FXML
    private void menubuttonstade(ActionEvent event) {
    }

    void setContent(User user) {
        System.out.println(user.getId());
        tfuid.setText(String.valueOf(user.getId()));
        
          
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
    private sub viewSub(ActionEvent event) {
   sub sub = null;
    Connection conn = DBConnexion.getInstance().getCnx();
    String query = "SELECT * FROM subs WHERE num_sub =" + tfnumsub.getText() + "";
    Statement st;
    ResultSet rs;
            try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            System.out.print(rs);
            
            while(rs.next()){
                  sub = new sub(rs.getInt("user_id"), rs.getString("stade_id"), rs.getDate("date_debut"), rs.getDate("date_fin"),rs.getFloat("prix"),rs.getString("photo"));
                  tfuid.setText(String.valueOf(sub.getUser_id()));
                  mbstade.setText(sub.getStade_id());
                  tfdated.setText(String.valueOf(sub.getDate_debut()));
                  tfdatef.setText(String.valueOf(sub.getDate_fin()));
                  tfprix.setText(String.valueOf(sub.getPrix()));
                  tfphoto.setText(sub.getPhoto());

                          

            }    
        }catch(Exception ex){
            ex.printStackTrace();
        }
    return sub;
    }

 
    
}
