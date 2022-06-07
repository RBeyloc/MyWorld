package com.company.repository;


import com.company.model.Ejemplar;
import com.company.model.User;
import com.company.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserRepository {

    public static User create(User userToSave){
        //create a manager to do all the CRUD operations with student object
        //I can create manager because I created EntityManagerFactoryUtils
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        //manager call Transaction, that is, it is a state to persist
        EntityTransaction transaction = manager.getTransaction();
        //let s start with begin the operations, thanks to transaction object
        transaction.begin();
        //prepares the operation to be done
        manager.persist(userToSave);
        //this operation WRITES the object on the actual table
        transaction.commit();
        manager.close();
        return userToSave;
    }

    public static User getUserByUUID(UUID userUuid){

        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        User userFound = null;
        userFound = manager.find(User.class, userUuid);

        transaction.commit();
        manager.close();

        return userFound;
    }

    public static List<User> getAllUsers() {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<User> resultsUsersFound = manager.createQuery("SELECT user FROM User user").getResultList();

        transaction.commit();
        manager.close();

        return resultsUsersFound;

    }

    public static User update(User userToUpdate) {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        User userUpdated = manager.merge(userToUpdate);

        transaction.commit();
        manager.close();

        return userUpdated;
    }

    public static List<User> listEnabledUsers() {

        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<User> resultsUsersFound = manager.createQuery("SELECT user FROM User user WHERE user.status = 'enabled'")
                .getResultList();

        transaction.commit();
        manager.close();

        return resultsUsersFound;


    }
}


