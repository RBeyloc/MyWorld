package com.company.service;

import com.company.model.Ejemplar;
import com.company.model.EjemplarList;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    public static boolean checkEjemplarAvailableByUUID(EjemplarList lista, UUID ejemplarUUID){
        for (Map.Entry<UUID, Ejemplar> entry : lista.getEjemplares().entrySet()) {
            if (entry.getValue().getSku().equals(ejemplarUUID) && entry.getValue().isAvailable()){
                return true;
            }
        }
        return false;
    }

}
