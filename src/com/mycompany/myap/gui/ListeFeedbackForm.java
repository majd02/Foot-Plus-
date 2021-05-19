/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myap.Services.ServiceFeedback;
import com.mycompany.myap.Services.ServiceReclamation;
import com.mycompany.myap.entities.Feedback;
import com.mycompany.myap.entities.Reclamation;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ListeFeedbackForm extends Form {
    Form current;
ArrayList<Feedback> data = new ArrayList<>();

public ListeFeedbackForm(Form previous) {
    setTitle("Listes  Des Reclamation");
    data = ServiceFeedback.getInstance().getAllCours();
    Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    for (int i=0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Reclamation u = new Reclamation();
        u.setId(data.get(i).getId());
        
   
     
        u.setDescription(data.get(i).getDescription());
      
          u.setUser(data.get(i).getUser());
        Label separation = new Label("----------------------------");
    
        Label contact = new Label("description : "+ data.get(i).getDescription());
        Label rate = new Label("rate : "+ data.get(i).getRate());
         Label image = new Label("user : "+ data.get(i).getUser());
    
        
//        Button modif = new Button("Modifier");
//        Button supp = new Button("Supprimer");
        //CheckBox box = new CheckBox();
        
        
//        modif.addActionListener(e -> new ModifierAbonnementForm(current,ab).show());
//        supp.addActionListener(new ActionListener(){
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            ServiceAbonnement.getInstance().deleteAbonnement(ab);
//            Dialog.show("Success", "Memory Deleted Successfully.", "OK", "Cancel");
//        }
//        });
        
        x.addAll( contact,rate,image);
        
        //xx.addAll(supp,modif);
        y.addAll(x,xx,separation);
    }
    
    
    
    
    addAll(y);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
}
    
}
