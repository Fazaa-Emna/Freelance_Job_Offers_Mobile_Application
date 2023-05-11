/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
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
public class AddCourseForm extends BaseForm{
    
      public AddCourseForm(Resources res) {
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

        
        setTitle("Add a new Course");
        setLayout(BoxLayout.y());
   
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        add(LayeredLayout.encloseIn(
                sl
        ));

     
        TextField Title = new TextField("","Title");
        TextField d= new TextField("", "video link");
         TextField category = new TextField("","category");
        TextField photo= new TextField("", "photo");
         TextField price = new TextField("","price");

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

//        
//        TextField Title = new TextField("Title");
//        Title.setUIID("TextFieldBlack");
//        addStringValue("Title", Title);
//   TextField d = new TextField("video link");
//        d.setUIID("TextFieldBlack");
//        addStringValue("video link", d);
//           TextField category = new TextField("category");
//        category.setUIID("TextFieldBlack");
//        addStringValue("Title", category);
//           TextField photo = new TextField("photo");
//        photo.setUIID("TextFieldBlack");
//        addStringValue("photo", photo);
//           TextField price = new TextField("price");
//        price.setUIID("TextFieldBlack");
//        addStringValue("Title", price);
//         
     
        
        
        Button btnValider = new Button("Add Course");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Title.getText().length()==0)||(d.getText().length()==0)||(category.getText().length()==0)||(photo.getText().length()==0)||(price.getText().length()==0))
                    com.codename1.ui.Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                         
                        Course t = new Course(Integer.parseInt(price.getText()), Title.getText(),d.getText(), photo.getText(), category.getText());
                        if( ServiceCourse.getInstance().addCourse(t))
                        {
                           com.codename1.ui.Dialog.show("Success","Course added succesfully",new Command("OK"));
                        }else
                            com.codename1.ui.Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        com.codename1.ui.Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
         Button backButton = new Button("Back");
        backButton.addActionListener(evt -> {
            new CoursesDisplay(res).show();
        });
Container buttonContainer = new Container(new FlowLayout(Component.CENTER));
Label spacer = new Label(" ");
Label spacer1 = new Label(" ");
Label spacer2 = new Label(" ");
Label spacer3 = new Label(" ");
Label spacer4 = new Label(" ");
Label spacer5 = new Label(" ");
buttonContainer.add(backButton);
     
       
       addAll(Title,d,category,photo,price,btnValider,buttonContainer)  ;  
        
     
    }
       private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
