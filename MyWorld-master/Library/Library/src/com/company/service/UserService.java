package com.company.service;

import com.company.model.User;
import com.company.model.UserMap;
import com.company.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserService {

    public static boolean checkUserEnabledByUUID(UserMap users, UUID userUuid) {
        return UserRepository.isUserEnabled(userUuid);
    }

    public static String listEnabledUsersToString(UserMap users) {
        String enabledUserList = "Enable users:\n";
        HashMap<String, User> result = UserRepository.listEnabledUsers();
        if (!result.isEmpty()) {
            for (User user : result.values()) {
                enabledUserList += user.toString() + "\n";
            }
        }
        return enabledUserList;
    }

    public static User create(User userToSave) {
        return UserRepository.create(userToSave);
    }

    public static List<User> getAllUsers() {
        return UserRepository.getAllUsers();
    }

}
