package com.company.controller;

import com.company.model.User;
import com.company.service.UserService;
import com.company.utils.Utilities;
import java.util.HashMap;
import java.util.UUID;

public class UserController {


    public static HashMap<String, String> createUser(HashMap<String, String> dataToCreateUser) {

        String name = dataToCreateUser.get("name");
        String surname = dataToCreateUser.get("surname");
        String birthdate = dataToCreateUser.get("birthdate");
        String address = dataToCreateUser.get("address");
        String email = dataToCreateUser.get("email");
        String phoneNumber = dataToCreateUser.get("phoneNumber");


        //Let s introduce data to create User
        User createdUser = new User(name, surname, birthdate, address, email, phoneNumber);

        //Let s add this new User object to the main (and just one) array
        User statusOperation = UserService.create(createdUser);

        HashMap<String, String> createUserResponse = new HashMap<>();
        createUserResponse.put("response", "createUserResponse");

        if (statusOperation != null) createUserResponse.put("status", "created");
        else createUserResponse.put("status", "not created");

        return createUserResponse;
    }

    public static HashMap<String, String> listUsers() {
        String usersList = UserService.getAllUsers().toString();

        HashMap<String, String> listUsersResponse = new HashMap<>();
        listUsersResponse.put("response", "listUsersResponse");
        if (!usersList.equals("Users Map:\n")) {
            listUsersResponse.put("status", "List exists");
            listUsersResponse.put("message", usersList);
        } else {
            listUsersResponse.put("status", "List doesn't exists");
            listUsersResponse.put("message", "No users");
        }
        return listUsersResponse;
    }

    public static HashMap<String, String> listEnabledUsers() {
        String enabledUserList = UserService.listEnabledUsersToString();

        HashMap<String, String> listUsersResponse = new HashMap<>();
        listUsersResponse.put("response", "listEnabledUsersResponse");
        if (!enabledUserList.equals("Enable users:\n")) {
            listUsersResponse.put("status", "List exists");
            listUsersResponse.put("message", enabledUserList);
        } else {
            listUsersResponse.put("status", "List doesn't exists");
            listUsersResponse.put("message", "No users");
        }
        return listUsersResponse;
    }

    public static HashMap<String, String> checkUserEnabledByUUID(HashMap<String, String> dataToCheckUser) {
        boolean isChecked;
        try {
            UUID uuid = UUID.fromString(dataToCheckUser.get("uuid"));
            isChecked = UserService.checkUserEnabledByUUID(uuid);
        } catch (Exception e) {
            isChecked = false;
        }

        HashMap<String, String> response = new HashMap<>();
        response.put("response", "checkUserEnabledByUUID");
        if (isChecked) {
            response.put("status", "Enabled user");
            response.put("message", String.valueOf(true));
        } else {
            response.put("status", "Not enabled user");
            response.put("message", String.valueOf(false));
        }
        return response;
    }

}
