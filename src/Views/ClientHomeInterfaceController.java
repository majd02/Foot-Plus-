/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class ClientHomeInterfaceController implements Initializable {

    @FXML
    private TextField tfuid;
    @FXML
    private TextField tfsid;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
