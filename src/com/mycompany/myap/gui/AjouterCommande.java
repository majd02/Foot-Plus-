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
import com.mycompany.myap.Services.CommandeService;
import com.mycompany.myap.entities.Commande;
import com.teknikindustries.bulksms.SMS;




/**
 *
 * @author asus
 */
public class AjouterCommande extends Form {

    public AjouterCommande(Form previous) {
        setTitle("Ajouter un nouveau commande");
       
        
        TextField tfnom = new TextField("","Nom");
        TextField tfprenom = new TextField("","prenom");
        TextField tfemail = new TextField("","email");
        TextField tfadresse = new TextField("","adresse");
        TextField tfnumTelephone = new TextField("","numtelephone");
        TextField tfdate = new TextField("","date");         
        TextField tftotalCost = new TextField("","totalcost");
        
        Button btnValider = new Button("Add");
        
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfnom.getText().length()==0)||(tfprenom.getText().length()==0)||(tfemail.getText().length()==0)||(tfadresse.getText().length()==0)||(tfnumTelephone.getText().length()==0)||(tfdate.getText().length()==0)||(tftotalCost.getText().length()==0))
                { Dialog.show("Alert", "Please fill all the fields", new Command("OK"));}
                else{
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog idialog = ip.showInfiniteBlocking();
            Commande s = new Commande(
                    String.valueOf(tfnom.getText()),
                    String.valueOf(tfprenom.getText()),
                    String.valueOf(tfemail.getText()),
                    String.valueOf(tfadresse.getText()),
                    Integer.parseInt(tfnumTelephone.getText()),
                    String.valueOf(tfdate.getText()),
                    Integer.parseInt(tftotalCost.getText())
                    );
                    System.err.println("data commande=="+s);
                    CommandeService.getInstance().AjouterCommande(s);
                    idialog.dispose();
                    Dialog.show("Succes", "Commande Added", new Command("OK"));
                    
                    SMS sms=new SMS();
                 String nt= "+21628631786";
                 sms.SendSMS("azizmaamar","Aziz28631786", "Une commande est passer", nt ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
            }
        }
        });
        addAll(tfnom,tfprenom,tfemail,tfadresse,tfnumTelephone,tfdate,tftotalCost,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}