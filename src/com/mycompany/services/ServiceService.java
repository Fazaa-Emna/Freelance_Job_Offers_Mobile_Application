/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

/**
 *
 * @author Zahra
 */

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Service;
import com.mycompany.utilis.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceService {
   Service service = new Service();
    public ArrayList<Service> services;
    
    public static ServiceService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceService() {
         req = new ConnectionRequest();
    }
 
    public Service getServiceById(int id) {
        for (Service service : services) {
            if (service.getid() == id) {
                return service;
            }
        }
        return null;
    }

    public static ServiceService getInstance() {
        if (instance == null) {
            instance = new ServiceService();
        }
        return instance;
    }

    public boolean addService(Service s) {
        System.out.println(s);
        System.out.println("********");
       
       String url = Statics.BASE_URL + "addServiceJson?name=" + s.getName() + "&descr=" + s.getDescr()+ "&prix=" + s.getPrix() + "&file=" + s.getFile() + "&cat=" + s.getCat();
     req.setUrl(url);    
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 public boolean updateService(Service s) {

     String url = Statics.BASE_URL + "updateServiceJson/" + s.getid() + "?" + "name=" + s.getName() + "&descr=" + s.getDescr()+ "&prix=" + s.getPrix() + "&file=" + s.getFile() + "&cat=" + s.getCat();
     req.setUrl(url);    
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<Service> parseTasks(String jsonText){
        try {
            services=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
              for(Map<String,Object> obj : list){
            Service service = new Service();
            double id = Double.parseDouble(obj.get("id").toString());
            service.setid((int)id);
            service.setName((String) obj.get("name"));
            service.setDescr((String) obj.get("descr"));
            double prix = Double.parseDouble(obj.get("prix").toString());
            service.setPrix((int)prix);
            service.setFile((String) obj.get("file"));
            
            service.setCat((String) obj.get("cat"));
            services.add(service);
        }
                       
        } catch (IOException ex) {
            
        }
        return services;
    }
    
    public ArrayList<Service> getAllServices(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"AllServicesJson/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                services = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return services;
    }

    public static class getInstance {

        public getInstance() {
        }
    }
    
    public boolean deleteService(Service s) {

     String url = Statics.BASE_URL + "deleteServiceJson/" + s.getid() ;
     req.setUrl(url);    
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
