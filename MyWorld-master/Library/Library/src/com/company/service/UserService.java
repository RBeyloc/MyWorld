package com.company.service;

import com.company.model.Ejemplar;
import com.company.model.User;
import com.company.model.UserMap;
import com.company.repository.EjemplarRepository;
import com.company.repository.UserRepository;
import com.company.utils.EntityManagerFactoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserService {

    public static boolean checkUserEnabledByUUID(UUID userUuid) {
        User gotUser = UserRepository.getUserByUUID(userUuid);
        return gotUser.getStatus().equals("enabled");
    }

    public static String listEnabledUsersToString() {
        String enabledUserList = "Enabled users:\n";
        List<User> result = UserRepository.listEnabledUsers();
        if (!result.isEmpty()) {
            for (User user : result) {
                enabledUserList += user.toString() + "\n";
            }
        }
        return enabledUserList;
    }

    public static String listAllUsersToString() {
        String userList = "All users:\n";
        List<User> result = UserRepository.getAllUsers();
        if (!result.isEmpty()) {
            for (User user : result) {
                userList += user.toString() + "\n";
            }
        }
        return userList;
    }

    public static User getUserByUuid(UUID userUUID) {
        User user = UserRepository.getUserByUUID(userUUID);
        return user;
    }

    public static User create(User userToSave) {
        return UserRepository.create(userToSave);
    }

    public static List<User> getAllUsers() {
        return UserRepository.getAllUsers();
    }

    public static User update(User userToUpdate) {
        return UserService.update(userToUpdate);
    }

}
