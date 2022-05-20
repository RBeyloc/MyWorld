package com.company.model;

import com.company.service.LendingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LendingList {

    private List<Lending> lendingList;

    public LendingList() {
        this.lendingList = LendingService.getAllLendings();
    }

    public LendingList(List<Lending> lendingList) {
        this.lendingList = lendingList;
    }

    public List<Lending> getLendingList() {
        return lendingList;
    }

    public void setLendingList(List<Lending> lendingList) {
        this.lendingList = lendingList;
    }

    @Override
    public String toString() {
        String lendingsList = "Lendings List:\n";
        if (!this.lendingList.isEmpty()) {
            for (Lending lending : this.lendingList) {
                lendingsList += lending.toString() + "\n";
            }
        }
        return lendingsList;
    }

    public List<Lending> getLendingListByEjemplar(Ejemplar ejemplarToSearch) {
        List<Lending> ejemplarLendingsList = new ArrayList<Lending>();
        for (Lending lending : this.lendingList) {
            if (lending.getEjemplar().equals(ejemplarToSearch)) {
                ejemplarLendingsList.add(lending);
            }
        }
        return ejemplarLendingsList;
    }

    public Lending getLastLendingByEjemplar(Ejemplar ejemplar) {
        List<Lending> ejemplarLendingsList = this.getLendingListByEjemplar(ejemplar);
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

}
