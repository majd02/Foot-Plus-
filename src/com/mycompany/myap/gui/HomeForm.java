/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author USER
 */
public class HomeForm extends Form{
 Form current;
    public HomeForm(){
        current = this;
        setTitle("Home Page");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Ajouter un Stade");
        Button btnddAddTask = new Button("Liste feedback ");
      
        Button btndAddTask = new Button("Noter !");
        Button btnListTasks = new Button("Liste des Stades");

        btnAddTask.addActionListener(e -> new ListLivraison(current).show());
        
        btndAddTask.addActionListener(e -> new ReservationClient(current).show());
       btnddAddTask.addActionListener(e -> new ListLivreur(current).show());
 
        btnListTasks.addActionListener(e -> new Reclamamtion(current).show());
        addAll(btnAddTask,btndAddTask,btnddAddTask, btnListTasks);
    }   
}
