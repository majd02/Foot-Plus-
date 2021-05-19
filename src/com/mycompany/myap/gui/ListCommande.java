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
import com.mycompany.myap.Services.CommandeService;
import com.mycompany.myap.entities.Commande;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ListCommande extends Form{
Form current;
ArrayList<Commande> data = new ArrayList<>();

public ListCommande(Form previous) {
    setTitle("Liste Des Commandes");
    current = this;
    data = CommandeService.getInstance().getAllCours();
    Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    for (int i=0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Commande u = new Commande();
        
        
        u.setCommandeId(data.get(i).getCommandeId());
        u.setNom(data.get(i).getNom());
        u.setPrenom(data.get(i).getPrenom());
        u.setEmail(data.get(i).getEmail());
        u.setAdresse(data.get(i).getAdresse());   
        u.setNumTelephone(data.get(i).getNumTelephone());
        u.setDate(data.get(i).getDate());
        u.setTotalCost(data.get(i).getTotalCost());
                  
                  
                  
        Label separation = new Label("----------------------------");
        Label commandeId = new Label(""+ data.get(i).getCommandeId());
        Label nom = new Label( data.get(i).getNom());
        Label prenom = new Label(data.get(i).getPrenom());
        Label email = new Label(data.get(i).getEmail());
        Label adresse = new Label( data.get(i).getAdresse());
        Label numTelephone = new Label("  "+ data.get(i).getNumTelephone());
        Label date = new Label(data.get(i).getDate());
        Label totalCost = new Label(" "+ data.get(i).getTotalCost());
        
    
       Button Ajout = new Button("Valider");
       Button supp = new Button("Annuler");
    
        
        

        supp.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
            CommandeService.getInstance().deleteCommande(u.getCommandeId()); 
            Dialog.show("Success", "Commande Deleted Successfully.", "OK", "Cancel");
            new HomeForm().show();
       }
       });
        Ajout.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
           
            new AjouterLivraison(current).show();
       }
       });

        x.addAll( commandeId,nom,prenom,adresse,email,numTelephone,date,totalCost,supp,Ajout);
        //xx.addAll(supp,modif);
        y.addAll(x,xx,separation);
    }
    
    
    
    
    addAll(y);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
}
}