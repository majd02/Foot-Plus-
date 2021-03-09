/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Services.ServiceStade;
import Utils.DataSource;
import static Utils.DataSource.ConnectDb;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import models.Stade;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutStadeController implements Initializable {

    @FXML
    private TextField ftNom;
    @FXML
    private TextField fid;
    @FXML
    private TextField ftAdresse;
    @FXML
    private TextField ftPrixph;
    @FXML
    private TextField ftContact;
    @FXML
    private TextField ftImage;
    @FXML
    private Button btn;
    @FXML
    private TableColumn<Stade, Integer> col_id;
    @FXML
    private TableColumn<Stade, String> col_nom;
    @FXML
    private TableColumn<Stade, String> col_adresse;
    @FXML
    private TableColumn<Stade, Integer> col_prixph;
    @FXML
    private TableColumn<Stade, String> col_contact;
    @FXML
    private TableColumn<Stade, String> col_image;
    @FXML
    private Button btnd;
    @FXML
    private Button btnu;
    @FXML
    private TableView<Stade> table_stade;
    private Connection cnx = null ;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Stade> listM;
    private ObservableList<Stade> listS = FXCollections.observableArrayList();
    private ObservableList<Stade> dataList;
    

    ObservableList<Stade> list = FXCollections.observableArrayList();
    ResultSet rs1 = null;
    @FXML
    private TextField ftR;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
         try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM stade");
            while(rs.next()){
            list.add(new Stade(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
            
            table_stade.setItems(list);
            table_stade.refresh();
            
        }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutStadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AfficherStade();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutStadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    

    public void AfficherStade() throws SQLException{
          col_id.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
          col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
          col_prixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
          col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
          col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        UpdateTable();
        table_stade.setItems(list);
        
    }
    
    
    

    @FXML
    private void AjouterStade(ActionEvent event) throws SQLException {
        ServiceStade sp = new ServiceStade();
        int i =Integer.parseInt(ftPrixph.getText());
        
        sp.Ajouter(new Stade(ftNom.getText(),ftAdresse.getText(),i,ftContact.getText(),ftImage.getText()));
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
         col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         col_prixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
         col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
         col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        JOptionPane.showMessageDialog(null,"Stade Ajoutée");
        
        UpdateTable();
        table_stade.refresh();
    }

    @FXML
    private void SupprimerStade(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Stade x = gettempStade(edittedcell);
        UpdateTable();

        if (x != null) {

            int i = x.getId_stade();
            ServiceStade cat = new ServiceStade();

            int s = cat.deleteStade(i);
            UpdateTable();
            
            
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Stade deleted");
                
                alert.showAndWait();
                table_stade.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
 public Stade gettempStade(TableColumn.CellEditEvent edittedCell) {
        Stade test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }
        

    


    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = table_stade.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    fid.setText(col_id.getCellData(index).toString());
    ftNom.setText(col_nom.getCellData(index));
    ftAdresse.setText(col_adresse.getCellData(index));
    ftPrixph.setText(col_prixph.getCellData(index).toString());
    ftContact.setText(col_contact.getCellData(index));
    ftImage.setText(col_image.getCellData(index));
    
    
    }
    @FXML
    public void Edit (){   
        try {
            UpdateTable();
            cnx = DataSource.ConnectDb();
            String value1 = ftNom.getText();
            int value7 = Integer.parseInt(fid.getText());
            
            String value2 = ftAdresse.getText();
            String value3 = ftPrixph.getText();
            String value4 = ftContact.getText();
            String value5 = ftImage.getText();
            String sql = "update stade set nom= '"+value1+"',adresse= '"+value2+"',prixph= '"+
                    value3+"',contact= '"+value4+"',image= '"+value5+"' where id_stade='"+value7+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated");
            UpdateTable();
            
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    @FXML
    private void SearchStade() throws SQLException {
       Connection conn = ConnectDb();
      
        String value9 = ftR.getText();
      
            PreparedStatement ps = conn.prepareStatement("select * from stade where nom Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();
            
            while (rs5.next()){   
                
                listS.add(new Stade( rs5.getInt(1),rs5.getString(2), rs5.getString(3), rs5.getInt(4), rs5.getString(5), rs5.getString(6)));               
            }
        col_id.setCellValueFactory(new PropertyValueFactory<Stade,Integer>("id_stade"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_prixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
        col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));

         table_stade.setItems(listS);
        
        }
     
        
  
    
    
    
    
     public void UpdateTable() throws SQLException{
            
        col_id.setCellValueFactory(new PropertyValueFactory<Stade,Integer>("id_stade"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_prixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
        col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
         listM = DataSource.getDataStade();
        
        table_stade.setItems(listM);
        }
}
        
            
    
