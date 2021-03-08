/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.esprit.models.Feedback;
import com.esprit.models.Reclamation;
import com.esprit.service.ServiceFeedback;
import com.esprit.service.ServiceReclamation;
import com.esprit.util.DataSource;
import java.io.IOException;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ClientFeedbackController implements Initializable {

    @FXML
    private TableView<Feedback> RECl;
    @FXML
    private ComboBox<String> prod;
    @FXML
    private TableColumn<?, ?> dd;
    @FXML
    private TableColumn<?, ?> rt;
    @FXML
    private TableColumn<?, ?> ds;
    @FXML
    private TableColumn<?, ?> dt;
    @FXML
    private TableColumn<?, ?> usr;
    
    @FXML
    private TextField filterField;
    
      private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    ObservableList<Feedback> listM;
    /**
     * Initializes the controller class.
     */
    ObservableList<Feedback> list = FXCollections.observableArrayList();
    @FXML
    private TextField desc;
    @FXML
    private TextField user;
    @FXML
    private Button ajout;
    @FXML
    private Button buttonRetourAjouterReclamation;
    @FXML
    private Button supprimer;
    /**
     * Initializes the controller class.
     */
   
          @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(ClientFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           afficherReclamation();
    }    
     public void initialiserlist() throws SQLException{
             try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM feedback");
            while(rs.next()){
            list.add(new Feedback(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5)));
        }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
            prod.getItems().addAll("1","2","3","4","5");
           
           }
      @FXML
    private void AjoutFeedback(ActionEvent event) throws SQLException {
        ServiceFeedback o = new ServiceFeedback();
        int i = Integer.parseInt(prod.getValue());
            
            if (list.contains(user.getText())) {
                
                o.ajouter(new Feedback(desc.getText(),i,user.getText()));
                
                JOptionPane.showMessageDialog(null,"User a deja noté ");
            }
             else {
                o.ajouter(new Feedback(desc.getText(),i,user.getText()));
                JOptionPane.showMessageDialog(null,"Feedback Ajoutée"); }
         list.clear();
                initialiserlist(); 
                afficherReclamation();
                list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
    }
    @FXML
     public void recherche(){
    ServiceFeedback ff= new ServiceFeedback();
    List<Feedback>results = new ArrayList<>();
    results = ff.afficher();
     FilteredList<Feedback> filteredData = new FilteredList<>(list , b -> true);
		Reclamation r = new Reclamation();
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(feedback -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if (feedback.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(r.getSujet()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Feedback> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(RECl.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		RECl.setItems(sortedData);
               
        
    }

    private void afficherReclamation() {  dd.setCellValueFactory(new PropertyValueFactory<>("id"));
          ds.setCellValueFactory(new PropertyValueFactory<>("description"));
          dt.setCellValueFactory(new PropertyValueFactory<>("date"));
          
          rt.setCellValueFactory(new PropertyValueFactory<>("rate"));
            usr.setCellValueFactory(new PropertyValueFactory<>("User"));
            
            
        RECl.setItems(list);
    }
public Feedback gettempFeedback(TableColumn.CellEditEvent edittedCell) {
        Feedback test = RECl.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    private void supprimerrc(ActionEvent event) throws SQLException  {
        TableColumn.CellEditEvent edittedcell = null;
        Feedback x = gettempFeedback(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServiceFeedback cat = new ServiceFeedback();

            int s = cat.deletefeedback(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Feedback deleted");
                alert.showAndWait();
                
                list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
                  
   
    prod.setValue("");
  
    desc.setText("");
    
    
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
        }
    
    

