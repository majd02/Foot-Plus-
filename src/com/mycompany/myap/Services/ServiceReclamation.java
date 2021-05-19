
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
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;

import com.codename1.ui.events.ActionListener;
import com.mycompany.myap.entities.Reclamation;
import com.mycompany.myap.utils.Statics;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author USER
 */
public class ServiceReclamation {
    public ArrayList<Reclamation> reclamation;
    public static ServiceReclamation instance = null;
    public static boolean resultOK =true ;
    private ConnectionRequest req;
       public static Image getScreenshot() {
        Form form = Display.getInstance().getCurrent();
        if (form != null) {
            Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
            form.paintComponent(screenshot.getGraphics(), true);
            return screenshot;
        } else {
            return null;
        }
    }
    
    public static ServiceReclamation getInstance(){
        if(instance==null)
            instance = new ServiceReclamation();
         return instance;
        
    }
    
    
    public ServiceReclamation(){
        req = new ConnectionRequest();
    }
    public void addStade(Reclamation reclamation){
        String url = Statics.BASE_URL+"/addrec?type="+reclamation.getType()+"&ido="+reclamation.getIdo()+"&description="+reclamation.getDescription()+"&sujet="+reclamation.getSujet()+"&idU="+reclamation.getUser();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    public ArrayList<Reclamation> parseCours(String jsonText){
        try {
            reclamation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation u = new Reclamation();
               u.setId((int) Float.parseFloat(obj.get("id").toString()));
                u.setType(obj.get("type").toString());
                u.setIdo(obj.get("ido").toString());
                u.setSujet(obj.get("sujet").toString());
                            u.setEtat(obj.get("etat").toString());
                
                
                u.setDescription(obj.get("description").toString());
                
               
             
       
                reclamation.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamation;
    }
        
        
    public ArrayList<Reclamation> getAllCours(){
        ArrayList<Reclamation> result =new ArrayList<>();
        
        String url = Statics.BASE_URL+"/listereclamation";
        req.setUrl(url);
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamation = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamation;
    }
    public boolean DeleteReclamation(int id )
    {
        String url = Statics.BASE_URL+"/deletereclamation?"+id;
         req.setUrl(url);
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
                      }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean deleteStade(int id){
        String url = Statics.BASE_URL+"/deletereclamation?id="+id;
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
       public boolean accepter(int id){
        String url = Statics.BASE_URL+"/"+id+"/etatacc";
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
       
       public boolean refuser(int id){
        String url = Statics.BASE_URL+"/"+id+"/etatrej";
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
         public boolean done(int id){
        String url = Statics.BASE_URL+"/"+id+"/etatdon";
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
           public boolean mail(int id){
        String url = Statics.BASE_URL+"/contacta";
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
               public boolean mailc(int id){
        String url = Statics.BASE_URL+"/contactr";
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
                       public boolean mailcc(int id){
        String url = Statics.BASE_URL+"/contactd";
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
    public boolean modifierReclamation(Reclamation reclamation){
        String url = Statics.BASE_URL+"/updatereclamation?id="+reclamation.getId()+"&type="+reclamation.getType()+"&ido="+reclamation.getIdo()+"&sujet="+reclamation.getSujet()+"&description="+reclamation.getDescription()+"&idu="+reclamation.getUser();
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
/*


*/