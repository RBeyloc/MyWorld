package com.company.model;

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
    private int available;

    public Ejemplar() {
        this.dateOfAdquisition = new Date();
        this.sku = UUID.randomUUID();
        this.available = 1;
    }

    public Ejemplar (String itemTitle, String author){
        this.ejemplarTitle = itemTitle;
        this.author = author;
        this.dateOfAdquisition = new Date();
        this.sku = UUID.randomUUID();
        this.available = 1;

    }

    public Ejemplar (String itemTitle, String author, int availability){
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


    public int isAvailable() {
        return available;
    }

    public void setAvailable(int available) {
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
