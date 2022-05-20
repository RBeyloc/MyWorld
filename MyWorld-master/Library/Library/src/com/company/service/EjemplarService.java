package com.company.service;

import com.company.model.Ejemplar;
import com.company.model.EjemplarList;
import com.company.model.Lending;
import com.company.repository.EjemplarRepository;
import com.company.repository.LendingRepository;

import java.util.HashMap;
import java.util.List;
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
            if (entry.getValue().getSku().equals(ejemplarUUID) && entry.getValue().isAvailable() == 1){
                return true;
            }
        }
        return false;
    }

    public static Ejemplar create(Ejemplar ejemplar) {
        Ejemplar ejemplarCreated = EjemplarRepository.create(ejemplar);
        return ejemplarCreated;
    }

    public static List<Ejemplar> getAllEjemplars() {
        List<Ejemplar> resultEjemplarsFound = EjemplarRepository.getAllEjemplars();
        return resultEjemplarsFound;
    }

}
