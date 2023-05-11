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
import com.mycomany.entities.Course;
import com.mycompany.utilis.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceCourse {
   Course course = new Course();
    public ArrayList<Course> courses;
    
    public static ServiceCourse instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCourse() {
         req = new ConnectionRequest();
    }
 
    public Course getCourseById(int id) {
        for (Course course : courses) {
            if (course.getCid() == id) {
                return course;
            }
        }
        return null;
    }

    public static ServiceCourse getInstance() {
        if (instance == null) {
            instance = new ServiceCourse();
        }
        return instance;
    }
 public boolean deleteCourse(Course  t) {

     String url = Statics.BASE_URL + "deleteCourse/" + t.getCid() ;
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
    public boolean updateCourse(Course  t) {

     String url = Statics.BASE_URL + "updateCourse/" + t.getCid() + "?" + "title=" + t.getTitle() + "&category=" + t.getCategory()+ "&price=" + t.getPrice() + "&description=" + t.getDecription() + "&photo=" + t.getPhoto();
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
    public boolean addCourse(Course t) {
        System.out.println(t);
        System.out.println("********");
       
       String url = Statics.BASE_URL + "addCourse?title=" + t.getTitle() + "&category=" + t.getCategory()+ "&price=" + t.getPrice() + "&description=" + t.getDecription() + "&photo=" + t.getPhoto();
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

    public ArrayList<Course> parseTasks(String jsonText){
        try {
            courses=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
              for(Map<String,Object> obj : list){
            Course course = new Course();
            double id = Double.parseDouble(obj.get("cid").toString());
            course.setCid((int)id);
            course.setTitle((String) obj.get("title"));
            course.setDecription((String) obj.get("description"));
            course.setPhoto((String) obj.get("photo"));
            double price = Double.parseDouble(obj.get("price").toString());
            course.setPrice((int)price);
            course.setCategory((String) obj.get("category"));
            courses.add(course);
        }
                       
        } catch (IOException ex) {
            
        }
        return courses;
    }
    
    public ArrayList<Course> getAllCourses(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"Allcourses/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                courses = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return courses;
    }

    public static class getInstance {

        public getInstance() {
        }
    }
//    
//public Course getCourseById(String jsonText, int courseId) {
//    Course course = null;
//    try {
//        JSONParser j = new JSONParser();
//        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
//
//        for (Map<String, Object> obj : list) {
//            int id = (int) Double.parseDouble(obj.get("cid").toString());
//            if (id == courseId) {
//                course = new Course();
//                course.setCid(id);
//                course.setTitle((String) obj.get("title"));
//                course.setDecription((String) obj.get("description"));
//                course.setPhoto((String) obj.get("photo"));
//                double price = Double.parseDouble(obj.get("price").toString());
//                course.setPrice((int) price);
//                course.setCategory((String) obj.get("category"));
//                break;
//            }
//        }
//    } catch (IOException ex) {
//        ex.printStackTrace();
//    }
//    return course;
//}
// public Course showCourse(int id){
//        String url = Statics.BASE_URL + "Allcourses"+ id;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//              
//      course = getCourseById(new String(req.getResponseData()),id);
//               
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return course;       
//    }
}
