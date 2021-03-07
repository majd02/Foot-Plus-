/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.Suptech.Entite.produit;
import com.Suptech.Service.Serviceproduit;
import com.Suptech.Utils.DataBase;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * FXML Controller class
 *
 * @author moham
 */
public class ProduitFxController implements Initializable {

    @FXML
    private TextField fid;
    @FXML
    private TextField fnom;
    @FXML
    private TextField fprix;
    @FXML
    private TextField fdesc;
    @FXML
    private TextField fimage;
    @FXML
    private Button bt;
    @FXML
    private TableView<produit> table_produit;
    @FXML
    private TableColumn<produit, Integer> colid;
    @FXML
    private TableColumn<produit, String> colnom;
    @FXML
    private TableColumn<produit, Double> colprix;
    @FXML
    private TableColumn<produit, String> coldesc;
    @FXML
    private TableColumn<produit, String> colimage;
    ObservableList<produit> list = FXCollections.observableArrayList();
    private ObservableList<produit> listM;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = DataBase.getInstance().getConnection();
        ResultSet rs = null;
        try {
            rs = cnx.createStatement().executeQuery("SELECT * FROM produit");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitFxController.class.getName()).log(Level.SEVERE, null, ex);
        }
           try {
    while(rs.next()){
        list.add(new produit(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5)));
        
        table_produit.setItems(list);
        table_produit.refresh();
        // TODO
    }   } catch (SQLException ex) {
            Logger.getLogger(ProduitFxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficherproduit();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitFxController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
}    

    @FXML
    private void ajouterproduit(ActionEvent event) throws SQLException {
    Serviceproduit sp = new Serviceproduit();
    Double p =Double.parseDouble(fprix.getText());
    sp.ajouter(new produit(fnom.getText(),p,fdesc.getText(),fimage.getText()));
             colid.setCellValueFactory(new PropertyValueFactory<>("id"));
          colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          coldesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
          colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
    
    JOptionPane.showMessageDialog(null,"Produit Ajoutée");
    UpdateTable();
    
    }
      public void afficherproduit() throws SQLException{
          colid.setCellValueFactory(new PropertyValueFactory<>("id"));
          colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          coldesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
          colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        
      UpdateTable();
        table_produit.setItems(list);
        
        
        
    }
       public void UpdateTable() throws SQLException{
            
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
         listM = DataBase.getDataProduit();
        
        table_produit.setItems(listM);
        }

    @FXML
    private void supprimerproduit(ActionEvent event) throws SQLException {
           TableColumn.CellEditEvent edittedcell = null;
        produit x = gettempproduit(edittedcell);
        UpdateTable();

        if (x != null) {

            int i = x.getId();
            Serviceproduit cat = new Serviceproduit();

            int s = cat.deleteproduit(i);
            UpdateTable();
            
            
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Stade deleted");
                
                alert.showAndWait();
                table_produit.refresh();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
 public produit gettempproduit(TableColumn.CellEditEvent edittedCell) {
        produit test = table_produit.getSelectionModel().getSelectedItem();
        return test;
    }

   // private produit gettempproduit(TableColumn.CellEditEvent edittedcell) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

