package com.company.repository;

import com.company.model.Ejemplar;
import com.company.model.User;
import com.company.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class EjemplarRepository {

    public static Ejemplar create(Ejemplar ejemplarToSave){
        //create a manager to do all the CRUD operations with student object
        //I can create manager because I created EntityManagerFactoryUtils
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        //manager call Transaction, that is, it is a state to persist
        EntityTransaction transaction = manager.getTransaction();
        //let s start with begin the operations, thanks to transaction object
        transaction.begin();
        //prepares the operation to be done
        manager.persist(ejemplarToSave);
        //this operation WRITES the object on the actual table
        transaction.commit();
        manager.close();
        return ejemplarToSave;
    }

    public static Ejemplar getEjemplarByUUID(UUID ejemplarUUID){

        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Ejemplar ejemplarFound = null;
        ejemplarFound = manager.find(Ejemplar.class, ejemplarUUID);

        transaction.commit();
        manager.close();

        return ejemplarFound;
    }

    public static List<Ejemplar> getAllEjemplars() {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<Ejemplar> resultEjemplarsFound = manager.createQuery("SELECT ejemplar FROM Ejemplar ejemplar").getResultList();

        transaction.commit();
        manager.close();

        return resultEjemplarsFound;

    }

    public static Ejemplar Update(Ejemplar ejemplarToUpdate) {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Ejemplar ejemplarUpdated = manager.merge(ejemplarToUpdate);

        transaction.commit();
        manager.close();

        return ejemplarUpdated;
    }

    public static List<Ejemplar> getEjemplarsAvailable() {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<Ejemplar> resultEjemplarsFound = manager.createQuery("SELECT ejemplar FROM Ejemplar ejemplar WHERE ejemplar.available = : availability")
                .setParameter("availability", true).getResultList();

        transaction.commit();
        manager.close();

        return resultEjemplarsFound;

    }
}
