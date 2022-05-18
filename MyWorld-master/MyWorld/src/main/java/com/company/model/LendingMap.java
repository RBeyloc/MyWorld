package com.company.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class LendingMap {

    private HashMap<String, Lending> lendingList;

    public LendingMap(HashMap<String, Lending> lendingList) {
        this.lendingList = lendingList;
    }

    public LendingMap() {
        this.lendingList = new HashMap<>();
    }

    public boolean addLending(Lending lending) {
        try {
            this.lendingList.put(String.valueOf(lending.getLendingUuid()), lending);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Lending getLastLendingByEjemplar(Ejemplar ejemplar) {
        HashMap<String, Lending> ejemplarLendingsMap = this.getLendingListByEjemplar(ejemplar);
        LocalDate lastLendingDate = LocalDate.EPOCH;
        UUID sku = null;
        for (Lending lending : ejemplarLendingsMap.values()) {
            if (lending.getLendingDate().isAfter(lastLendingDate)) {
                lastLendingDate = lending.getLendingDate();
                sku = lending.getLendingUuid();
            }
        }
        return ejemplarLendingsMap.get(sku);
    }

    public HashMap<String, Lending> getLendingList() {
        return lendingList;
    }

    public void setLendingList(HashMap<String, Lending> lendingList) {
        this.lendingList = lendingList;
    }

    public HashMap<String, Lending> getLendingListByEjemplar(Ejemplar ejemplarToSearch) {
        HashMap<String, Lending> ejemplarLendingsMap = new HashMap<String, Lending>();
        for (Lending  lending : this.lendingList.values()) {
            if (lending.getEjemplar().equals(ejemplarToSearch)) {
                ejemplarLendingsMap.put(String.valueOf(lending.getEjemplar().getSku()), lending);
            }
        }
        return ejemplarLendingsMap;
    }

    @Override
    public String toString() {
        String lendingsList = "Lendings Map:\n";
        if(!this.lendingList.isEmpty()) {
            for(Lending lending : this.lendingList.values()) {
                lendingsList += lending.toString() + "\n";
            }
        }
        return lendingsList;
    }
}
