/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Event;
import com.mycompany.services.EventService;

/**
 *
 * @author Zahra
 */
public class UpdateEventForm extends BaseForm {

    public UpdateEventForm(Resources res, Event event) {
        super("Newsfeed", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());

        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        setTitle("Modify your event");
        setLayout(BoxLayout.y());

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        add(LayeredLayout.encloseIn(
                sl
        ));
        Label id = new Label(String.valueOf(event.getEventId()));
        id.setVisible(false);

        TextField eventName = new TextField(event.getEventName());

        addStringValue("eventName", eventName);

        TextField d = new TextField(event.getDescription());

        addStringValue("description", d);

        TextField Location = new TextField(event.getLocation());

        addStringValue("Location", Location);

        TextField maxAttendees = new TextField(String.valueOf(event.getMaxAttendees()));

        addStringValue("maxAttendees", maxAttendees);

        

        eventName.setUIID("MyCustomLabel");
        d.setUIID("MyCustomLabel");
        Location.setUIID("MyCustomLabel");
        maxAttendees.setUIID("MyCustomLabel");
        
        Style myLabelStyle = UIManager.getInstance().getComponentStyle("MyCustomLabel");
        myLabelStyle.setFgColor(0x000000);
        eventName.refreshTheme();
        d.refreshTheme();
        Location.refreshTheme();
        maxAttendees.refreshTheme();
        
        Button delete = new Button("Delete");
        delete.addActionListener(evt -> {
            boolean userResponse = com.codename1.ui.Dialog.show("Alert", "Are you sure you want to delete this event?", "Yes", "No");
            if (userResponse) {
                EventService.getInstance().deleteEvent(event.getEventId());
                com.codename1.ui.Dialog.show("Success", "The Event has been deleted.", new com.codename1.ui.Command("OK"));
                new EventDisplay(res).show();
            }
        });
        Button btnValider = new Button("Modify");
        addStringValue("", btnValider);
        addStringValue("", delete);
        addStringValue("", id);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((eventName.getText().length() == 0) || (d.getText().length() == 0) || (Location.getText().length() == 0) || (maxAttendees.getText().length() == 0) ) {
                    com.codename1.ui.Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    Event t = new Event(Integer.parseInt(id.getText()), eventName.getText(), d.getText(),event.getStartDate(),event.getEndDate(), Location.getText(), Integer.parseInt(maxAttendees.getText()),event.getRegistrationdeadline());
                    if (EventService.getInstance().updateEvent(t)) {
                        com.codename1.ui.Dialog.show("Success", "Event updated succesfully", new Command("OK"));
                    } else {
                        com.codename1.ui.Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            }
        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
