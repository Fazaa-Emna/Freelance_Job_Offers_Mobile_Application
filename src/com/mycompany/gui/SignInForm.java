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

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import javafx.scene.image.ImageView;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public SignInForm(Resources res) {
//        super(new BorderLayout());
//
//        getTitleArea().setUIID("Container");
//        setUIID("SignIn");
//        Label l = new Label(res.getImage("INSTALANCE"));
//     Form hi = new Form("ImageViewer", new BorderLayout());
//ImageViewer iv = new ImageViewer(res.getImage("art.png"));
//hi.add(BorderLayout.CENTER, iv);
//add(BorderLayout.NORTH,hi);
//        add(BorderLayout.NORTH, new Label(res.getImage("art.png"), "hi"));
//
//        Label username = new Label("Continue as : ");
//
        //  Button signIn = new Button("Freelancer");
        // Button signUp = new Button("Admin");
//        signUp.addActionListener(e -> new SignUpForm(res).show());
//        Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
//        Font f = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
//        l.getUnselectedStyle().setFont(mediumPlainSystemFont);
//        username.getUnselectedStyle().setFont(mediumPlainSystemFont);
//        signIn.getUnselectedStyle().setFont(f);
//        signUp.getUnselectedStyle().setFont(f);
//
//        Container content = BoxLayout.encloseY(
//                username,
//                signIn,
//                signUp
//        );
//
//        content.setScrollableY(true);
//        add(BorderLayout.CENTER, content);
//        signIn.requestFocus();
//        signUp.addActionListener(e -> new SignUpForm(res).show());
//        signIn.addActionListener(e -> new NewsfeedForm(res).show());
//    }
//        super(new BorderLayout());
//
//        if (!Display.getInstance().isTablet()) {
//            BorderLayout bl = (BorderLayout) getLayout();
//            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
//            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
//        }
//        getTitleArea().setUIID("Container");
//        //  setUIID("SignIn");
//
//        add(BorderLayout.NORTH, new Label(res.getImage("art.png"), "LogoLabel"));
//
//        Button signIn = new Button("Freelancer");
//        Button signUp = new Button("Admin");
//
//        // signUp.setUIID("Link");
//        Container content = BoxLayout.encloseY(
//                signIn,
//                signUp,
//                createLineSeparator()
//        );
//     //   content.setScrollableY(true);
//        add(BorderLayout.CENTER, content);
//        signUp.addActionListener(e -> new SignUpForm(res).show());
//        signIn.addActionListener(e -> new NewsfeedForm(res).show());
//    }
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
        Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label l = new Label("INSTALANCE");
        l.getUnselectedStyle().setFont(mediumPlainSystemFont);
        add(BorderLayout.NORTH, new Label(res.getImage("art.png"), "LogoLabel"));

        Button signIn = new Button("Freelancer");
        Button signUp = new Button("Admin");
        signIn.getUnselectedStyle().setFont(f);
        signUp.getUnselectedStyle().setFont(f);
        signUp.addActionListener(e -> new Back(res).show());
        //  signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("");

        Container content = BoxLayout.encloseY(FlowLayout.encloseCenter(l),
                createLineSeparator(),
                createLineSeparator(),
                signIn,
                createLineSeparator(),
                createLineSeparator(),
                createLineSeparator(),
                 signUp
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        signIn.requestFocus();
        signIn.addActionListener(e -> new NewsfeedForm(res).show());
    }

}
