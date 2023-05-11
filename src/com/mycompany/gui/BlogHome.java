/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;




/**
 *
 * @author zeinab
 */
public class BlogHome extends BaseForm {
      public BlogHome(Resources res) {
     
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddBlog = new Button("Add Blog");
        Button btnListBlogs = new Button("List Blog");
       // btnAddReservation.addActionListener(e-> new AddLigneForm(this).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListBlogs.addActionListener(e-> new ListBlog(res).show());
        add(btnAddBlog);
        add(btnListBlogs);

    }
  
    
}
