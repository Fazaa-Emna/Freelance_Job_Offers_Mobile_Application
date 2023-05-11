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
public class Lesson {
      private int cid; 
    private int lid; 
    private String name; 
    private String file; 

    public Lesson() {
    }

    public Lesson(int lid) {
        this.lid = lid;
    }

    public Lesson(int lid, String name, String file) {
        this.lid = lid;
        this.name = name;
        this.file = file;
    }

    public Lesson(int cid, int lid, String name, String file) {
        this.cid = cid;
        this.lid = lid;
        this.name = name;
        this.file = file;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Lesson other = (Lesson) obj;
        if (this.cid != other.cid) {
            return false;
        }
        if (this.lid != other.lid) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.file, other.file)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lesson{" + "cid=" + cid + ", lid=" + lid + ", name=" + name + ", file=" + file + '}';
    }
    
}

