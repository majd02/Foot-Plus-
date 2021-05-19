
package com.mycompany.myap.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myap.entities.Livreur;
import com.mycompany.myap.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizm
 */
public class LivreurService { 

  
    public static LivreurService instance = null;
    private ConnectionRequest req;
    public ArrayList<Livreur> livreur;
    public static boolean resultOK;
    
    public static LivreurService getInstance(){
        if(instance==null)
            instance = new LivreurService();
         return instance;
        
    }
    public LivreurService(){
        req = new ConnectionRequest();
    }
    public void AjouterLivreur(Livreur livreur){
        String url = Statics.BASE_URL+"/addlivreur?nomlivreur="+livreur.getNomLivreur()+"&prenomlivreur="+livreur.getPrenomLivreur()+"&email="+livreur.getEmail()+"&adresselivreur="+livreur.getAdresseLivreur()+"&numTelephonelivreur="+livreur.getNumTelephoneLivreur();
        req.setUrl(url);
        req.addResponseListener(a->{
            String str = new String(req.getResponseData());
            System.err.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<Livreur> parseCours(String jsonText){
        try {
            livreur =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Livreur l = new Livreur();
               
                l.setIdLivreur((int) Float.parseFloat(obj.get("idlivreur").toString()));
                l.setNomLivreur(obj.get("nomlivreur").toString());
                l.setPrenomLivreur(obj.get("prenomlivreur").toString());
                l.setEmail(obj.get("email").toString());
                l.setAdresseLivreur(obj.get("adresselivreur").toString());
                l.setNumTelephoneLivreur((int)Double.parseDouble(obj.get("numtelephonelivreur").toString()));
               
                
               
       
                livreur.add(l);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return livreur;
    }
        
        
    public ArrayList<Livreur> getAllCours(){
        String url = Statics.BASE_URL+"/listelivreur/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livreur = parseCours(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livreur;
    }
    public boolean deleteLivreur(int idLivreur){
        String url = Statics.BASE_URL+"/deletelivreur?idLivreur="+idLivreur;
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
    public boolean modifierLivreur(Livreur livreur){
        String url = Statics.BASE_URL+"/modifierlivreur?idLivreur="+livreur.getIdLivreur()+"&nomLivreur="+livreur.getNomLivreur()+"&prenomLivreur="+livreur.getPrenomLivreur()+"&adresseLivreur="+livreur.getAdresseLivreur()+"&email="+livreur.getEmail()+"&numTelephoneLivreur="+livreur.getNumTelephoneLivreur();
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


