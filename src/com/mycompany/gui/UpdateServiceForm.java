/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import static com.codename1.push.PushContent.setTitle;
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
import com.mycomany.entities.Service;
import com.mycompany.services.ServiceService;

/**
 *
 * @author belhassan
 */
public class UpdateServiceForm extends BaseForm {
    public UpdateServiceForm(Resources res, Service service) {
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

        setTitle("Modify your Service");
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
        Label id = new Label(String.valueOf(service.getid()));
        id.setVisible(false);
         TextField name = new TextField(service.getName());

        addStringValue("name", name);

        TextField descr = new TextField(service.getDescr());

        addStringValue("descr", descr);
        
        TextField prix = new TextField(String.valueOf(service.getPrix()));

        addStringValue("prix", prix);
        
        TextField file = new TextField(service.getFile());

        addStringValue("file", file);

        TextField cat = new TextField(service.getCat());

        addStringValue("category", cat);

        name.setUIID("MyCustomLabel");
        descr.setUIID("MyCustomLabel");
         prix.setUIID("MyCustomLabel");
       
        file.setUIID("MyCustomLabel");
        cat.setUIID("MyCustomLabel");
        Style myLabelStyle = UIManager.getInstance().getComponentStyle("MyCustomLabel");
        myLabelStyle.setFgColor(0x000000);
        name.refreshTheme();
        descr.refreshTheme();
        prix.refreshTheme();
        file.refreshTheme();
        cat.refreshTheme();
       
      
        Button delete = new Button("Delete");
         delete.addActionListener(evt -> {
            boolean userResponse = com.codename1.ui.Dialog.show("Alert", "Are you sure you want to delete this service?", "Yes", "No");
            if (userResponse) {
                ServiceService.getInstance().deleteService(service);
                com.codename1.ui.Dialog.show("Success", "The service has been deleted.", new com.codename1.ui.Command("OK"));
                new ServicesDisplay(res).show();
            }
        });
         
         Button btnValider = new Button("Modify");
        addStringValue("", btnValider);
        addStringValue("", delete);
        addStringValue("", id);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((name.getText().length() == 0) || (descr.getText().length() == 0) || (cat.getText().length() == 0) || (file.getText().length() == 0) || (prix.getText().length() == 0)) {
                    com.codename1.ui.Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    Service s = new Service(Integer.parseInt(id.getText()), name.getText(), descr.getText(), Integer.parseInt(prix.getText()), file.getText(), cat.getText());
                    if (ServiceService.getInstance().updateService(s)) {
                        com.codename1.ui.Dialog.show("Success", "service updated succesfully", new Command("OK"));
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

