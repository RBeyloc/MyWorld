package com.company.repository;

import com.company.model.Ejemplar;
import com.company.model.User;
import com.company.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class EjemplarRepository {

    public static void create(Ejemplar ejemplarToCreate){

        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();

        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();

        manager.persist(ejemplarToCreate);

        transaction.commit();

        manager.close();

    }

    public static Ejemplar getEjemplarByUUID(UUID ejemplarUUID){

        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<Ejemplar> resultsEjemplarFound = manager.createQuery("SELECT ejemplar FROM Ejemplar ejemplar WHERE ejemplar.SKU LIKE :email")
                .setParameter("email", ejemplarUUID).getResultList();

        transaction.commit();
        manager.close();

        Ejemplar EjemplarFound = null;
        if ( resultsEjemplarFound.size() != 0 ) EjemplarFound = resultsEjemplarFound.get(0);

        return EjemplarFound;
    }

    public static List<Ejemplar> getAllEjemplars() {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<Ejemplar> resultsEjemplarFound = manager.createQuery("SELECT ejemplar FROM Ejemplar ejemplar")
                .getResultList();

        transaction.commit();
        manager.close();

        return resultsEjemplarFound;

    }

    public static List<Ejemplar> getAvailableEjemplars() {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        boolean available = true;

        List<Ejemplar> resultsEjemplarFound = manager.createQuery("SELECT ejemplar FROM Ejemplar ejemplar WHERE available LIKE 1")
                .getResultList();

        transaction.commit();
        manager.close();

        return resultsEjemplarFound;

    }
}
