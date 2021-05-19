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
import com.mycompany.myap.entities.Livraison;
import com.mycompany.myap.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizm
 */
public class LivraisonService {
    public static LivraisonService instance = null;
    private ConnectionRequest req;
    public ArrayList<Livraison> livraison;
    public static boolean resultOK;
    
    public static LivraisonService getInstance(){
        if(instance==null)
            instance = new LivraisonService();
         return instance;
        
    }
    public LivraisonService(){
        req = new ConnectionRequest();
    }
    public void AjouterLivraison(Livraison livraison){
        String url = Statics.BASE_URL+"/addlivraison?commandeid="+livraison.getCommandeId()+"&nom="+livraison.getNom()+"&prenom="+livraison.getPrenom()+"&email="+livraison.getEmail()+"&adresse="+livraison.getAdresse()+"&numtelephone="+livraison.getNumTelephone()+"&date="+livraison.getDate()+"&totalcost="+livraison.getTotalCost();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<Livraison> parseCours(String jsonText){
        try {
            livraison=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Livraison u = new Livraison();
                
                
                u.setLivraisonId((int) Float.parseFloat(obj.get("livraisonid").toString()));
                u.setCommandeId((int) Float.parseFloat(obj.get("commandeid").toString()));
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setEmail(obj.get("email").toString());
                u.setAdresse(obj.get("adresse").toString());
                u.setNumTelephone((int)Double.parseDouble(obj.get("numtelephone").toString()));
                u.setDate(obj.get("date").toString());
                u.setTotalCost((int)Double.parseDouble(obj.get("totalcost").toString()));
                
               
       
                livraison.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return livraison;
    }
        
        
    public ArrayList<Livraison> getAllCours(){
        String url = Statics.BASE_URL+"/listelivraison/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livraison = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livraison;
    }
  
    
}
