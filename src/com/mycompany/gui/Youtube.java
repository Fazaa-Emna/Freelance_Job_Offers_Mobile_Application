/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Asus
 */
public class Youtube extends Form {

    Resources res1;

    public Youtube() {
        setTitle("Youtube");
        setLayout(new BorderLayout());

        // Create a browser component to display the YouTube video
        BrowserComponent browser = new BrowserComponent();
        res1 = UIManager.initFirstTheme("/theme");
        browser = new BrowserComponent();
        browser.setURL("https://www.youtube.com/watch?v=to4IDjJVays");
        this.addComponent(BorderLayout.CENTER, browser);
        this.show();

        // Add a "Back" command to the toolbar to navigate back to the previous form
        this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
            try {
                new NewsfeedForm(res1).showBack();
            } catch (Exception ex) {
                // Handle any exceptions that may occur
            }
        });
    }
}
