package com.company.controller;

import com.company.model.User;
import com.company.model.UserMap;
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

    public static void createFakeUsers() {
        //just to work with them, no having a void arraylist
        User newUser1 = new User("Alex", "Pixel", "1987-07-30", "Muntaner 555, 08032, Barcelona", "alex.pixel@gmail.com", "619111435");
        User newUser2 = new User("Thomas", "Edison", "1982-07-30", "Carrer del Comte de Sert, 25, 08035, Barcelona", "Thomas@gmail.com", "653111345");
        User newUser3 = new User("Susan", "Lane", "1977-07-30", "Paseo de Gracia, 43, 08007 Barcelona", "lane@msn.com", "932 160 306");
        User newUser4 = new User("Marta", "Gross", "1980-07-30", "Paseo de Gracia, 92, 08008 Barcelona", "Martha.L@hotmail.com", "932 14 25 76");
        UserService.create(newUser1);
        UserService.create(newUser2);
        UserService.create(newUser3);
        UserService.create(newUser4);
    }

}
