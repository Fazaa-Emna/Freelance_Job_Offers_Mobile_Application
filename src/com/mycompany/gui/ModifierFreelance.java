/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Freelance;
import com.mycompany.services.Service_Freelance;

/**
 * Represents the "ModifierFreelance" class that extends the "BaseForm" class.
 * It provides functionality for modifying a freelance record.
 *
 * @author Emna Fazaa
 */
public class ModifierFreelance extends BaseForm {

    Form current; // The current form

    /**
     * Constructs a new instance of the ModifierFreelance class.
     *
     * @param res The resources object for retrieving UI elements.
     * @param rec The freelance record to modify.
     */
    public ModifierFreelance(Resources res, Freelance rec) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Update Offer");
        getContentPane().setScrollVisible(false);

        // Adding search command to the toolbar
        tb.addSearchCommand(e -> {
            // TODO: Add search functionality
        });

        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();
        addTab(swipe, s1, res.getImage("back-logoo.jpg"), "", "", res);

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        super.addSideMenu(res);

        TextField email = new TextField(rec.getEmailbo(), "Email", 20, TextField.ANY);
        TextField cat = new TextField(rec.getCategoryF(), "Category", 20, TextField.ANY);
        TextField desc = new TextField(rec.getDescription(), "Description", 20, TextField.ANY);

        email.setUIID("NewsTopLine");
        email.setSingleLineTextArea(true);

        cat.setUIID("NewsTopLine");
        cat.setSingleLineTextArea(true);

        desc.setUIID("NewsTopLine");
        desc.setSingleLineTextArea(true);

        Button btnModifier = new Button("Save");

        // Event onClick btnModifier
        btnModifier.addPointerPressedListener(l -> {
            rec.setEmailbo(email.getText());
            rec.setCategoryF(cat.getText());
            rec.setDescription(desc.getText());

            if (Service_Freelance.getInstance().ModifierEvent(rec)) {
                new ListeFreelance(res).show();
            }
        });

        Button btnAnnuler = new Button("Cancel");
        btnAnnuler.addActionListener(l -> {
            new ListeFreelance(res).show();
        });

        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        Label l4 = new Label();
        Label l5 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(cat),
                createLineSeparator(),
                new FloatingHint(desc),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );
        add(content);
        show();
    }

    /**
     * Adds a tab to the swipe container.
     *
     * @param swipe The swipe container to add the tab to.
     * @param spacer A label used for spacing.
     * @param image The image to display in the tab.
     * @param string A string representing the tab.
     * @param text The text to display in the tab.
     * @param res The resources object for retrieving UI elements.
     */
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

        Container page1 = LayeredLayout.encloseIn(
                imageScale,
                overLay,
                BorderLayout.south(
                        BoxLayout.encloseY(
                                new SpanLabel(text, "LargeWhiteText"),
                                spacer
                        )
                )
        );

        swipe.addTab("", res.getImage("back-logo.jpeg"), page1);
    }

    /**
     * Updates the position of the arrow based on the selected button.
     *
     * @param btn The selected button.
     * @param l The label representing the arrow.
     */
    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

    /**
     * Binds button selection to arrow position.
     *
     * @param btn The button to bind.
     * @param l The label representing the arrow.
     */
    public void bindButtonSelection(Button btn, Label l) {
        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
    }
}
