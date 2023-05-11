/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Zahra
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
public class Service {
       private int id, prix;
    private String name, descr, file,cat;

    public Service() {
    }

    public Service(int id) {
        this.id = id;
    }

    public Service(String name, String descr,int prix, String file, String cat) {
        this.name=name;
        
        this.descr = descr;
        this.prix = prix;
        this.file = file;
        this.cat = cat;
       
    }

    public Service(int id,String name, String descr,int prix, String file, String cat) {
        
        this.id = id;
        this.name=name;
        
        this.descr = descr;
        this.prix = prix;
        this.file = file;
        this.cat = cat;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
  

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
      public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
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
        final Service other = (Service) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (Objects.equals(this.descr, other.descr)) {
        } else {
            return false;
           }
        if (!Objects.equals(this.file, other.file)) {
            return false;
        }
        if (!Objects.equals(this.cat, other.cat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", prix=" + prix + ", name=" + name + ", descr=" + descr + ", file=" + file + ", cat=" + cat + '}';
    }
    
}

