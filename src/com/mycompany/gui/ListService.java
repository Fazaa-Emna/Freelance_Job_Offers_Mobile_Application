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

import com.mycomany.entities.Service;
import com.mycompany.services.ServiceService;


/**
 *
 * @author Zahra
 */
public class ListService extends Form{
      public ListService(Form previous) {
        setTitle("List of Services");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        for (Service service : ServiceService.getInstance().getAllServices()) {
            MultiButton multiButton = new MultiButton(service.getName());
            multiButton.setTextLine2("Price: " + service.getPrix());
            multiButton.setTextLine3("Category: " + service.getCat());
            add(multiButton);
        }

        getToolbar().addCommandToLeftBar("Back", null, e -> previous.showBack());
    }
}
