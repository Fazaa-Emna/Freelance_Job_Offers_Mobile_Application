/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author fatha
 */
public class Event {
    private int eventId;
    private String eventName;
    private String description;
    private String location;
    private int maxAttendees;
    private String registrationdeadline;
    private String startDate;
    private String endDate;

    public Event() {
    }

    public Event(int eventId, String eventName, String description,String startDate, String endDate, String location, int maxAttendees, String registrationdeadline ) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.registrationdeadline = registrationdeadline;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String eventName, String description , String startDate, String endDate , String location, int maxAttendees, String registrationdeadline) {
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.registrationdeadline = registrationdeadline;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public String getRegistrationdeadline() {
        return registrationdeadline;
    }

    public void setRegistrationdeadline(String registrationdeadline) {
        this.registrationdeadline = registrationdeadline;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
}
