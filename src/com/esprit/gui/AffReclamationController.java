/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.models.Reclamation;
import com.esprit.service.ServiceReclamation;
import com.esprit.util.DataSource;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import static java.util.Collections.list;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AffReclamationController implements Initializable {

    @FXML
    private ComboBox<String> prod;
    @FXML
    private TextField prod1;
    @FXML
    private TextField iddd;
    @FXML
    private TextField type;
    @FXML
    private TextField sujet;
    @FXML
    private TextField desc;
    @FXML
    private TextField etat;
    @FXML
    private TextField user;
    @FXML
    private Button ajout;
    @FXML
    private Button cc;
    @FXML
    private Button btnu;
    @FXML
    private Button ccxc;
     @FXML
    private Button supprimer;
    @FXML
    private TableView<Reclamation> RECl;
    @FXML
    private TableColumn<?, ?> dd;
    @FXML
    private TableColumn<?, ?> tt;
    @FXML
    private TableColumn<?, ?> oo;
    @FXML
    private TableColumn<?, ?> ss;
    @FXML
    private TableColumn<?, ?> ds;
    @FXML
    private TableColumn<?, ?> dt;
    @FXML
    private TableColumn<?, ?> dtt;
    @FXML
    private TableColumn<?, ?> usr;

    
    private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    ObservableList<Reclamation> listM;
    /**
     * Initializes the controller class.
     */
    ObservableList<Reclamation> list = FXCollections.observableArrayList();
    @FXML
    private Button refresh;
    @FXML 
    private Button btnu2;
    @FXML
    private TextField filterField;
    @FXML
    private Button ftMail;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try { 
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(AffReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
           afficherReclamation();
    }
        private void afficherReclamation(){
          dd.setCellValueFactory(new PropertyValueFactory<>("id"));
          tt.setCellValueFactory(new PropertyValueFactory<>("type"));
          oo.setCellValueFactory(new PropertyValueFactory<>("ido"));
          ss.setCellValueFactory(new PropertyValueFactory<>("sujet"));
          ds.setCellValueFactory(new PropertyValueFactory<>("description"));
          dt.setCellValueFactory(new PropertyValueFactory<>("date"));
           dtt.setCellValueFactory(new PropertyValueFactory<>("etat"));
            usr.setCellValueFactory(new PropertyValueFactory<>("User"));
        
        
        RECl.setItems(list);
    }
        public void initialiserlist() throws SQLException{
             try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM reclamation");
            while(rs.next()){
            list.add(new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
        }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
              Connection cnx = DataSource.getInstance().getCnx();
            rs = cnx.createStatement().executeQuery("SELECT nom FROM stade");
           while(rs.next())

            // Now add the comboBox addAll statement
                prod.getItems().addAll(rs.getString("nom"));
           
        }
        // TODO

    public Reclamation gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Reclamation test = RECl.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    private void Mailing(ActionEvent event) {
        ftMail.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
      @FXML
    private void Feedback(ActionEvent event) {
        btnu2.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("ClientFeedback.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    @FXML
    private void refreshReclamation() throws SQLException {
         
                list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
                 type.setText("");
    /*prod.setValue("");*/
    sujet.setText("");
    desc.setText("");
    
    prod1.setText("");
    etat.setText("");
    user.setText("");
    }
    @FXML
     public void recherche(){
    ServiceReclamation re= new ServiceReclamation() ;
    List<Reclamation>results = new ArrayList<>();
    results = re.afficher();
     FilteredList<Reclamation> filteredData = new FilteredList<>(list , b -> true);
		Reclamation r = new Reclamation();
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (reclamation.getIdo().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (reclamation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(r.getSujet()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(RECl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		RECl.setItems(sortedData);
               
        
    }
    @FXML
    public void Edit () throws SQLException{   
        try {
            cnx = DataSource.ConnectDb();
            String value0 = iddd.getText();
            String value2 = prod.getValue();
            
            String value6 = prod1.getText();
            
            String value3 = sujet.getText();
            
            String value1 = type.getText();
            String value4 = desc.getText();
            String value7 = etat.getText();
            String value8 = user.getText();
            String sql = "update reclamation set type= '"+value1+"',ido= '"+value2+"',sujet= '"+
                    value3+"',description= '"+value4+"',date= '"+value6+"',etat= '"+value7+"',idu= '"+value8+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
            UpdateTable();
                list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
            JOptionPane.showMessageDialog(null, "Update");
            iddd.setText("");
             list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
    type.setText("");
    prod.setValue("");
    sujet.setText("");
    desc.setText("");
    
    prod1.setText("");
    etat.setText("");
    user.setText("");
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
    }
      @FXML
    private void AjoutReclamation(ActionEvent event) throws SQLException {
        ServiceReclamation r = new ServiceReclamation();
        
        r.ajouter(new Reclamation(type.getText(),prod.getValue(),sujet.getText(),desc.getText(),etat.getText(),user.getText()));
        JOptionPane.showMessageDialog(null,"Reclamation Ajoutée");
         list.clear();
                initialiserlist(); 
                afficherReclamation();
                list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
    }
    @FXML
    private void supprimerrc(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServiceReclamation cat = new ServiceReclamation();

            int s = cat.deletereclamation(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reclamation deleted");
                alert.showAndWait();
                UpdateTable();
                list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
                  iddd.setText("");
    type.setText("");
    prod.setValue("");
    sujet.setText("");
    desc.setText("");
    
    prod1.setText("");
    etat.setText("");
    user.setText("");
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
    public void UpdateTable() throws SQLException{
         dd.setCellValueFactory(new PropertyValueFactory<>("id"));
          tt.setCellValueFactory(new PropertyValueFactory<>("type"));
          oo.setCellValueFactory(new PropertyValueFactory<>("ido"));
          ss.setCellValueFactory(new PropertyValueFactory<>("sujet"));
          ds.setCellValueFactory(new PropertyValueFactory<>("description"));
          dt.setCellValueFactory(new PropertyValueFactory<>("date"));
           dtt.setCellValueFactory(new PropertyValueFactory<>("etat"));
            usr.setCellValueFactory(new PropertyValueFactory<>("ido"));
         listM = DataSource.getDataReclamation();
        
        RECl.setItems(listM);
    }
    @FXML
    private void refuserReclamation(ActionEvent event) throws SQLException {

        int i;
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);
        if (x != null) {

            int c = x.getId();
            String etats = "Rejected";

            ServiceReclamation sc = new ServiceReclamation();

            Reclamation rec = new Reclamation(c, etats);

            i = sc.updateEtat(rec);

            if (i == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("reclamation updated");
                alert.showAndWait();
               list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
iddd.setText("");
    type.setText("");
    prod.setValue("");
    sujet.setText("");
    desc.setText("");
    
    prod1.setText("");
    etat.setText("");
    user.setText("");
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
    }
@FXML
    private void AccepterReclamation(ActionEvent event) throws SQLException {

        int i;
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);

        if (x != null) {
            int c = x.getId();
            String etats = "Accepted";

            ServiceReclamation sc = new ServiceReclamation();

            Reclamation rec = new Reclamation(c, etats);

            i = sc.updateEtat(rec);

            if (i == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("reclamation updated");
                alert.showAndWait();
                list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
                iddd.setText("");
       type.setText("");
    prod.setValue("");
    sujet.setText("");
    desc.setText("");
    
    prod1.setText("");
    etat.setText("");
    user.setText("");
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }

    }

 

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = RECl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
   iddd.setText(dd.getCellData(index).toString());
    type.setText(tt.getCellData(index).toString());
    prod.setValue(oo.getCellData(index).toString());
    sujet.setText(ss.getCellData(index).toString());
    desc.setText(ds.getCellData(index).toString());
    
    prod1.setText(dt.getCellData(index).toString());
    etat.setText(dtt.getCellData(index).toString());
    user.setText(usr.getCellData(index).toString());
    
    
    
    }
    
    }    


  
    

