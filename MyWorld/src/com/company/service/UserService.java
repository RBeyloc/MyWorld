package com.company.service;

import com.company.model.UserMap;

import java.util.UUID;

public class UserService {

    public static boolean checkUserEnabledByUUID(UserMap users, UUID userUuid) {
        return users.isUserEnabled(userUuid);
    }

}
