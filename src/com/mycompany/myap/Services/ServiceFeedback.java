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
import com.mycompany.myap.entities.Feedback;
import com.mycompany.myap.utils.Statics;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author USER
 */
public class ServiceFeedback {
    public ArrayList<Feedback> feedback;
    public static ServiceFeedback instance = null;
    public static boolean resultOK =true ;
    private ConnectionRequest req;
    
    
    public static ServiceFeedback getInstance(){
        if(instance==null)
            instance = new ServiceFeedback();
         return instance;
        
    }
    
    
    public ServiceFeedback(){
        req = new ConnectionRequest();
    }
    public void addStade(Feedback feedback){
        String url = Statics.BASE_URL+"/addfe?description="+feedback.getDescription()+"&rate="+feedback.getRate()+"&idu="+feedback.getUser();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    public ArrayList<Feedback> parseCours(String jsonText){
        try {
            feedback=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
              Feedback u = new Feedback();
                
                
            
               
                u.setDescription(obj.get("description").toString());
                u.setRate((int) Double.parseDouble(obj.get("rate").toString()));
                u.setUser(obj.get("idu").toString());
                
                
       
                feedback.add(u);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return feedback;
    }
        
        
    public ArrayList<Feedback> getAllCours(){
        ArrayList<Feedback> result =new ArrayList<>();
        
        String url = Statics.BASE_URL+"/listefeedback/";
        req.setUrl(url);
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                feedback = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return feedback;
    }
    public boolean DeleteReclamation(int id )
    {
        String url = Statics.BASE_URL+"/deletefeedback?"+id;
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

   
}
/*


*/