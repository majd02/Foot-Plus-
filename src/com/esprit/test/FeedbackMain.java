/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

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
import javax.swing.JOptionPane;
/**
 *
 * @author USER
 */
public class FeedbackMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ClientFeedback.fxml")) ;
        Parent root=loader.load() ;
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
