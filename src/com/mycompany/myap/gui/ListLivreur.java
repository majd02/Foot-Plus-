/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.gui;



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
import com.mycompany.myap.Services.LivreurService;
import com.mycompany.myap.entities.Livreur;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ListLivreur extends Form{
Form current;
ArrayList<Livreur> data = new ArrayList<>();

public ListLivreur(Form previous) {
    setTitle("Liste Des Livreurs");
    data = LivreurService.getInstance().getAllCours();
    Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    for (int i=0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        
        Livreur l = new Livreur();
        
        
        l.setIdLivreur(data.get(i).getIdLivreur());
        l.setNomLivreur(data.get(i).getNomLivreur());
        l.setPrenomLivreur(data.get(i).getPrenomLivreur());
        l.setEmail(data.get(i).getEmail());
        l.setAdresseLivreur(data.get(i).getAdresseLivreur());   
        l.setNumTelephoneLivreur(data.get(i).getNumTelephoneLivreur());
      
                  
                  
        Button modif = new Button("Modifier");
                  
        Label separation = new Label("----------------------------");
        Label idLivreur = new Label(""+ data.get(i).getIdLivreur());
        Label nom = new Label( data.get(i).getNomLivreur());
        Label prenom = new Label(data.get(i).getPrenomLivreur());
        Label email = new Label(data.get(i).getEmail());
        Label adresse = new Label( data.get(i).getAdresseLivreur());
        Label numTelephone = new Label("  "+ data.get(i).getNumTelephoneLivreur());
    
        
    
        
//        Button modif = new Button("Modifier");
       Button supp = new Button("Supprimer");
        //CheckBox box = new CheckBox();
        
        
//        modif.addActionListener(e -> new ModifierAbonnementForm(current,ab).show());
        supp.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            LivreurService.getInstance().deleteLivreur(l.getIdLivreur()); 
            Dialog.show("Success", "Livreur Deleted Successfully.", "OK", "Cancel");
            new HomeForm().show();
       }
       });

         modif.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
             
            new ModifierLivreur(l);
       }
       });
        x.addAll( idLivreur,nom,prenom,adresse,email,numTelephone,supp,modif);
        //xx.addAll(supp,modif);
        y.addAll(x,xx,separation);
    }
    
    getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e-> new AjouterLivreur(current).show());
    
    
    addAll(y);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
}

}