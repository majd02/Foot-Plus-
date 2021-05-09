/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Services.StadeService;

import com.mycompany.myapp.entities.Stade;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ListStadeForm extends Form{
Form current;
ArrayList<Stade> data = new ArrayList<>();

public ListStadeForm(Form previous) {
    setTitle("Listes  Des Stades");
    data = StadeService.getInstance().getAllCours();
    Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    for (int i=0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Stade u = new Stade();
        u.setId_stade(data.get(i).getId_stade());
        
        
        u.setNom(data.get(i).getNom());
        u.setAdresse(data.get(i).getAdresse());
        u.setPrixph(data.get(i).getPrixph());
        u.setContact(data.get(i).getContact());
        u.setImage(data.get(i).getImage());
        
        Label separation = new Label("----------------------------");
         Button modif = new Button("Modifier");
       Button supp = new Button("Supprimer");
       Button reserver = new Button("Reserver");
        
         
        Label nom = new Label("nom : " + data.get(i).getNom());
        Label adresse = new Label("Adresse : "+ data.get(i).getAdresse());
        Label prix  = new Label("prix : "+ data.get(i).getPrixph());
        Label contact = new Label("Contact : "+ data.get(i).getContact());
        Label image = new Label("image : "+ data.get(i).getImage());

        //CheckBox box = new CheckBox();
        
        
//        modif.addActionListener(e -> new ModifierAbonnementForm(current,ab).show());
        supp.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            StadeService.getInstance().deleteStade(u.getId_stade()); 
            Dialog.show("Success", "Stade Deleted Successfully.", "OK", "Cancel");
            new HomeForm().show();
       }
       });
         modif.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
             
            new ModifStadeForm(u);
       }
       });

          
        x.addAll( nom,prix,supp,modif);
        //xx.addAll(supp,modif);
        y.addAll(x,xx,separation);
    }
    
    
    
    
    
    addAll(y);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
}
}

