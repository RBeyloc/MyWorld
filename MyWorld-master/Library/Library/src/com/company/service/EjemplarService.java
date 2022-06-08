package com.company.service;

import com.company.model.Ejemplar;
import com.company.model.EjemplarList;
import com.company.model.Lending;
import com.company.model.User;
import com.company.repository.EjemplarRepository;
import com.company.repository.LendingRepository;
import com.company.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EjemplarService {

    public static Ejemplar create(Ejemplar ejemplar) {
        Ejemplar ejemplarCreated = EjemplarRepository.create(ejemplar);
        return ejemplarCreated;
    }

    public static List<Ejemplar> getAllEjemplars() {
        List<Ejemplar> resultEjemplarsFound = EjemplarRepository.getAllEjemplars();
        return resultEjemplarsFound;
    }

    public static String listAllEjemplaresToString() {
        String allEjemplaresList = "All ejemplares:\n";
        List<Ejemplar> result = EjemplarRepository.getAllEjemplars();
        if (!result.isEmpty()) {
            for (Ejemplar ejemplar : result) {
                allEjemplaresList += ejemplar.toString() + "\n";
            }
        }
        return allEjemplaresList;
    }

    public static boolean checkEjemplarAvailableByUUID(UUID ejemplarUUID){
        Ejemplar gotEjemplar = EjemplarRepository.getEjemplarByUUID(ejemplarUUID);
        return gotEjemplar.getAvailable();
    }

    public static String getEjemplarsAvailable() {
        List<Ejemplar> resultEjemplarsFound = EjemplarRepository.getEjemplarsAvailable();
        String itemsList = "Items Available:\n";
        if (!resultEjemplarsFound.isEmpty()) {
            for (Ejemplar ejemplar : resultEjemplarsFound) {
                itemsList += ejemplar.toString() + "\n";
            }

        }
        return itemsList;
    }

    public static String listAvailabledEjemplaresToString() {
        String availableEjemplaresList = "Available ejemplares:\n";
        List<Ejemplar> result = EjemplarRepository.getEjemplarsAvailable();
        if (!result.isEmpty()) {
            for (Ejemplar ejemplar : result) {
                availableEjemplaresList += ejemplar.toString() + "\n";
            }
        }
        return availableEjemplaresList;
    }

    public static Ejemplar getEjemplarByUuid(UUID ejemplarUUID) {
        Ejemplar ejemplar = EjemplarRepository.getEjemplarByUUID(ejemplarUUID);
        return ejemplar;
    }

    public static Ejemplar update(Ejemplar ejemplarToUpdate) {
        return EjemplarRepository.update(ejemplarToUpdate);
    }

}
