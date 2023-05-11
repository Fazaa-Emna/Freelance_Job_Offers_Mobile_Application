/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Course;
import java.io.IOException;

/**
 *
 * @author Zahra
 */
public class CourseDetailsForm extends BaseForm {

    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;

    public CourseDetailsForm(Resources res, Course course) {
        super("Newsfeed", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
      //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
      

        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        try {
            enc = EncodedImage.create("/" + course.getPhoto());

            ScaleImageLabel sl = new ScaleImageLabel(enc);
            sl.setUIID("BottomPad");
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

            Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
            Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
            facebook.setTextPosition(BOTTOM);
            twitter.setTextPosition(BOTTOM);

            add(LayeredLayout.encloseIn(
                    sl,
                    BorderLayout.south(
                            GridLayout.encloseIn(2,
                                    facebook,
                                    twitter
                            )
                    )
            ));
        } catch (IOException ex) {
        }
        Label titleLabel = new Label(course.getTitle());
        Label priceLabel = new Label("Price: " + course.getPrice());
        titleLabel.getAllStyles().setFgColor(0x2986cc); // set color to red
        titleLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // set font to bold
        titleLabel.getAllStyles().setAlignment(Component.CENTER);
        BrowserComponent browser = new BrowserComponent();
        if (course.getDecription() != null) {
            browser.setURL(course.getDecription());
        }

        browser.setPreferredSize(new Dimension(500, 700));

// create the back button and add it to the container
        Button backButton = new Button("Back");
        backButton.addActionListener(evt -> {
            new NewsfeedForm(res).show();
        });
       

Container buttonContainer = new Container(new FlowLayout(Component.LEFT));
Label spacer = new Label(" ");
Label spacer1 = new Label(" ");
Label spacer2 = new Label(" ");
Label spacer3 = new Label(" ");
Label spacer4 = new Label(" ");
Label spacer5 = new Label(" ");
buttonContainer.add(backButton);
        addAll(titleLabel, priceLabel, browser,spacer,spacer1,spacer2,spacer3,spacer4,spacer5,buttonContainer);


    }

}
