package com.company.service;

import com.company.model.Ejemplar;
import com.company.model.EjemplarList;
import com.company.repository.EjemplarRepository;


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

    public static void create(Ejemplar ejemplarToSave) {
        EjemplarRepository.create(ejemplarToSave);
    }

    public static boolean checkEjemplarAvailableByUUID(UUID ejemplarUUID){

        Ejemplar ejemplar = EjemplarRepository.getEjemplarByUUID(ejemplarUUID);

        return ejemplar.isAvailable();

    }

}
