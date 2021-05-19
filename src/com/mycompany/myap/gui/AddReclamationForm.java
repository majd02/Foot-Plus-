/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myap.Services.ServiceReclamation;
import com.mycompany.myap.entities.Reclamation;
import com.mycompany.myap.entities.Stade;

/**
 *
 * @author USER
 */
public class AddReclamationForm extends Form {
    public AddReclamationForm(Stade stade) {
        setTitle("Passer une Reclamation");
       
        TextField tfType = new TextField("","type");     
        TextField tfido = new TextField(stade.getNom(),"ido");
        TextField tfsujet = new TextField("","sujet");
        
        TextField tfdescription = new TextField("","description");
        TextField tfidu = new TextField("","idu");
     
         
      
        
        Button B = new Button("Add");
        
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfidu.getText().length()==0)||(tfsujet.getText().length()==0)||(tfdescription.getText().length()==0)||(tfidu.getText().length()==0)||(tfType.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Reclamation s = new Reclamation(String.valueOf(tfType.getText()
                    .toString()).toString(),String.valueOf(tfido.getText()
                    .toString()).toString(),String.valueOf(tfsujet.getText().toString()).toString()
            ,String.valueOf(tfdescription.getText().toString()).toString(),
            String.valueOf(tfidu.getText()
                    .toString()).toString());
                    System.err.println("data stade=="+s);
                     ServiceReclamation.getInstance().addStade(s);
                    idialog.dispose(); 
                   
               ToastBar.showErrorMessage("Reclamation ajoute", FontImage.MATERIAL_ERROR); 
                   
            }
        }
            

           
        });
        addAll(tfido,tfsujet,tfdescription,tfidu,tfType,B);
    }

   
}
