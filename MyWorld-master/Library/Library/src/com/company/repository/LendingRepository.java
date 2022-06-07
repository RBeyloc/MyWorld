package com.company.repository;

import com.company.model.Ejemplar;
import com.company.model.Lending;
import com.company.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class LendingRepository {

    public static Lending create(Lending lending) {
        //create a manager to do all the CRUD operations with student object
        //I can create manager because I created EntityManagerFactoryUtils
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        //manager call Transaction, that is, it is a state to persist
        EntityTransaction transaction = manager.getTransaction();
        //let s start with begin the operations, thanks to transaction object
        transaction.begin();
        //prepares the operation to be done
        manager.persist(lending);
        //this operation WRITES the object on the actual table
        transaction.commit();
        manager.close();
        return lending;
    }

    public static Lending getLendingByUUID(UUID lendingUUID) {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Lending lendingFound = null;
        lendingFound = manager.find(Lending.class, lendingUUID);

        transaction.commit();
        manager.close();
        return lendingFound;
    }

    public static List<Lending> getAllLendings() {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<Lending> resultLendingsFound = manager.createQuery("SELECT lending FROM Lending lending").getResultList();

        transaction.commit();
        manager.close();
        return resultLendingsFound;
    }

    public static Lending getLastLendingByEjemplarUUID(String ejemplarUUID) {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<Lending> resultLendingsFound = manager.createQuery("SELECT lending FROM Lending lending WHERE lending.ejemplarid = : ejemplarUUID").
                setParameter("ajemplarUUID", ejemplarUUID).getResultList();
        //Take the last one
        Lending resultLendingFound = resultLendingsFound.get(resultLendingsFound.size() - 1);

        transaction.commit();
        manager.close();
        return resultLendingFound;
    }
}
