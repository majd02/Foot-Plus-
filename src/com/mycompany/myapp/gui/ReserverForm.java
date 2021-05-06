/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Services.StadeService;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.entities.Stade;

/**
 *
 * @author asus
 */
public class ReserverForm extends Form{
     public ReserverForm(Stade stade) {
        setTitle("Reserver le stade");
       
        
        TextField tfid = new TextField(stade.getId_stade());
        
        TextField tfheure = new TextField("","heure");
        
        TextField tfnom = new TextField(stade.getNom());
        TextField tfdate = new TextField("","date");
         
        
        
        Button btnValider = new Button("Add");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Reservation s = new Reservation(
            Integer.parseInt(tfheure.getText().toString()),
                    Integer.parseInt(tfid.getText().toString()),
            String.valueOf(tfnom.getText()
                    .toString()).toString(),String.valueOf(tfdate.getText()
                    .toString()).toString());
                    System.err.println("data stade=="+s);
                    StadeService.getInstance().addReservation(s);
                    idialog.dispose();
                    Dialog.show("Succes", "Reservation Added", new Command("OK"));
                   
            
        }
        });
        addAll(tfnom,tfid,tfheure,tfdate,btnValider);
       
    }
    
}


