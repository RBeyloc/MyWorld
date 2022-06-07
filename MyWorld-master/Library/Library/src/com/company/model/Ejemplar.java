package com.company.model;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EJEMPLAR_TABLE")
public class Ejemplar {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private final UUID sku;
    private String ejemplarTitle;
    private String author;
    private final Date dateOfAdquisition;
    private boolean available;

    public Ejemplar() {
        this.dateOfAdquisition = new Date();
        this.sku = UUID.randomUUID();
        this.available = true;
    }

    public Ejemplar (String itemTitle, String author){
        this.ejemplarTitle = itemTitle;
        this.author = author;
        this.dateOfAdquisition = new Date();
        this.sku = UUID.randomUUID();
        this.available = true;

    }

    public Ejemplar (String itemTitle, String author, boolean availability){
        this.ejemplarTitle = itemTitle;
        this.author = author;
        this.dateOfAdquisition = new Date();
        this.sku = UUID.randomUUID();
        this.available = availability;

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

    public Boolean getAvailable(){
        return this.available;
    }

    @Override
    public String toString(){
        return this.getSku()  + " - " + this.getEjemplarTitle() + " - " + this.getAuthor() + " - Available: " + this.isAvailable();
    }




}
