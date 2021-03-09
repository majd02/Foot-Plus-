/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.ServiceReservation;
import Services.ServiceStade;
import Utils.DataSource;
import static Utils.DataSource.ConnectDb;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import models.Reservation;
import models.Stade;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TablePosition;
import javafx.scene.layout.AnchorPane;
import models.Maptet;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReserverController implements Initializable {

    @FXML
    private Button btnr;
    @FXML
    private ComboBox<Integer> com1;
    @FXML
    private ComboBox<String> com2;
    ObservableList<Stade> optionS ;
    ObservableList<Reservation> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reservation, Integer> col_id;
    @FXML
    private TableColumn<Reservation, Integer> col_h;
    private ObservableList<Reservation> listM;
    private ObservableList<Stade> listR = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    ObservableList<Reservation> list1 = FXCollections.observableArrayList();
    ObservableList<Stade> listS = FXCollections.observableArrayList();
    @FXML
    private TableView<Reservation> table_reservation;
    @FXML
    private Button btns;
    @FXML
    private TextField ftid;
    @FXML
    private TableColumn<?, ?> col_r;
    @FXML
    private TableColumn<Stade, Integer> tid;
    @FXML
    private TableColumn<Stade, String> tnom;
    @FXML
    private TableColumn<Stade, String> tadresse;
    @FXML
    private TableColumn<Stade, Integer> tprixph;
    @FXML
    private TableColumn<Stade, String> tcontact;
    @FXML
    private TableColumn<Stade, String> timage;
    @FXML
    private TableView<Stade> table_stade;
    @FXML
    private TextField mapadress;
    @FXML
    private TextField ftR;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            
            
            com1.setPromptText("Les Stades");
            com2.setPromptText("Chosir l'heure");
            ResultSet rs1 = null;
            try {
                afficherreservation();
            } catch (SQLException ex) {
                Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Connection cnx = DataSource.getInstance().getCnx();
                rs1 = cnx.createStatement().executeQuery("SELECT id_stade FROM stade");
            } catch (SQLException ex) {
                Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs1.next()) {  // loop
                    
                    // Now add the comboBox addAll statement
                    com1.getItems().addAll(rs1.getInt("id_stade"));
                    
                }} catch (SQLException ex) {
                    Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            com2.getItems().addAll("1","2","3","4","5");
            
            travail();
                try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM stade");
            while(rs.next()){
            listS.add(new Stade(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
            
            table_stade.setItems(listS);
            table_stade.refresh();
            
        }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutStadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                afficherstade();
            
            
          
            
        
            
        
            
    }    
    public void travail(){
        try {
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rs1 = cnx.createStatement().executeQuery("SELECT * FROM reservation");
            while(rs1.next()){
            list1.add(new Reservation(rs1.getInt(1),rs1.getInt(2)));
            table_reservation.setItems(list1); 
            
            table_reservation.refresh();
            afficherreservation();
            
        }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutStadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficherreservation();
        } catch (SQLException ex) {
            Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void afficherreservation() throws SQLException{
        col_r.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
          col_h.setCellValueFactory(new PropertyValueFactory<>("heure"));
          col_id.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
          table_reservation.setItems(list1); 
          UpdateTable();
          
    }

    @FXML
    private void Reserver(ActionEvent event) throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();
        int i =com1.getValue();
        int j =Integer.parseInt(com2.getValue());
        ServiceReservation sr = new ServiceReservation();
        
        sr.Ajouter(new Reservation(i,j));
        JOptionPane.showMessageDialog(null,"Réservation Ajoutée");
        UpdateTable();
    }

    @FXML
    private void SelectStade(ActionEvent event) throws SQLException {

    }

    @FXML
    private void SelectHeure(ActionEvent event) {
    }

    @FXML
    private void SupprimerReservation(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Reservation x = gettempReservation(edittedcell);

        if (x != null) {

            int i = x.getId_reservation();
            ServiceReservation cat = new ServiceReservation();

            int s = cat.deleteReservation(i);
            

            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("reservation deleted");
                UpdateTable();
                alert.showAndWait();
                table_reservation.refresh();
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
        col_r.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));    
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
        col_h.setCellValueFactory(new PropertyValueFactory<>("heure"));

         listM = DataSource.getDataReservation();
        
        table_reservation.setItems(listM);
        }

    public Reservation gettempReservation(TableColumn.CellEditEvent edittedCell) {
        Reservation test = table_reservation.getSelectionModel().getSelectedItem();
        return test;
    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            int index = table_reservation.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
    com1.setValue(col_id.getCellData(index));
    com2.setValue(col_h.getCellData(index).toString());
    ftid.setText(col_r.getCellData(index).toString());
 
    
    }

    @FXML
    private void Edit() {
        try {
            UpdateTable();
            Connection cnx = DataSource.getInstance().getCnx();
            cnx = DataSource.ConnectDb();
            int value1 =com1.getValue();
        int value2 =Integer.parseInt(com2.getValue());
            int value3 = Integer.parseInt(ftid.getText());
            String sql ="update reservation set id_stade= '"+value1+"',heure= '"+value2+"' where id_reservation='"+value3+"' ";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst= cnx.prepareStatement(sql);
            pst.execute();
            UpdateTable();
            JOptionPane.showMessageDialog(null, "Updated");
            UpdateTable();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    private void afficherstade(){
       
            tid.setCellValueFactory(new PropertyValueFactory<>("id_stade"));
            tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            tprixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
            tcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            timage.setCellValueFactory(new PropertyValueFactory<>("image"));
            table_stade.setItems(listS);
            
            
            
    }
  

    @FXML
    private void getadressclicked(MouseEvent event) {
         int index = table_stade.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
           mapadress.setText(tadresse.getCellData(index).toString());
    }

    @FXML
    private void SeeLocation(ActionEvent event) {
           
int index = table_stade.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
           mapadress.setText(tadresse.getCellData(index).toString());
           String S = mapadress.getText();
           String[] splitString = S.split(",");
                
                Double d = Double.parseDouble(splitString[0]);
                Double d1 = Double.parseDouble(splitString[1]);
                Maptet test = new Maptet(d, d1);
    }

    @FXML
    private void SearchStade(ActionEvent event) throws SQLException {
          Connection conn = ConnectDb();
      
        String value9 = ftR.getText();
      
            PreparedStatement ps = conn.prepareStatement("select * from stade where nom Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();
            
            while (rs5.next()){   
                
                listR.add(new Stade( rs5.getInt(1),rs5.getString(2), rs5.getString(3), rs5.getInt(4), rs5.getString(5), rs5.getString(6)));               
            }
        tid.setCellValueFactory(new PropertyValueFactory<Stade,Integer>("id_stade"));
        tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tadresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tprixph.setCellValueFactory(new PropertyValueFactory<>("prixph"));
        tcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        timage.setCellValueFactory(new PropertyValueFactory<>("image"));

         table_stade.setItems(listR);
        
    }

   
}
    

