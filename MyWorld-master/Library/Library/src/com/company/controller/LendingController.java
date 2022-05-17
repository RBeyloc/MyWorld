package com.company.controller;

import com.company.model.*;
import com.company.service.EjemplarService;
import com.company.service.UserService;

import java.util.HashMap;
import java.util.UUID;

public class LendingController {

   /* public static HashMap<String, String> createLending(HashMap<String, String> request) {
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
            User user = UserService.getUserByUUID(userUUID);
            Ejemplar ejemplar = EjemplarService.getEjemplarByUUID(ejemplarUUID);

            // Creating the new lending object and put into lendings "map" object
            if ((user == null) || (ejemplar == null)) {
                createLendingResponse.put("message", "Failure in retrieving user or ejemplar.");
            } else {
                Lending newLending = new Lending(userUUID, ejemplarUUID);
                if (!LendingService.createLending(newLending)) {
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

    public static HashMap<String, String> makeDevolution(HashMap<String, String> request){
        String ejemplarUUID = request.get("ejemplarUUID");
        Lending lending = null;
        Ejemplar ejemplar = null;
        boolean statusOperation = false;
        ejemplar = ejemplares.findBySku(UUID.fromString(ejemplarUUID));

        if(ejemplar != null) {
            LendingList lendings = new LendingList();
            lending = getLastLendingByEjemplar(ejemplar);
        }
        if(lending != null) {
            statusOperation = lending.devolution();
        }

        HashMap<String, String> devolutionResponse = new HashMap<>();
        devolutionResponse.put("status", "failed");
        devolutionResponse.put("response", "Devolution failed");

        if(ejemplar == null) {
            devolutionResponse.put("response", "Can not retrieve the book reference");
        } else if (lending == null) {
            devolutionResponse.put("response", "Can not retrieve the last lending reference");
        } else if (statusOperation) {
            devolutionResponse.put("status", "success");
            devolutionResponse.put("status", "Devolution succeded");
        }
        return devolutionResponse;
    }*/
}
