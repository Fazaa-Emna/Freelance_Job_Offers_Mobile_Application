/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.mycompany.entities.Blog;
import com.mycompany.services.ServiceBlog;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author zeinab
 */
public class ListBlog extends Form{
    
     public ListBlog(Resources res) {
    setTitle("List Ligne");
    setLayout(BoxLayout.y());
    ArrayList<Blog> blog = ServiceBlog.getInstance().getAllBlogs();
    
    for (Blog bl : blog) {
        Container card = new Container(new BorderLayout());
        card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card.getStyle().setMargin(Component.BOTTOM, 10);
        card.getStyle().setBgColor(0xFFFFFF);

        Label clientLabel = new Label("id: " + bl.getId());
        Label dateLabel = new Label("Title: " + bl.getTitle());
        Label heureDepartLabel = new Label("Body: " + bl.getBody());
       
        
        clientLabel.getStyle().setFgColor(0x000000);
        dateLabel.getStyle().setFgColor(0x000000);
        heureDepartLabel.getStyle().setFgColor(0x000000);
       
        card.add(BorderLayout.NORTH, clientLabel);
        card.add(BorderLayout.CENTER, BoxLayout.encloseY(dateLabel, heureDepartLabel));
        this.add(card);
        Button btndelete = new Button("delete");
        add(btndelete);
        btndelete.addActionListener((e) -> {
       com.mycompany.services.ServiceBlog.getInstance().suppBlog(bl);
       ListBlog refresh = new ListBlog(res);
       refresh.show();

});
        
        Button updateButton = new Button("Update Blog");
updateButton.addActionListener(e -> {
    Form updateForm = new Form("Update Blog");

    TextField title= new TextField("", "title");
     TextField body = new TextField("", "body");

    Button submitButton = new Button("Submit");
    submitButton.addActionListener(submitEvent -> {
        // Get the updated values of the fields
       
        String title1 = title.getText();
        String body1 = body.getText();

        // Call the service function to update the station
               com.mycompany.services.ServiceBlog.getInstance().modifieBlog(bl.getId(), title1, body1);

     

        // Show a confirmation message to the user
        Dialog.show("Success", "Blog updated successfully", "OK", null);
            ListBlog refresh = new ListBlog(res);
     refresh.show();
    });

    updateForm.add(title).add(body).add(submitButton);
    updateForm.show();
    
});

add(updateButton);
        
        
    }

}
}
