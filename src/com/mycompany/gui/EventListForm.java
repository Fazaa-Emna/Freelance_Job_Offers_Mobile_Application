/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

import com.mycomany.entities.Event;
import com.mycompany.services.EventService;

/**
 *
 * @author fatha
 */
public class EventListForm extends Form{
      public EventListForm(Form previous) {
        setTitle("List of Events");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        for (Event event : EventService.getInstance().displayEvents()) {
            MultiButton multiButton = new MultiButton(event.getEventName());
            multiButton.setTextLine2("Description: " + event.getDescription());
            multiButton.setTextLine3("location: " + event.getLocation());
            multiButton.setTextLine4("Max Attendees: " + event.getMaxAttendees());
            /*multiButton.setTextLine4("Registration Deadline: " + event.getRegistrationdeadline());
            multiButton.setTextLine3("Strat Date: " + event.getStartDate());
            multiButton.setTextLine3("End Date: " + event.getEndDate());*/
            add(multiButton);
        }

        getToolbar().addCommandToLeftBar("Back", null, e -> previous.showBack());
    }
}
