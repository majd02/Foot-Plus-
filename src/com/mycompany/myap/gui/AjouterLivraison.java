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
import com.mycompany.myap.Services.LivraisonService;
import com.mycompany.myap.entities.Livraison;






/**
 *
 * @author asus
 */
public class AjouterLivraison extends Form {

    public AjouterLivraison(Form previous) {
        setTitle("Ajouter une Livraison ");
       
        TextField tfcommandeId = new TextField("","Id Commande");
        TextField tfnom = new TextField("","Nom");
        TextField tfprenom = new TextField("","prenom");
        TextField tfemail = new TextField("","email");
        TextField tfadresse = new TextField("","adresse");
        TextField tfnumTelephone = new TextField("","numtelephone");
        TextField tfdate = new TextField("","date");         
        TextField tftotalCost = new TextField("","totalcost");
        
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfcommandeId.getText().length()==0)||(tfnom.getText().length()==0)||(tfprenom.getText().length()==0)||(tfemail.getText().length()==0)||(tfadresse.getText().length()==0)||(tfnumTelephone.getText().length()==0)||(tfdate.getText().length()==0)||(tftotalCost.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Livraison s = new Livraison(
                    
                    Integer.parseInt(tfcommandeId.getText()),
                    String.valueOf(tfnom.getText()),
                    String.valueOf(tfprenom.getText()),
                    String.valueOf(tfemail.getText()),
                    String.valueOf(tfadresse.getText()),
                    Integer.parseInt(tfnumTelephone.getText()),
                    String.valueOf(tfdate.getText()),
                    Integer.parseInt(tftotalCost.getText())
                    );
                   
                    
                    System.err.println("data commande=="+s);
                    LivraisonService.getInstance().AjouterLivraison(s);
                    idialog.dispose();
                    Dialog.show("Succes", "Livraison Ajouter", new Command("OK"));
                   
            }
        }
        });
        addAll(tfcommandeId,tfnom,tfprenom,tfemail,tfadresse,tfnumTelephone,tfdate,tftotalCost,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}