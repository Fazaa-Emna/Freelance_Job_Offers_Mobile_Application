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
import com.mycomany.entities.Service;
import com.mycompany.services.ServiceService;



/**
 *
 * @author Zahra
 */
public class AddServiceForm extends BaseForm{
    
      public AddServiceForm(Resources res) {
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

        
        setTitle("Add a new Service");
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

     
        TextField name = new TextField("","name");
        TextField descr= new TextField("", "descr");
        TextField prix = new TextField("","prix");
         TextField file= new TextField("", "file");
         TextField cat = new TextField("","cat");
       
         

name.setUIID("MyCustomLabel");
Style myLabelStyle = UIManager.getInstance().getComponentStyle("MyCustomLabel");
myLabelStyle.setFgColor(0x000000); 
name.refreshTheme();
descr.setUIID("MyCustomLabe");
Style myLabelStyl = UIManager.getInstance().getComponentStyle("MyCustomLabe");
myLabelStyl.setFgColor(0x000000); 
descr.refreshTheme();
prix.setUIID("MyCustom");
Style myLabel = UIManager.getInstance().getComponentStyle("MyCustom");
myLabel.setFgColor(0x000000); 
prix.refreshTheme();

file.setUIID("MyCustomL");
Style myLabelSt = UIManager.getInstance().getComponentStyle("MyCustomL");
myLabelSt.setFgColor(0x000000); 
file.refreshTheme();
cat.setUIID("MyCustomLab");
Style myLabelSty = UIManager.getInstance().getComponentStyle("MyCustomLab");
myLabelSty.setFgColor(0x000000); 
cat.refreshTheme();

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
     
        
        
        Button btnValider = new Button("Add Service");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((name.getText().length()==0)||(descr.getText().length()==0)||(prix.getText().length()==0)||(file.getText().length()==0)||(cat.getText().length()==0))
                    com.codename1.ui.Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                         
                  Service s = new Service( name.getText(),descr.getText(),Integer.parseInt(prix.getText()), file.getText(), cat.getText());
                        if( ServiceService.getInstance().addService(s))
                        {
                           com.codename1.ui.Dialog.show("Success","Service added succesfully",new Command("OK"));
                        }else
                            com.codename1.ui.Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        com.codename1.ui.Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
     
       
       addAll(name,descr,cat,file,prix,btnValider)  ;  
        
     
    }
       private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
