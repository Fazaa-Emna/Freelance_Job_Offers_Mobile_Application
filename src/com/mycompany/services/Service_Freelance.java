/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Freelance;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youss
 */
public class Service_Freelance {
        
    public ArrayList<Freelance> Coachs;
    public static Service_Freelance instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Freelance() {
        req = new ConnectionRequest();
    }

     
    public static Service_Freelance getInstance() {
        if (instance == null) {
            instance = new Service_Freelance();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<Freelance> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Freelance coach = new Freelance();
                
                
               float idfreelance = Float.parseFloat(obj.get("idfreelance").toString());
               coach.setIdfreelance((int) idfreelance);
               
             
                float idbo = Float.parseFloat(obj.get("idbo").toString());
               coach.setIdbo((int) idbo);
               
               
       
               
                
               float budget = Float.parseFloat(obj.get("budget").toString());
               coach.setBudget((float) budget);
               
                
               
                
               float nbapplicants = Float.parseFloat(obj.get("nbapplicants").toString());
               coach.setNbapplicants((int) nbapplicants);
               
                
               
                
                
        
               coach.setEmailbo(obj.get("emailbo").toString());
               
               
               coach.setCategoryF(obj.get("categoryF").toString());
               
               coach.setDescription(obj.get("description").toString());

                 coach.setAdddate(obj.get("adddate").toString());
                 
                 

                 coach.setUrllogo(obj.get("urllogo").toString());
                 
                 
                 

     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Freelance> findAll() {
        String url = Statics.BASE_URL + "freelance_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachs = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachs;
    }
    
    
    
    
    
    
    
    
    
    
    
      
          public void AddFreelance(Freelance c) {
        String url = Statics.BASE_URL + "newfreelance_mobile/"+ c.getIdbo()+ "/" + c.getEmailbo()+ "/" +c.getCategoryF()+ "/" + c.getDescription()+ "/" +c.getBudget()+ "/" + c.getAdddate()+ "/" +c.getUrllogo()+ "/" + c.getNbapplicants(); //création de l'URL

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
    
    
    
    
    
    
    
      public boolean deleteFreelance(int idfreelance) {
        String url = Statics.BASE_URL + "SupprimerFreelance?idfreelance=" + idfreelance;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
                    
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }
    
      
      
      
      
      
      
      
      
          
    public boolean ModifierEvent( Freelance c) {
        
       String url = Statics.BASE_URL + "updateFreelance?idfreelance=" + c.getIdfreelance()+"&emailbo=" + c.getEmailbo()+"&categoryF=" + c.getCategoryF()+"&description=" + c.getDescription();

       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        //System.out.println("url ==> " + url);
        //System.out.println("data ==> " + req);
        return resultOK;

    }
    
    
    
    
    
      
}
