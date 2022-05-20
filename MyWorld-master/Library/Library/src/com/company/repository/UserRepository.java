package com.company.repository;


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

    public static User getUserByUUID(String userEmail){

        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<User> resultsUserFound = manager.createQuery("SELECT user FROM User user WHERE user.email LIKE :email")
                .setParameter("email", userEmail).getResultList();

        transaction.commit();
        manager.close();

        User userFound = null;
        if ( resultsUserFound.size() != 0 ) userFound = resultsUserFound.get(0);

        return userFound;
    }

    public static List<User> getAllUsers() {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<User> resultsUsersFound = manager.createQuery("SELECT user FROM User user")
                .getResultList();

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

    public static boolean isUserEnabled(UUID userUuid) {
        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<User> resultsUsersFound = manager.createQuery("SELECT user FROM User user WHERE user.status = 'enabled' AND user.userUuid = '" + userUuid + "'")
                .getResultList();

        transaction.commit();
        manager.close();

        return !resultsUsersFound.isEmpty();
    }

    public static HashMap<String, User> listEnabledUsers() {

        EntityManager manager = EntityManagerFactoryUtils.getEntityManger();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<User> resultsUsersFound = manager.createQuery("SELECT user FROM User user WHERE user.status = 'enabled'")
                .getResultList();

        HashMap <String, User> results = new HashMap<>();
        resultsUsersFound.forEach(u->{results.put(String.valueOf(u.getIdNumber()), u);});

        transaction.commit();
        manager.close();

        return results;


    }
}


