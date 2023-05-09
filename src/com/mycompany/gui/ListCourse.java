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
import com.mycomany.entities.Course;
import com.mycompany.services.ServiceCourse;


/**
 *
 * @author Zahra
 */
public class ListCourse extends Form{
      public ListCourse(Form previous) {
        setTitle("List of Courses");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        for (Course course : ServiceCourse.getInstance().getAllCourses()) {
            MultiButton multiButton = new MultiButton(course.getTitle());
            multiButton.setTextLine2("Price: " + course.getPrice());
            multiButton.setTextLine3("Category: " + course.getCategory());
            add(multiButton);
        }

        getToolbar().addCommandToLeftBar("Back", null, e -> previous.showBack());
    }
}
