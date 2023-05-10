/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author youss
 */
public class Freelance {
    
    
    private int idfreelance;
    private int idbo;   
    private String emailbo;   
    private String categoryF;   
    private String description;   
    private float budget;   
    private String adddate;   
    private String urllogo;   
    private int  nbapplicants;   

    public Freelance(int idfreelance, int idbo, String emailbo, String categoryF, String description, float budget, String adddate, String urllogo, int nbapplicants) {
        this.idfreelance = idfreelance;
        this.idbo = idbo;
        this.emailbo = emailbo;
        this.categoryF = categoryF;
        this.description = description;
        this.budget = budget;
        this.adddate = adddate;
        this.urllogo = urllogo;
        this.nbapplicants = nbapplicants;
    }

    public Freelance() {
    }

    public int getIdfreelance() {
        return idfreelance;
    }

    public void setIdfreelance(int idfreelance) {
        this.idfreelance = idfreelance;
    }

    public int getIdbo() {
        return idbo;
    }

    public void setIdbo(int idbo) {
        this.idbo = idbo;
    }

    public String getEmailbo() {
        return emailbo;
    }

    public void setEmailbo(String emailbo) {
        this.emailbo = emailbo;
    }

    public String getCategoryF() {
        return categoryF;
    }

    public void setCategoryF(String categoryF) {
        this.categoryF = categoryF;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getUrllogo() {
        return urllogo;
    }

    public void setUrllogo(String urllogo) {
        this.urllogo = urllogo;
    }

    public int getNbapplicants() {
        return nbapplicants;
    }

    public void setNbapplicants(int nbapplicants) {
        this.nbapplicants = nbapplicants;
    }
    
    
    
    
    
    
    
    
    
    

    
}
