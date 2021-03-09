/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membres_abonnemnts_final;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Youssef
 */
public class Membres_Abonnemnts_final extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/LoginMember.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    primaryStage.setTitle("AddMembre");
    primaryStage.setScene(scene);
    primaryStage.show();
    
   /* FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
loader.setController(new MainController(path));
Pane mainPane = loader.load();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
