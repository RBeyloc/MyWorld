package com.company.service;

import com.company.model.Ejemplar;
import com.company.model.EjemplarList;

import java.util.HashMap;

public class EjemplarService {

    public static String listAvailableEjemplaresToString(EjemplarList lista) {
        String itemsList = "Items Available:\n";
        if (!lista.listAvailableEjemplares().isEmpty()) {
            for (Ejemplar ejemplar : lista.listAvailableEjemplares().values()) {
                itemsList += ejemplar.toString() + "\n";
            }

        }
        return itemsList;
    }

}
