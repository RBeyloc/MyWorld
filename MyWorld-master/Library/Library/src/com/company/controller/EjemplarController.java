package com.company.controller;

import com.company.model.Ejemplar;
import com.company.model.EjemplarList;
import com.company.model.User;
import com.company.repository.EjemplarRepository;
import com.company.service.EjemplarService;
import com.company.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EjemplarController {

    static EjemplarList lista = new EjemplarList();

    public static HashMap<String, String> createEjemplar(HashMap<String, String> dataToCreateItem) {
        String title = dataToCreateItem.get("title");
        String author = dataToCreateItem.get("author");

        Ejemplar ejemplarCreated = new Ejemplar(title, author);

        boolean statusOperation = lista.add(ejemplarCreated);
        EjemplarService.create(ejemplarCreated);

        HashMap<String, String> createItemResponse = new HashMap<>();
        createItemResponse.put("response", "createItemResponse");

        if (statusOperation) createItemResponse.put("status", "created");
        else createItemResponse.put("status", "not created");

        return createItemResponse;
    }

    public static HashMap<String, String> listItems() {

        List<Ejemplar> allEjemplares = EjemplarRepository.getAllEjemplars();

        String ejemplaresList = "";

        for (Ejemplar ejemplar : allEjemplares) {
            ejemplaresList += ejemplar.toString() + "\n";
        }


        HashMap<String, String> listItemsResponse = new HashMap<>();
        listItemsResponse.put("response", "listUsersResponse");
        if(!ejemplaresList.equals("Items Map:\n")) {
            listItemsResponse.put("status", "List exists");
            listItemsResponse.put("message", ejemplaresList);
        } else {
            listItemsResponse.put("status", "List doesnt's exists");
            listItemsResponse.put("message", "No items");
        }
        return listItemsResponse;
    }

    public static HashMap<String, String> listAvailableEjemplares() {

        List<Ejemplar> allEjemplares = EjemplarRepository.getAvailableEjemplars();

        String ejemplaresList = "";

        for (Ejemplar ejemplar : allEjemplares) {
            ejemplaresList += ejemplar.toString() + "\n";
        }


        HashMap<String, String> listItemsResponse = new HashMap<>();
        listItemsResponse.put("response", "listUsersResponse");
        if(!ejemplaresList.equals("Items Available:\n")) {
            listItemsResponse.put("status", "List exists");
            listItemsResponse.put("message", ejemplaresList);
        } else {
            listItemsResponse.put("status", "List doesnt's exists");
            listItemsResponse.put("message", "No items");
        }
        return listItemsResponse;
    }

    public static HashMap<String, String> checkEjemplarAvailableByUUID(HashMap<String, String> dataToCheckEjemplar){
        boolean isChecked;
        try {
            UUID uuid = UUID.fromString(dataToCheckEjemplar.get("uuid"));
            isChecked = EjemplarService.checkEjemplarAvailableByUUID(uuid);
        }
        catch(Exception e) {
            isChecked = false;
        }
        HashMap<String, String> response = new HashMap<>();
        response.put("response", "checkEjemplarEnabledByUUID");
        if (isChecked) {
            response.put("status", "Book available");
            response.put("message", String.valueOf(true));
        } else {
            response.put("status", "Book not available");
            response.put("message", String.valueOf(false));
        }
        return response;

    }

}










