package com.company.frontcontroller;

import com.company.controller.EjemplarController;
import com.company.controller.LendingController;
import com.company.controller.UserController;

import java.util.HashMap;

public class FrontController {

    public static HashMap<String, String> mainLoopController(HashMap<String, String> request) {
        //
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "error");
        //
        if (request.get("operation").equalsIgnoreCase("createUser")) response = UserController.createUser(request);
       // else if (request.get("operation").equals( "createLending")) response = LendingController.createLending(request);
        else if (request.get("operation").equalsIgnoreCase( "createItem")) response = EjemplarController.createEjemplar(request);
       // else if (request.get("operation").equals( "listLendings")) response = LendingController.listLendings();
        else if (request.get("operation").equalsIgnoreCase( "listUsers")) response = UserController.listUsers();
        else if (request.get("operation").equalsIgnoreCase( "listEnabledUsers")) response = UserController.listEnabledUsers();
        else if (request.get("operation").equalsIgnoreCase( "listItems")) response = EjemplarController.listItems();
        else if (request.get("operation").equalsIgnoreCase( "listAvailableItems")) response = EjemplarController.listAvailableEjemplares();
        else if (request.get("operation").equalsIgnoreCase( "checkUserEnabledByUUID")) response = UserController.checkUserEnabledByUUID(request);


        System.out.println(response);

        return response;
    }
}
