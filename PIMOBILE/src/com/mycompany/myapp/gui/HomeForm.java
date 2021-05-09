/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author asus
 */
public class HomeForm extends Form{
    Form current;
    public HomeForm(){
        current = this;
        setTitle("Home Page");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Ajouter un Stade");
        Button btnListTasks = new Button("Liste des Stades");
         Button btnClient = new Button("Reserver un stade");

        btnAddTask.addActionListener(e -> new AddStadeForm(current).show());
        btnListTasks.addActionListener(e -> new ListStadeForm(current).show());
        btnClient.addActionListener(e -> new ReservationClient(current).show());
        addAll(btnAddTask, btnListTasks,btnClient);
    }
}
