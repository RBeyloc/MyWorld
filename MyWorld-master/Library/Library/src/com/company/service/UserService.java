package com.company.service;

import com.company.model.User;
import com.company.model.UserMap;
import com.company.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserService {

    public static boolean checkUserEnabledByUUID(UserMap users, UUID userUuid) {
        return users.isUserEnabled(userUuid);
    }

    public static String listEnabledUsersToString(UserMap users) {
        String enabledUserList = "Enable users:\n";
        if (!users.listEnabledUsers().isEmpty()) {
            for (User user : users.listEnabledUsers().values()) {
                enabledUserList += user.toString() + "\n";
            }
        }
        return enabledUserList;
    }

    public static void create(User userToSave) {
        UserRepository.create(userToSave);
    }

    public static List<User> getAllUsers() {
        return UserRepository.getAllUsers();
    }

}
