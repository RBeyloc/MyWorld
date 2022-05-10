package com.company.model;

import java.util.Date;
import java.util.UUID;

public class Ejemplar {

    private final UUID sku;
    private String ejemplarTitle;
    private String author;
    private final Date dateOfAdquisition;
    private boolean available;

    public Ejemplar (String itemTitle, String author){
        this.ejemplarTitle = itemTitle;
        this.author = author;
        this.dateOfAdquisition = new Date();
        this.sku = UUID.randomUUID();
        this.available = true;

    }

    public UUID getSku() {
        return sku;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getEjemplarTitle() {

        return ejemplarTitle;
    }

    public void setEjemplarTitle(String itemTitle) {

        this.ejemplarTitle = itemTitle;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getDateOfAdquisition() {

        return dateOfAdquisition;
    }

    @Override
    public String toString(){
        return this.getSku()  + " - " + this.getEjemplarTitle() + " - " + this.getAuthor() + " - Available: " + this.isAvailable();
    }




}
