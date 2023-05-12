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
import com.mycomany.entities.Event;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fatha
 */
public class EventService {
  Event c = new Event();
    public ArrayList<Event> Events;
    public static boolean resultOk = true;
    public static EventService instance = null;
    private ConnectionRequest req;

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public EventService() {
        req = new ConnectionRequest();
    }

    //add Event
    public void addEvent(Event event) {
        String url = Statics.BASE_URL + "addEventMobile?eventName=" + event.getEventName() + "&description=" + event.getDescription() + "&location=" + event.getLocation() + "&maxAttendees=" + event.getMaxAttendees();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data = " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req); //execution mtaa el request sinon raw ma yetaada chay
    }

    //Display
    public ArrayList<Event> parseTasks(String jsonText) {
        try {
            Events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
              
                Event c = new Event();
                float eventId = Float.parseFloat(obj.get("eventId").toString());
                String eventName = obj.get("eventName").toString();
 
                String description = obj.get("description").toString();
                String location = obj.get("location").toString();
                float maxAttendees = Float.parseFloat(obj.get("maxAttendees").toString());
                c.setRegistrationdeadline(obj.get("registrationdeadline").toString());
                
                c.setStartDate(obj.get("startDate").toString());
                c.setEndDate(obj.get("endDate").toString());
                c.setEventId((int)eventId);
                c.setEventName(eventName);
                c.setDescription(description);
                c.setMaxAttendees((int)maxAttendees);
                c.setLocation(location);
                
            
                Events.add(c);
            }

        } catch (IOException ex) {

        }
        return Events;
    }

    public ArrayList<Event> displayEvents() {
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "displayEventMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Events;
    }

    /*public ArrayList<Event> displayEvents() {
        ArrayList<Event> result = new ArrayList<>();

        String url = Statics.BASE_URL + "displayEventMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapEvents = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvents.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Event event = new Event();

                        //dima id fi codename one float 5outhouha
                        float eventId = Float.parseFloat(obj.get("eventId").toString());
                        String eventName = obj.get("eventName").toString();

                        String description = obj.get("description").toString();
                        String location = obj.get("location").toString();
                        int maxAttendees = Integer.parseInt(obj.get("maxAttendees").toString());
                        String DateConverter = obj.get("registrationdeadline").toString().substring(obj.get("registrationdeadline").toString().indexOf("timestamp") + 10, obj.get("registrationdeadline").toString().lastIndexOf("}"));
                        String DateConverter1 = obj.get("startDate").toString().substring(obj.get("startDate").toString().indexOf("timestamp") + 10, obj.get("startDate").toString().lastIndexOf("}"));
                        String DateConverter2 = obj.get("endDate").toString().substring(obj.get("endDate").toString().indexOf("timestamp") + 10, obj.get("endDate").toString().lastIndexOf("}"));

                        Date registrationdeadline = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        Date startDate = new Date(Double.valueOf(DateConverter1).longValue() * 1000);
                        Date endDate = new Date(Double.valueOf(DateConverter2).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        event.setEventId((int) eventId);

                        //Date 
                        String dateString = formatter.format(registrationdeadline);
                        String dateString1 = formatter.format(startDate);
                        String dateString2 = formatter.format(endDate);
                        event.setEventName(eventName);
                        event.setDescription(description);
                        event.setLocation(location);
                        event.setMaxAttendees((int) maxAttendees);
                        event.setRegistrationdeadline(dateString);
                        event.setStartDate(dateString1);
                        event.setEndDate(dateString2);
                        //event=new Event(eventName,description,dateString1,dateString2,location,maxAttendees,dateString);

                        //insert data into ArrayList result
                        result.add(event);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }*/

    //Details Event
    public Event EventDetail(int eventId, Event event) {
        String url = Statics.BASE_URL + "detailsEventMobile/" + eventId;
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                event.setEventName(obj.get("eventName").toString());
                event.setLocation(obj.get("location").toString());
                event.setDescription(obj.get("description").toString());
                event.setMaxAttendees(Integer.parseInt(obj.get("maxAttendees").toString()));

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }

            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return event;

    }
    //Delete 

    public boolean deleteEvent(int eventId) {
        String url = Statics.BASE_URL + "deleteEventMobile/" + eventId;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    //Update 
    public boolean updateEvent(Event event) {
        String url = Statics.BASE_URL + "updateEventMobile/" + event.getEventId() + "?eventName=" + event.getEventName() + "&description=" + event.getDescription() + "&location=" + event.getLocation() + "&maxAttendees=" + event.getMaxAttendees();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }

}
