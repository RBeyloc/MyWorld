package com.company.controller;

import com.company.model.*;
import com.company.service.EjemplarService;
import com.company.service.LendingService;
import com.company.service.UserService;

import java.util.HashMap;
import java.util.UUID;

public class LendingController {

    public static HashMap<String, String> createLending(HashMap<String, String> request) {
        // Unpacking data
        UUID userUUID = UUID.fromString(request.get("userId"));
        UUID ejemplarUUID = UUID.fromString(request.get("ejemplarId"));

        // Building response HashMap
        HashMap<String, String> createLendingResponse = new HashMap<>();
        createLendingResponse.put("response", "createLendingResponse");
        createLendingResponse.put("status", "fail");

        // Getting user object by id and ejemplar object by id
        if (!UserService.checkUserEnabledByUUID(userUUID)) {
            createLendingResponse.put("message", "User don't exists or is not enabled to borrow.");
        } else if (!EjemplarService.checkEjemplarAvailableByUUID(ejemplarUUID)) {
            createLendingResponse.put("message", "Ejemplar don't exists or is not available to be lent.");
        } else {
            // Get object user and ejemplar
            User user = UserService.getUserByUuid(userUUID);
            Ejemplar ejemplar = EjemplarService.getEjemplarByUuid(ejemplarUUID);

            // Creating the new lending object and put into lendings "map" object
            if ((user == null) || (ejemplar == null)) {
                createLendingResponse.put("message", "Failure in retrieving user or ejemplar.");
            } else {
                Lending newLending = new Lending(userUUID, user, ejemplarUUID, ejemplar);
                if (LendingService.createLending(newLending).getUserID() == null) {
                    createLendingResponse.put("message", "Failure in saving new lending.");
                } else {
                    user.setStatus("disabled");
                    UserService.update(user);
                    ejemplar.setAvailable(false);
                    EjemplarService.update(ejemplar);
                    createLendingResponse.put("status", "success");
                    createLendingResponse.put("message", "Lending created successfully.");
                }
            }
        }
        return createLendingResponse;
    }

    public static HashMap<String, String> listLendings() {
        String lendingListStringyfied = LendingService.listAllLendingsToString();
        HashMap<String, String> listLendingsResponse = new HashMap<>();
        listLendingsResponse.put("response", "listLendingsResponse");
        if (!lendingListStringyfied.equals("Lendings Map:\n")) {
            listLendingsResponse.put("status", "List exists");
            listLendingsResponse.put("message", lendingListStringyfied);
        } else {
            listLendingsResponse.put("status", "List doesnt's exists");
            listLendingsResponse.put("message", "No users");
        }
        return listLendingsResponse;
    }

    public static HashMap<String, String> makeDevolution(HashMap<String, String> request){
        String ejemplarUUID = request.get("uuid");
        Lending lending = null;
        Ejemplar ejemplar = null;
        boolean statusOperation = false;
        ejemplar = EjemplarService.getEjemplarByUuid(UUID.fromString(ejemplarUUID));

        if(ejemplar != null) {
            lending = LendingService.getLastLendingByEjemplarUUID(ejemplarUUID);
        }
        if(lending != null) {
            statusOperation = lending.devolution();
            LendingService.update(lending);
            UUID userId = lending.getUserID();
            User user = UserService.getUserByUuid(userId);
            user.setStatus("enabled");
            UserService.update(user);
            ejemplar.setAvailable(true);
            EjemplarService.update(ejemplar);
        }

        HashMap<String, String> makeDevolutionResponse = new HashMap<>();
        makeDevolutionResponse.put("status", "failed");
        makeDevolutionResponse.put("response", "Devolution failed");

        if(ejemplar == null) {
            makeDevolutionResponse.put("response", "Can not retrieve the book reference");
        } else if (lending == null) {
            makeDevolutionResponse.put("response", "Can not retrieve the last lending reference");
        } else if (statusOperation) {
            makeDevolutionResponse.put("status", "success");
            makeDevolutionResponse.put("response", "Returned");
        }
        return makeDevolutionResponse;
    }
}
