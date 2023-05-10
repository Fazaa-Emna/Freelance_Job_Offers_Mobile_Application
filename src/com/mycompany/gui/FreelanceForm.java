/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Freelance;
import com.mycompany.services.Service_Freelance;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Emna Fazaa
 */
public class FreelanceForm extends BaseForm {

    Resources theme = UIManager.initFirstTheme("/themeCoHeal");

    public FreelanceForm(Form previous) {
    super("Freelances", BoxLayout.y());

    // Add an InfiniteProgress component while loading data
    this.add(new InfiniteProgress());

    // Schedule a background task to load data
    Display.getInstance().scheduleBackgroundTask(() -> {
        // This will take a while...

        // Execute the following code on the EDT (Event Dispatch Thread)
        Display.getInstance().callSerially(() -> {
            // Remove all components from the form
            this.removeAll();

            // Fetch data from Service_Freelance and add items to the form
            for (Freelance f : new Service_Freelance().findAll()) {
                this.add(addItem_Offer(f));
            }

            // Revalidate the form to update the layout
            this.revalidate();
        });
    });

    // Add a search command to the toolbar
    this.getToolbar().addSearchCommand(e -> {
        String text = (String) e.getSource();
        if (text == null || text.length() == 0) {
            // Clear search: show all components
            for (Component cmp : this.getContentPane()) {
                cmp.setHidden(false);
                cmp.setVisible(true);
            }
            this.getContentPane().animateLayout(150);
        } else {
            text = text.toLowerCase();
            for (Component cmp : this.getContentPane()) {
                MultiButton mb = (MultiButton) cmp;
                String line1 = mb.getTextLine1();
                String line2 = mb.getTextLine2();

                // Apply UIID styling
                mb.setUIIDLine1("libC");
                mb.setUIIDLine2("btn");

                // Show or hide components based on the search text
                boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                        || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                mb.setHidden(!show);
                mb.setVisible(show);
            }
            this.getContentPane().animateLayout(150);
        }
    }, 4);

    // Add a command to the toolbar for adding an offer
    this.getToolbar().addCommandToRightBar("Add Offer", null, ev -> {
        try {
            new AddFreelance(this).show();
        } catch (Exception ex) {
            // Handle any exceptions
        }
    });
}

//details of the offer function   
    public Container addItem_Offer_detail(Freelance f) {
        // Create containers for layout
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());

        // Create labels for displaying details
        Label idF = new Label("Id Freelance  : " + f.getIdfreelance());
        Label idBO = new Label("ID BO : " + f.getIdbo());
        Label email = new Label("Email : " + f.getEmailbo());
        Label cat = new Label("categoryF : " + f.getCategoryF());
        Label desc = new Label("description : " + f.getCategoryF());
        Label salary = new Label("budget : " + f.getBudget());
        Label addDate = new Label("Date Add : " + f.getAdddate());
        Label nbApplicants = new Label("nbapplicants : " + f.getNbapplicants());

        // Create a button for taking a screenshot
        Button screen = new Button("ScreenShot");

        // Add labels and button to the second container
        cn2.add(idF).add(idBO).add(cat).add(desc).add(email).add(salary).add(addDate).add(nbApplicants).add(screen);

        // Add the second container to the first container (with WEST layout constraint)
        cn1.add(BorderLayout.WEST, cn2);

        // Add an action listener to the screenshot button
        screen.addActionListener(e -> {
            Form form = Display.getInstance().getCurrent();
            if (form != null) {
                // Capture a screenshot of the current form
                Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
                form.revalidate();
                form.setVisible(true);
                form.paintComponent(screenshot.getGraphics(), true);

                // Save the screenshot as an image file
                String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
                try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
                } catch (IOException err) {
                    Log.e(err);
                }
            }
        });

        return cn1;
    }

    public MultiButton addItem_Offer(Freelance f) {

        MultiButton m = new MultiButton();

        // Create a notification toast
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Welcome: Business Owner Session!");
        status.setExpires(4000);
        status.show();

        ImageViewer image_coach;
        Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);

        Label type = new Label("Category  : " + f.getCategoryF());
        Label datems = new Label("email  : " + f.getEmailbo());

        // Set the text lines and emblem for the MultiButton
        m.setTextLine1(f.getCategoryF());
        m.setTextLine2(f.getEmailbo());
        m.setEmblem(theme.getImage("round.png"));

        // Set an action listener for the MultiButton
        m.addActionListener(l -> {
            Form f2 = new Form("Detail", BoxLayout.y());

            // Add the detail item to the form
            f2.add(addItem_Offer_detail(f));
            f2.getToolbar().addCommandToOverflowMenu("Back", null, ev -> {
                new FreelanceForm(this).showBack();
            });
            f2.show();
        });

        return m;

    }

}
