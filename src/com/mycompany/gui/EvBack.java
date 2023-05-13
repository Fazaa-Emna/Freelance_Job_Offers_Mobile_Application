/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Event;
import com.mycomany.entities.Service;
import com.mycompany.services.EventService;
import com.mycompany.services.ServiceService;
import java.util.ArrayList;

/**
 *
 * @author Zahra
 */
public class EvBack extends BaseForm{
       public final TextField searchField = new TextField("", "Search");
  public EvBack(Resources res) {
        super("Events Back office", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();


        searchField.addDataChangedListener( (i1, i2) -> {
            String searchQuery = searchField.getText().toLowerCase();
            ArrayList<Event> filteredCourses = new ArrayList<>();
            for (Event course : EventService.getInstance().displayEvents()) {
                if (course.getEventName().toLowerCase().contains(searchQuery) ||
                        course.getStartDate().toLowerCase().contains(searchQuery)) {
                    filteredCourses.add(course);
                }
            }
            showCourses(filteredCourses);
        });

        
        showCourses(EventService.getInstance().displayEvents());

        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        super.addSideMenuB(res);
    }

    private void showCourses(ArrayList<Event> courses) {
        removeAll();
        createLineSeparator();
        Label label = new Label("\n ");
        Label label1 = new Label("\n ");
        addAll(label, label1,searchField);
        for (Event course : courses) {
            TableLayout t = new TableLayout(2, 2);

            t.setGrowHorizontally(true);
            Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

            Container rowContainer = new Container(t);
            add(rowContainer);
            Label courseName = new Label(course.getEventName());
            Label courseDescription = new Label(course.getStartDate());
            courseName.getUnselectedStyle().setFont(f);
            courseDescription.getUnselectedStyle().setFont(f);
            rowContainer.add(courseName);
            rowContainer.add(courseDescription);

            Button delete = new Button("Delete");
            delete.addActionListener(evt -> {
                boolean userResponse = com.codename1.ui.Dialog.show("Alert", "Are you sure you want to delete ?", "Yes", "No");
                if (userResponse) {
                    EventService.getInstance().deleteEvent(course.getEventId());
                    com.codename1.ui.Dialog.show("Success", " deleted.", new com.codename1.ui.Command("OK"));
                    showCourses(EventService.getInstance().displayEvents());
                }
            });
            add(delete);
        }
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
