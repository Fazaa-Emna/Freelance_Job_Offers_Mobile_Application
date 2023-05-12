/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author belha
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Objects;

/**
 *
 * @author Zahra
 */
public class Packag {
       private int id_p, sid ,price;
       private String type;

    public Packag() {
    }

    public Packag(int id_p) {
        this.id_p = id_p;
    }

    public Packag(int sid , String type , int price) {
        this.sid=sid;
        
        this.type = type;
        this.price = price;
       
       
    }

    public Packag(int id_p ,int sid , String type , int price) {
        
        this.id_p = id_p;
        this.sid=sid;
        
        this.type = type;
        this.price = price;
  
    }

    public int getidp() {
        return id_p;
    }

    public void setidp(int id_p) {
        this.id_p = id_p;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
    
  

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
      public int getPrice() {
        return price;
    }

    public void setPrix(int price) {
        this.price = price;
    }

  

   

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Packag other = (Packag) obj;
        if (this.id_p != other.id_p) {
            return false;
        }
        if (this.sid != other.sid) {
            return false;
        }
         if (this.price != other.price) {
        
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
            return false;
        }
       
        return true;
    }

    @Override
    public String toString() {
        return "Packag{" + "id_p=" + id_p + ", sid=" + sid + ", type=" + type + ", price=" + price + '}';
    }
    
}


