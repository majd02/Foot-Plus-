/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myap.Services.ServiceFeedback;
import com.mycompany.myap.Services.ServiceReclamation;
import com.mycompany.myap.entities.Feedback;
import com.mycompany.myap.entities.Reclamation;

/**
 *
 * @author USER
 */
public class AddFeedbackForm extends Form {
    public AddFeedbackForm(Form previous) {
    
        setTitle("Passer une Reclamation");
 
        TextField tfdescription = new TextField("","description");
        TextField tfrate = new TextField("","rate");
        
        TextField tfidu = new TextField("","idu");
        
    Button btnnValider = new Button("Add");
           
        
        btnnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfidu.getText().length()==0)||(tfdescription.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Feedback s = new Feedback(String.valueOf(tfdescription.getText().toString()).toString(), 
                    
                    
                    Integer.parseInt(tfrate.getText().toString()),
            String.valueOf(tfidu.getText()
                    .toString()).toString());
                    System.err.println("data stade=="+s);
                     ServiceFeedback.getInstance().addStade(s);
                    idialog.dispose();
                    Dialog.show("Succes", "NOTE Added", new Command("OK"));
                   
                   
            }
        }

           
        });
        addAll(tfdescription,tfrate,tfidu,btnnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
}
