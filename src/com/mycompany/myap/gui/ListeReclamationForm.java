/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myap.Services.ServiceReclamation;
import com.mycompany.myap.entities.Reclamation;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ListeReclamationForm extends Form {
    Form current;
ArrayList<Reclamation> data = new ArrayList<>();

public ListeReclamationForm(Form previous) {
    setTitle("Listes  Des Reclamation");
    data = ServiceReclamation.getInstance().getAllCours();
    Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    for (int i=0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Reclamation u = new Reclamation();
        
     
        
        
        
        
        
        
        
        
        
        
             TextField tfemail = new TextField("","email");
        
        u.setId(data.get(i).getId());
        
        
        u.setType(data.get(i).getType());
        u.setIdo(data.get(i).getIdo());
        u.setSujet(data.get(i).getSujet());
        u.setDescription(data.get(i).getDescription());
        u.setUser(data.get(i).getUser());
        Label separation = new Label("----------------------------");
        Label nom = new Label("type : " + data.get(i).getType());
        Label adresse = new Label("ido : "+ data.get(i).getIdo());
        Label prix  = new Label("sujet: "+ data.get(i).getSujet());
        Label contact = new Label("description : "+ data.get(i).getDescription());
        Label image = new Label("user : "+ data.get(i).getUser());
  
//        Button modif = new Button("Modifier");
//        Button supp = new Button("Supprimer");
        //CheckBox box = new CheckBox();
        Button modif = new Button("Modifier");
        Button Accept = new Button("accepter");
        Button Reject = new Button("refuser");
         Button email = new Button("mailing");
       Button supp = new Button("Supprimer");
        
//        supp.addActionListener(new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            ServiceAbonnement.getInstance().deleteAbonnement(ab);
//            Dialog.show("Success", "Memory Deleted Successfully.", "OK", "Cancel");
//        }
//        });
supp.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().deleteStade(u.getId()); 
            Dialog.show("Success", "Stade Deleted Successfully.", "OK", "Cancel");
            new HomeForm().show();
       }

            
       });
email.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
try {
                MailSender sm = new MailSender();
                sm.Recm(tfemail.getText());
            
            } catch (Exception ex) {
                ex.printStackTrace();

            }          
       }

            
       });

Accept.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().accepter(u.getId()); 
            Dialog.show("Success", "Reclamation Accepted Successfully.", "OK", "Cancel");
            new HomeForm().show();
       }

            
       });
Reject.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            ServiceReclamation.getInstance().refuser(u.getId()); 
            Dialog.show("Success", "Reclamation Rejected Successfully.", "OK", "Cancel");
            new HomeForm().show();
       }

            
       });
         modif.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
             
            new ModifReclamationForm(u);
       }
       });
        
        x.addAll( nom,adresse ,supp , modif,Accept,Reject);
        
        //xx.addAll(supp,modif);
        y.addAll(x,xx,separation,tfemail,email);
    }
     Button capture = new Button("capture");
     
           Form hi = new Form("ShareButton");
ShareButton sb = new ShareButton();
sb.setText("Share Screenshot");
hi.add(sb);

Image screenshot = Image.createImage(hi.getWidth(), hi.getHeight());
hi.revalidate();
hi.setVisible(true);
hi.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
sb.setImageToShare(imageFile, "image/png");
 
    
    
    addAll(y);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
}
  
}
