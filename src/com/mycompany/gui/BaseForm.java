/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    EncodedImage enc;

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        try {
            enc = EncodedImage.create("/art.png");

            ScaleImageLabel sl = new ScaleImageLabel(enc);
            sl.setUIID("BottomPad");
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    sl
            ));
        } catch (IOException ex) {
        }
        // tb.addMaterialCommandToSideMenu("Add Course", FontImage.MATERIAL_UPDATE, e-> new AddCourseForm(res).show());
        tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_UPDATE, e -> new ListBlog(res).show());
        tb.addMaterialCommandToSideMenu("Courses", FontImage.MATERIAL_UPDATE, e -> new CoursesDisplay(res).show());
        tb.addMaterialCommandToSideMenu("Services", FontImage.MATERIAL_UPDATE, e -> new ServicesDisplay(res).show());
        tb.addMaterialCommandToSideMenu("Show all offers", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListeFreelance(res).show());
        tb.addMaterialCommandToSideMenu(" EVENT", FontImage.MATERIAL_EXIT_TO_APP, e -> new EventDisplay(res).show());
        tb.addMaterialCommandToSideMenu("Freelance Management", FontImage.MATERIAL_EXIT_TO_APP, e -> new FreelanceForm(this).show());
        // tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            Display.getInstance().exitApplication();
        });

    }

    protected void addSideMenuB(Resources res) {
        Toolbar tb = getToolbar();
        try {
            enc = EncodedImage.create("/art.png");

            ScaleImageLabel sl = new ScaleImageLabel(enc);
            sl.setUIID("BottomPad");
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    sl
            ));
        } catch (IOException ex) {
        }

        tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_UPDATE, e -> new ListBlog(res).show());
        tb.addMaterialCommandToSideMenu("Courses", FontImage.MATERIAL_UPDATE, e -> new CoursesDisplay(res).show());
        tb.addMaterialCommandToSideMenu("Services", FontImage.MATERIAL_UPDATE, e -> new ServicesDisplay(res).show());
        tb.addMaterialCommandToSideMenu("Offers", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListeFreelance(res).show());
        tb.addMaterialCommandToSideMenu("Events", FontImage.MATERIAL_EXIT_TO_APP, e -> new EventDisplay(res).show());
       // tb.addMaterialCommandToSideMenu("Freelance Management", FontImage.MATERIAL_EXIT_TO_APP, e -> new FreelanceForm(this).show());
        tb.addMaterialCommandToSideMenu("Exit", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            Display.getInstance().exitApplication();
        });

    }
}
