package com.company.controller;

import com.company.model.*;
import com.company.service.UserService;

import java.util.HashMap;
import java.util.UUID;

public class LendingController {

    static UserMap users = UserController.getUsers();
    static EjemplarList ejemplares = EjemplarController.getEjemplares();
    static LendingMap lendings = new LendingMap();

    public static HashMap<String, String> createLending(HashMap<String, String> request) {
        // Unpacking data
        String userId = request.get("userId");
        String ejemplarId = request.get("ejemplarId");

        // Building response HashMap
        HashMap<String, String> createLendingResponse = new HashMap<>();
        createLendingResponse.put("response", "createLendingResponse");
        createLendingResponse.put("status", "fail");

        // Getting user object by id and ejemplar object by id
        if (!UserService.checkUserEnabledByUUID(users, UUID.fromString(userId))) {
            createLendingResponse.put("message", "User don't exists or is not enabled to borrow.");
       /* } else if (!ejemplares.checkEjemplarAvailableByUID(UUID.fromString(ejemplarId))) {
            createLendingResponse.put("message", "Ejemplar don't exists or is not available to be lent.");*/
        } else {
            // Get object user and ejemplar
            User user = users.getUserById(userId);
            Ejemplar ejemplar = ejemplares.findBySku(UUID.fromString(ejemplarId));

            // Creating the new lending object and put into lendings "map" object
            if ((user == null) || (ejemplar == null)) {
                createLendingResponse.put("message", "Failure in retrieving user or ejemplar.");
            } else {
                Lending newLending = new Lending(user, ejemplar);
                if (!lendings.addLending(newLending)) {
                    createLendingResponse.put("message", "Failure in saving new lending.");
                } else {
                    user.setStatus("disabled");
                    ejemplar.setAvailable(false);
                    createLendingResponse.put("status", "success");
                    createLendingResponse.put("message", "Lending created successfully.");
                }
            }
        }
        return createLendingResponse;
    }

    public static HashMap<String, String> listLendings() {
        String lendingsList = lendings.toString();
        HashMap<String, String> listLendingsResponse = new HashMap<>();
        listLendingsResponse.put("response", "listLendingsResponse");
        if (!lendingsList.equals("Lendings Map:\n")) {
            listLendingsResponse.put("status", "List exists");
            listLendingsResponse.put("message", lendingsList);
        } else {
            listLendingsResponse.put("status", "List doesnt's exists");
            listLendingsResponse.put("message", "No users");
        }
        return listLendingsResponse;
    }
}
