/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import com.codename1.ui.util.Resources;

/**
 *
 * @author zeinab
 */
public class Blog {
      private int id;
   private String title;
   private  String body;

    public Blog() {
    }

    public Blog(int id,String body,String title) {
        this.id=id;
        this.body=body;
        this.title=title;
        
    }

    public Blog(String body,String title) {
        this.body=body;
        this.title=title;
    }

   

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    
    public String getBody() {
        return body;
    }

    
   

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", Title=" + title + ", Body=" + body + '}';
    }
}
