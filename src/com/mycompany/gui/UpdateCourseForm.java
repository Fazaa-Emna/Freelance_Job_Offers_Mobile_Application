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
import com.mycomany.entities.Course;
import com.mycompany.services.ServiceCourse;

/**
 *
 * @author Zahra
 */
public class UpdateCourseForm extends BaseForm {

    public UpdateCourseForm(Resources res, Course course) {
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

        setTitle("Modify your Course");
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
        Label id = new Label(String.valueOf(course.getCid()));
        id.setVisible(false);

        TextField Title = new TextField(course.getTitle());

        addStringValue("title", Title);

        TextField d = new TextField(course.getDecription());

        addStringValue("video link", d);

        TextField category = new TextField(course.getCategory());

        addStringValue("category", category);

        TextField photo = new TextField(course.getPhoto());

        addStringValue("photo", photo);

        TextField price = new TextField(String.valueOf(course.getPrice()));

        addStringValue("price", price);

        Title.setUIID("MyCustomLabel");
        d.setUIID("MyCustomLabel");
        category.setUIID("MyCustomLabel");
        photo.setUIID("MyCustomLabel");
        price.setUIID("MyCustomLabel");
        Style myLabelStyle = UIManager.getInstance().getComponentStyle("MyCustomLabel");
        myLabelStyle.setFgColor(0x000000);
        Title.refreshTheme();
        d.refreshTheme();
        category.refreshTheme();
        photo.refreshTheme();
        price.refreshTheme();
        Button delete = new Button("Delete");
        delete.addActionListener(evt -> {
            boolean userResponse = com.codename1.ui.Dialog.show("Alert", "Are you sure you want to delete this course?", "Yes", "No");
            if (userResponse) {
                ServiceCourse.getInstance().deleteCourse(course);
                com.codename1.ui.Dialog.show("Success", "The course has been deleted.", new com.codename1.ui.Command("OK"));
                new CoursesDisplay(res).show();
            }
        });
        Button btnValider = new Button("Modify");
        addStringValue("", btnValider);
        addStringValue("", delete);
        addStringValue("", id);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Title.getText().length() == 0) || (d.getText().length() == 0) || (category.getText().length() == 0) || (photo.getText().length() == 0) || (price.getText().length() == 0)) {
                    com.codename1.ui.Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    Course t = new Course(Integer.parseInt(id.getText()), Integer.parseInt(price.getText()), Title.getText(), d.getText(), photo.getText(), category.getText());
                    if (ServiceCourse.getInstance().updateCourse(t)) {
                        com.codename1.ui.Dialog.show("Success", "Course updated succesfully", new Command("OK"));
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
