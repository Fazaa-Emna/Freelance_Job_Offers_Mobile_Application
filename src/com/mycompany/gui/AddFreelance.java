/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Freelance;
import com.mycompany.services.Service_Freelance;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Emna Fazaa
 */
public class AddFreelance extends BaseForm {

    String file;
    String file2;
    Resources theme;

    public AddFreelance(Form previous) throws IOException {
        super("Add Freelance", BoxLayout.y());
        theme = UIManager.initFirstTheme("/themeCoHeal");

        Label AJOUT = new Label("ADD New Freelance");
        this.add(AJOUT);

        TextField nom = new TextField("", "email BO", 20, TextArea.TEXT_CURSOR);
        TextField description = new TextField("", "categoryF", 20, TextArea.TEXT_CURSOR);
        TextField lieuEvent = new TextField("", "description", 20, TextArea.TEXT_CURSOR);
        TextField budgetEvent = new TextField("", "budget", 20, TextArea.TEXT_CURSOR);

        Picker adddate = new Picker();
        Button save = new Button("Ajouter");

        this.add("Email : ").add(nom);
        this.add("categoryF : ").add(description);
        this.add("description : ").add(lieuEvent);
        this.add("budget : ").add(budgetEvent);
        this.add("date add : ").add(adddate);

        this.add(save);

        save.addActionListener(l
                -> {

            if (nom.getText().equals("")) {
                com.codename1.ui.Dialog.show("Erreur", "Champ vide de Email ", "OK", null);

            } else if (description.getText().equals("")) {
                com.codename1.ui.Dialog.show("Erreur", "Champ vide Category  ", "OK", null);

            } else if (lieuEvent.getText().equals("")) {
                com.codename1.ui.Dialog.show("Erreur", "Champ vide description  ", "OK", null);

            } else if (budgetEvent.getText().equals("")) {
                com.codename1.ui.Dialog.show("Erreur", "Taper budget ", "OK", null);

            } else {

                try {

                    DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
                    Date ddebut = adddate.getDate();
                    String dated = dd.format(ddebut);

                    Freelance c = new Freelance();

                    c.setEmailbo(nom.getText());

                    c.setDescription(lieuEvent.getText());

                    c.setUrllogo("img.jpeg");

                    c.setCategoryF(description.getText());

                    c.setAdddate(dated);

                    c.setBudget(Float.valueOf(budgetEvent.getText()));

                    c.setIdbo(20);
                    c.setNbapplicants(2);

                    System.out.println("forms.addEvet.addItem()" + c);
                    new Service_Freelance().AddFreelance(c);
                    com.codename1.ui.Dialog.show("Ajouter Freelance", "Ajouter Freelance aves success ", "OK", null);

                    /////////////////////////////////////   Notification     /////////////////////
                    ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setMessage("Freelance de category  " + c.getCategoryF() + "  ajoute avec succes");
                    status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
                    status.show();
                    System.out.println("hallllllllllllllllllllllllllllllo");
////////////////////////////////////////////

                } catch (Exception ex) {
                    System.out.println("hekllllo");
                }

            }

        }
        );

        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
        );

        this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
            try {
                new FreelanceForm(this).showBack();
            } catch (Exception ex) {

            }

        });

        this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
            this.showBack();
        });

        this.show();

    }

    private void addButton(Image img, Resources res) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        add(cnt);

    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("", "ImageOverLay");

    }

}
