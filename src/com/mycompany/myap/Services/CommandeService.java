/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myap.Services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myap.entities.Commande;
import com.mycompany.myap.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizm
 */
public class CommandeService {
    public static CommandeService instance = null;
    private ConnectionRequest req;
    public ArrayList<Commande> commande;
    public static boolean resultOK;
    
    public static CommandeService getInstance(){
        if(instance==null)
            instance = new CommandeService();
         return instance;
        
    }
    public CommandeService(){
        req = new ConnectionRequest();
    }
    public void AjouterCommande(Commande commande){
        String url = Statics.BASE_URL+"/addcommande?nom="+commande.getNom()+"&prenom="+commande.getPrenom()+"&email="+commande.getEmail()+"&adresse="+commande.getAdresse()+"&numtelephone="+commande.getNumTelephone()+"&date="+commande.getDate()+"&totalcost="+commande.getTotalCost();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<Commande> parseCours(String jsonText){
        try {
            commande=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande u = new Commande();
               
                u.setCommandeId((int) Float.parseFloat(obj.get("commandeid").toString()));
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setEmail(obj.get("email").toString());
                u.setAdresse(obj.get("adresse").toString());
                u.setNumTelephone((int)Double.parseDouble(obj.get("numtelephone").toString()));
                u.setDate(obj.get("date").toString());
                u.setTotalCost((int)Double.parseDouble(obj.get("totalcost").toString()));
                
               
       
                commande.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return commande;
    }
        
        
    public ArrayList<Commande> getAllCours(){
        String url = Statics.BASE_URL+"/listecommande/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commande = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commande;
    }
    public boolean deleteCommande(int commandeId){
        String url = Statics.BASE_URL+"/deletecommande?commandeId="+commandeId;
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               req.removeResponseCodeListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
    }
    
}
