package com.company.model;

import java.util.HashMap;

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

    public HashMap<String, Lending> getLendingList() {
        return lendingList;
    }

    public void setLendingList(HashMap<String, Lending> lendingList) {
        this.lendingList = lendingList;
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
