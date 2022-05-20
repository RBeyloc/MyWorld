package com.company.service;

import com.company.model.Lending;
import com.company.model.LendingList;
import com.company.repository.LendingRepository;
import com.company.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class LendingService {

    public static Lending create(Lending lending) {
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

}
