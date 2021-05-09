/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.entities.Stade;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author asus
 */
public class StadeService {
    public ArrayList<Stade> stades;
    public static StadeService instance = null;
    private ConnectionRequest req;
    public static boolean resultOK;
    
    
    public static StadeService getInstance(){
        if(instance==null)
            instance = new StadeService();
         return instance;
        
    }
    
    
    public StadeService(){
        req = new ConnectionRequest();
    }
    public void addStade(Stade stade){
        String url = Statics.BASE_URL+"/add?nom="+stade.getNom()+"&adresse="+stade.getAdresse()+"&prixph="+stade.getPrixph()+"&contact="+stade.getContact()+"&image="+stade.getImage();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public void addReservation(Reservation res){
        String url = Statics.BASE_URL+"/addres?idStade="+res.getId_stade()+"&heure="+res.getHeure()+"&nom="+res.getNom()+"&date="+res.getDate();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<Stade> Afficherstade(){
        ArrayList<Stade> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/liste";
        req.setUrl(url);
        req.setPost(false);
        req.setDuplicateSupported(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{
                    Map<String,Object>mapStade=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List< Map<String,Object>> listOfMap = (List< Map<String,Object>>) mapStade.get("root");
                    
                    for(Map<String,Object> obj : listOfMap){
                        Stade s = new Stade();
                        float id_stade = Float.parseFloat(obj.get("id_stade").toString());
                        String nom = obj.get("nom").toString();
                         String adresse = obj.get("adresse").toString();
                         int prixph = Integer.parseInt(obj.get("prixph").toString());
                         String contact = obj.get("contact").toString();
                         String image = obj.get("image").toString();
                         
                         s.setId_stade((int) id_stade);
                         s.setAdresse(adresse);
                         s.setPrixph(prixph);
                         s.setContact(contact);
                         s.setImage(image);
                         
                         result.add(s);
                         
                    }
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    public Stade detailstade(int id_stade , Stade stade){
        String url = Statics.BASE_URL+"/detail/"+id_stade;
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener((evt) -> {
            
            JSONParser jsonp =new JSONParser();
            try{
                Map<String,Object>obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                stade.setNom(obj.get("nom").toString());
                stade.setAdresse(obj.get("adresse").toString());
                stade.setPrixph(Integer.parseInt( obj.get("prixph").toString()));
                stade.setContact(obj.get("contact").toString());
                stade.setImage(obj.get("image").toString());
                
            }catch(IOException ex){
                    System.err.println("error"+ex.getMessage());
                }
            System.err.println("data=="+str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stade;
        
    }
    public ArrayList<Stade> parseCours(String jsonText){
        try {
            stades=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Stade u = new Stade();
               
               u.setId_stade((int) Float.parseFloat(obj.get("idStade").toString()));
                u.setNom(obj.get("nom").toString());
                u.setAdresse(obj.get("adresse").toString());
                u.setPrixph((int) Double.parseDouble(obj.get("prixph").toString()));
                
                
                u.setContact(obj.get("contact").toString());
               
               u.setImage(obj.get("image").toString());
       
                stades.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stades;
    }
        
        
    public ArrayList<Stade> getAllCours(){
        String url = Statics.BASE_URL+"/liste/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                stades = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stades;
    }
    public boolean deleteStade(int id_stade){
        String url = Statics.BASE_URL+"/deletestade?id_stade="+id_stade;
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
    public boolean modifierStade(Stade stade){
        String url = Statics.BASE_URL+"/updatestade?id_stade="+stade.getId_stade()+"&nom="+stade.getNom()+"&adresse="+stade.getAdresse()+"&prixph="+stade.getPrixph()+"&contact="+stade.getContact()+"&image="+stade.getImage();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK=req.getResponseCode() == 200;
              req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
    }
        
        
    
}
