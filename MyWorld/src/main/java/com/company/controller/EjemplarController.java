package com.company.controller;

import com.company.model.Ejemplar;
import com.company.model.EjemplarList;
import com.company.model.User;

import java.util.HashMap;
import java.util.UUID;

public class EjemplarController {

    static EjemplarList lista = new EjemplarList();

    public static HashMap<String, String> createEjemplar(HashMap<String, String> dataToCreateItem) {
        String title = dataToCreateItem.get("title");
        String author = dataToCreateItem.get("author");

        Ejemplar ejemplarCreated = new Ejemplar(title, author);

        boolean statusOperation = lista.add(ejemplarCreated);

        HashMap<String, String> createItemResponse = new HashMap<>();
        createItemResponse.put("response", "createItemResponse");

        if (statusOperation) createItemResponse.put("status", "created");
        else createItemResponse.put("status", "not created");

        return createItemResponse;
    }

    public static HashMap<String, String> listItems() {
        String itemsList = lista.toString();
        HashMap<String, String> listItemsResponse = new HashMap<>();
        listItemsResponse.put("response", "listUsersResponse");
        if(!itemsList.equals("Items Map:\n")) {
            listItemsResponse.put("status", "List exists");
            listItemsResponse.put("message", itemsList);
        } else {
            listItemsResponse.put("status", "List doesnt's exists");
            listItemsResponse.put("message", "No items");
        }
        return listItemsResponse;
    }

    public static HashMap<String, String> listOnlyAvailableItems() {
        String itemsList = "Items Available:\n";
        if(!lista.onlyAvailable().isEmpty()) {
            for(Ejemplar ejemplar : lista.onlyAvailable().values()) {
                itemsList += ejemplar.toString() + "\n";
            }
        }

        HashMap<String, String> listItemsResponse = new HashMap<>();
        listItemsResponse.put("response", "listUsersResponse");
        if(!itemsList.equals("Items Available:\n")) {
            listItemsResponse.put("status", "List exists");
            listItemsResponse.put("message", itemsList);
        } else {
            listItemsResponse.put("status", "List doesnt's exists");
            listItemsResponse.put("message", "No items");
        }
        return listItemsResponse;
    }

    public static EjemplarList getEjemplares() {
        return lista;
    }

}










