package com.company.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EjemplarList {
    private HashMap<UUID, Ejemplar> ejemplares;

    public EjemplarList(){
        ejemplares = new HashMap<>();
        createFakeEjemplares();

    }

    public boolean add (Ejemplar ejemplar){
        try {
            this.ejemplares.put(ejemplar.getSku(), ejemplar);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public HashMap<UUID, Ejemplar> listAvailableEjemplares(){
        HashMap<UUID, Ejemplar> onlyAvailable = new HashMap<>();

        for (Map.Entry<UUID, Ejemplar> entry : ejemplares.entrySet()) {
            if (entry.getValue().isAvailable()){
                onlyAvailable.put(entry.getValue().getSku(), entry.getValue());
            }
        }
        return onlyAvailable;
    }

    public Ejemplar findBySku(UUID sku){
        Ejemplar chosenEjemplar = null;
        for (Map.Entry<UUID, Ejemplar> entry : ejemplares.entrySet()) {
            if (entry.getValue().getSku().equals(sku)){
                chosenEjemplar = entry.getValue();
            }
        }
        return chosenEjemplar;
    }

    //Getters y Setters
    public HashMap<UUID, Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(HashMap<UUID, Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    @Override
    public String toString() {
        String usersList = "Items Map:\n";
        if(!this.ejemplares.isEmpty()) {
            for(Ejemplar ejemplar : this.ejemplares.values()) {
                usersList += ejemplar.toString() + "\n";
            }
        }
        return usersList;
    }

    public void createFakeEjemplares(){
        Ejemplar ejemplar01 = new Ejemplar("Around the World in 80 days", "Jules Verne");
        Ejemplar ejemplar02 = new Ejemplar("Mrs. Dalloway", "Virginia Woolf");
        Ejemplar ejemplar03 = new Ejemplar("To Kill a Mockingbird" , "Harper Lee");
        Ejemplar ejemplar04 =  new Ejemplar("Cumbres Borrascosas" , "Emily Bronte");
        Ejemplar ejemplar05 =  new Ejemplar("Moby Dick" , "Herman Melville");
        Ejemplar ejemplar06 =  new Ejemplar("Anna Karenina" , "Leon Tolstoi");


        ejemplares.put(ejemplar01.getSku(), ejemplar01);
        ejemplares.put(ejemplar02.getSku(), ejemplar02);
        ejemplares.put(ejemplar03.getSku(), ejemplar03);
        ejemplares.put(ejemplar04.getSku(), ejemplar04);
        ejemplares.put(ejemplar05.getSku(), ejemplar05);
        ejemplares.put(ejemplar06.getSku(), ejemplar06);

    }



}


