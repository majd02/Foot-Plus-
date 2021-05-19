/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapLayout;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myap.Services.StadeService;
import com.mycompany.myap.entities.Reservation;
import com.mycompany.myap.entities.Stade;
import java.util.ArrayList;
 import java.util.StringTokenizer;

/**
 *
 * @author asus
 */
public class ReserverForm extends Form{
     
     public ReserverForm(Stade stade) {
        setTitle("Reserver le stade");
       
        TextField tfid = new TextField(String.valueOf(stade.getId_stade()));
        
        TextField tfheure = new TextField("","heure ");
        
        TextField tfnom = new TextField(stade.getNom(),"Stade Name");
        
        
        Picker datePicker = new Picker();
         datePicker.setType(Display.PICKER_TYPE_DATE);
         Button anuuler = new Button("Cancel");
        Button btnValider = new Button("Add");
         anuuler.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
             
            new HomeForm().show();
       }
       });
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {  
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Reservation s = new Reservation(
            Integer.parseInt(tfheure.getText().toString()),
                    Integer.parseInt(tfid.getText().toString()),
            String.valueOf(tfnom.getText()
                    .toString()).toString(),String.valueOf(datePicker.getText()
                    .toString()).toString());
                    System.err.println("data Reservation=="+s);
                    StadeService.getInstance().addReservation(s);
                    idialog.dispose(); 
                    Dialog.show("Succes", "Reservation Added", new Command("OK"));
        }
        });
        addAll(tfheure,datePicker,btnValider,anuuler);
    }
     }



