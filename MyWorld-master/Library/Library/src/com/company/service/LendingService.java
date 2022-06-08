package com.company.service;

import com.company.model.Ejemplar;
import com.company.model.Lending;
import com.company.model.LendingList;
import com.company.model.User;
import com.company.repository.EjemplarRepository;
import com.company.repository.LendingRepository;
import com.company.repository.UserRepository;
import com.company.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class LendingService {

    public static Lending createLending(Lending lending) {
        Lending lendingCreated = LendingRepository.create(lending);
        return lendingCreated;
    }

    public static Lending getLendingByUUID(UUID lendingUUID) {
        Lending lendingFound = null;
        lendingFound = LendingRepository.getLendingByUUID(lendingUUID);
        return lendingFound;
    }

    public static List<Lending> getAllLendings() {
        List<Lending> resultLendingsFound = LendingRepository.getAllLendings();
        return resultLendingsFound;
    }

    public static String listAllLendingsToString() {
        String lendingList = "All lendings:\n";
        List<Lending> result = LendingRepository.getAllLendings();
        if (!result.isEmpty()) {
            for (Lending lending : result) {
                lendingList += lending.toString() + "\n";
            }
        }
        return lendingList;
    }


    public static Lending getLastLendingByEjemplarUUID(String ejemplarUUID) {
        return LendingRepository.getLastLendingByEjemplarUUID(ejemplarUUID);
    }


    public static Lending update(Lending lendingToUpdate) {
        return LendingRepository.update(lendingToUpdate);
    }
}
