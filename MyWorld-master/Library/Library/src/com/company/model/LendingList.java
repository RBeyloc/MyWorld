package com.company.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;

public class LendingList {

    private LinkedList<Lending> lendingList;

    public LendingList() {
        this.lendingList = new LinkedList<>();
    }

    public LendingList(LinkedList<Lending> lendingList) {
        this.lendingList = lendingList;
    }

    public boolean addLending(Lending lending) {
        try {
            // this.lendingList.put(String.valueOf(lending.getLendingUuid()), lending);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LinkedList<Lending> getLendingList() {
        return lendingList;
    }

    public void setLendingList(LinkedList<Lending> lendingList) {
        this.lendingList = lendingList;
    }

    public Lending getLastLendingByEjemplar(Ejemplar ejemplar) {
        LinkedList<Lending> ejemplarLendingsList = this.getLendingListByEjemplar(ejemplar);
        LocalDate lastLendingDate = LocalDate.EPOCH;
        int currentPosition = 0;
        int lastLendingPosition = currentPosition;
        for (Lending lending : ejemplarLendingsList) {
            if (lending.getLendingDate().isAfter(lastLendingDate)) {
                lastLendingDate = lending.getLendingDate();
                lastLendingPosition = currentPosition;
            }
            currentPosition++;
        }
        return ejemplarLendingsList.get(lastLendingPosition);
    }

    public LinkedList<Lending> getLendingListByEjemplar(Ejemplar ejemplarToSearch) {
        LinkedList<Lending> ejemplarLendingsList = new LinkedList<Lending>();
        for (Lending  lending : this.lendingList) {
            if (lending.getEjemplar().equals(ejemplarToSearch)) {
                ejemplarLendingsList.add(lending);
            }
        }
        return ejemplarLendingsList;
    }

    @Override
    public String toString() {
        String lendingsList = "Lendings List:\n";
        if(!this.lendingList.isEmpty()) {
            for(Lending lending : this.lendingList) {
                lendingsList += lending.toString() + "\n";
            }
        }
        return lendingsList;
    }
}
