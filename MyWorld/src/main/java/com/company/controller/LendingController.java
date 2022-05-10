package com.company.controller;

import com.company.model.*;

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

        // Getting user object by id and ejemplar object by id
        User user = users.getUserById(userId);
        Ejemplar ejemplar = ejemplares.findBySku(UUID.fromString(ejemplarId));

        // Creating the new lending object and put into lendings "map" object
        boolean statusOperation = false;
        if ((user != null) && (ejemplar != null)) {
            Lending newLending = new Lending(user, ejemplar);
            // Todo change Ejemplar status to available false
            statusOperation = lendings.addLending(newLending);
        }
        createLendingResponse.put("status", "fail");

        // Add response HashMap data
        if (statusOperation) {
            createLendingResponse.put("status", "success");
            createLendingResponse.put("message", "Lending created successfully.");
        } else if (user == null) {
            createLendingResponse.put("message", "User don't exists.");
        } else if (ejemplar == null) {
            createLendingResponse.put("message", "Reference item don't exists.");
        } else {
            createLendingResponse.put("message", "Failure in creating lending.");
        }

        return createLendingResponse;

    }

    public static HashMap<String, String> listLendings() {
        String lendingsList = lendings.toString();
        HashMap<String, String> listLendingsResponse = new HashMap<>();
        listLendingsResponse.put("response", "listLendingsResponse");
        if(!lendingsList.equals("Lendings Map:\n")) {
            listLendingsResponse.put("status", "List exists");
            listLendingsResponse.put("message", lendingsList);
        } else {
            listLendingsResponse.put("status", "List doesnt's exists");
            listLendingsResponse.put("message", "No users");
        }
        return listLendingsResponse;
    }
}
