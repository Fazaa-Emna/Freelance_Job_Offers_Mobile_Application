
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
import com.mycomany.entities.Course;
import com.mycomany.entities.Lesson;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zahra
 */
public class ServiceLesson {

    Lesson course = new Lesson();
    public ArrayList<Lesson> lessons;

    public static ServiceLesson instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceLesson() {
        req = new ConnectionRequest(); //To change body of generated methods, choose Tools | Templates.
    }

    public Lesson getLessonById(int id) {
        for (Lesson course : lessons) {
            if (course.getLid() == id) {
                return course;
            }
        }
        return null;
    }

    public static ServiceLesson getInstance() {
        if (instance == null) {
            instance = new ServiceLesson();
        }
        return instance;
    }

    public boolean deleteCourse(Lesson t) {

        String url = Statics.BASE_URL + "deleteLesson/" + t.getLid();
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

    public boolean updateCourse(Lesson t) {

        String url = Statics.BASE_URL + "updateLesson/" + t.getLid() + "?" + "name=" + t.getName() + "&file=" + t.getFile() + "&cid=" + t.getCid();
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

    public boolean addCourse(Lesson t) {
        System.out.println(t);
        System.out.println("********");
        String url = Statics.BASE_URL + "addLesson/?name=" + t.getName() + "&file=" + t.getFile() + "&cid=" + t.getCid();

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

    public ArrayList<Lesson> parseTasks(String jsonText) {
        try {
            lessons = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Lesson l = new Lesson();

               // Course c = (Course) obj.get("cid");
                //   double id = Double.parseDouble(obj.get("cid").toString());
              //  l.setCid(c.getCid());
                double lid = Double.parseDouble(obj.get("lid").toString());
                l.setLid((int) lid);
                l.setName((String) obj.get("name"));

                l.setFile((String) obj.get("file"));
 Map<String, Object> courseObj = (Map<String, Object>) obj.get("cid");
           Course c = new Course();
            double cid = Double.parseDouble(courseObj.get("cid").toString());
            c.setTitle((String) courseObj.get("title"));
            c.setCid((int) cid);
            l.setCourse(c);
            l.setCid((int) cid);
            l.setCourse_title((String) courseObj.get("title"));
            // Set the course object on the lesson
         //   l.setCid(c);
                lessons.add(l);
            }

        } catch (IOException ex) {

        }
        return lessons;
    }

    public ArrayList<Lesson> getAllLessons() {
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "Alllessons/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lessons = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lessons;
    }

    public static class getInstance {

        public getInstance() {
        }
    }
}
