/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.mycompany.entities.Blog;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.utilis.Statics;

/**
 *
 * @author zeinab
 */
public class ServiceBlog {
    public static ServiceBlog instance = null;
     public boolean resultOK;
     private ConnectionRequest req;
     ArrayList<Blog> blog;

    private ServiceBlog() {
        req = new ConnectionRequest();
    }

    public static ServiceBlog getInstance() {
        if (instance == null) {
            instance = new ServiceBlog();
        }
        return instance;
    }
    
    
      public ArrayList<Blog> parseBlogs(String jsonText) {
        try {
            blog = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reservationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Blog r = new Blog();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                r.setTitle((String)obj.get("title"));
                r.setBody((String)obj.get("body"));
               
               blog.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return blog;
    }
      
      
      
      public ArrayList<Blog> getAllBlogs() {
        String url = Statics.BASE_URL + "AllBlogs";////////////////////////////////////////////////////////////////////
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                blog = parseBlogs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return blog;
 
      
   }
      
      
      

      
      public void AddBlog(Blog b){
        String url = Statics.BASE_URL+"addBlogJSON/new?title="+ b.getTitle() + "&body=" + b.getBody();////////////////////////////////////////////////////////
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
      
      
       
    public boolean suppBlog(Blog b)
    {

         String url = Statics.BASE_URL + "DeleteBlogJSON/" + b.getId(); ///////////////////////////////
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
    
    public void modifieBlog(int id, String title, String body) {
    String url = Statics.BASE_URL + "updateBlogJSON/" + id + "?&title=" + title+"&body="+body;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setHttpMethod("PUT");
    req.addResponseListener((NetworkEvent evt) -> {
    
       
        if (req.getResponseCode() == 200) {
            JSONParser parser = new JSONParser();
            try {
                Map<String, Object> response = parser.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                System.out.println(response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}
}
