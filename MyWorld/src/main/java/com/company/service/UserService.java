package com.company.service;

import com.company.model.User;
import com.company.model.UserMap;

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

}
