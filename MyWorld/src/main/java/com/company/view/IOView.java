package com.company.view;

import com.company.controller.UserController;
import com.company.frontcontroller.FrontController;
import com.company.test.UserTest;
import com.company.utils.Utilities;

import java.util.HashMap;
import java.util.Scanner;

public class IOView {

    public static void mainLoopView() {
        //just scanner object to manage io
        Scanner reader = new Scanner(System.in);
        //create fake users to work with them
        //this is a very BAD solution, so it is temporal
        //just for having some users to work with them
        //to-do: create a JSON to import when boot soft
        //or just a DB
        UserController.createFakeUsers();
        while (true) {
            //print mode menu
            Menu.modeMenu();
            String command = Utilities.ask(reader, "Mode?");

            if (command.equals("Quit")) {
                break;
            } else if (command.equals("test")) {
                //We create this feature to test our soft
                UserTest.test();
            } else if (command.equals("release")) {
                //We create this feature to release our soft
                releaseLoopView(reader);
                break;
            } else System.out.println("Unknown command");
        }
    }

    public static void releaseLoopView(Scanner reader) {
        //main loop starting
        while (true) {
            //print main menu
            Menu.mainMenu();
            String command = Utilities.ask(reader, "Option?");

            if (command.equals("Exit")) {
                break;
            } else if (command.equals("Users")) {
                //call secondary users menu-loop
                releaseLoopUsersView(reader);
                break;
            } else if (command.equals("Items")) {
                //call-operation to change pin
                releaseLoopItemsView(reader);
                break;
            } else if (command.equals("Lendings")) {
                //call-operation to make a transfer
                releaseLoopLendingsView(reader);
                break;
            } else System.out.println("Unknown command");
        }
    }

    public static void releaseLoopUsersView(Scanner reader) {
        //users loop starting
        while (true) {
            //print users menu
            Menu.usersMenu();
            String command = Utilities.ask(reader, "Option?");

            if (command.equals("Back")) {
                break;
            } else if (command.equals("Newuser")) {
                //call-operation to create new user
                createUser(reader);
            } else if (command.equals("listUsers")) {
                //call-operation to list users
                listUsers(reader);
            } else System.out.println("Unknown command");
        }
        releaseLoopView(reader);
    }

    public static void releaseLoopItemsView(Scanner reader) {
        //users loop starting
        while (true) {
            //print users menu
            Menu.itemsMenu();
            String command = Utilities.ask(reader, "Option?");

            if (command.equalsIgnoreCase("Back")) {
                break;
            } else if (command.equalsIgnoreCase("Newitem")) {
                createItem(reader);
            } else if (command.equalsIgnoreCase("listItems")){
                listEjemplares(reader);
            } else System.out.println("Unknown command");
        }
        releaseLoopView(reader);
    }

    public static void releaseLoopLendingsView(Scanner reader) {
        //users loop starting
        while (true) {
            //print users menu
            Menu.lendingsMenu();
            String command = Utilities.ask(reader, "Option?");

            if (command.equals("Back")) {
                releaseLoopView(reader);
                break;
            } else if (command.equals("Newlending")) {
                //call-operation to create new lending
                createLending(reader);
            } else if (command.equals("listLendings")) {
                //call-operation to list users
                listLendings(reader);
            } else System.out.println("Unknown command");
        }
        releaseLoopView(reader);
    }



    public static String createUser(Scanner reader) {
        //Let s introduce data to create a User
        String name = Utilities.ask(reader, "Name?");
        String surname = Utilities.ask(reader, "Surname?");
        String birthdate = Utilities.ask(reader, "Birthdate?");
        String address = Utilities.ask(reader, "Address?");
        String email = Utilities.ask(reader, "Email?");
        String phoneNumber = Utilities.ask(reader, "PhoneNumber?");
        //create hashmap to send data to controller
        HashMap<String, String> createUserRequest = new HashMap<>();
        //fill data hashmap object
        createUserRequest.put("operation", "createUser");
        createUserRequest.put("name", name);
        createUserRequest.put("surname", surname);
        createUserRequest.put("birthdate", birthdate);
        createUserRequest.put("address", address);
        createUserRequest.put("email", email);
        createUserRequest.put("phoneNumber", phoneNumber);

        //send data to controller and get the response
        HashMap<String, String> createUserResponse = FrontController.mainLoopController(createUserRequest);
        String createUserStatus = createUserResponse.get("status");
        System.out.println("status user: " + createUserStatus + "\n");

        return createUserStatus;
    }

    private static String createLending(Scanner reader) {
        //Let s introduce data to create a Lending
        listUsers(reader);
        String userId = Utilities.ask(reader, "User Id?");
        // TODO check user exists and is ENABLED to get a book; if not exit or ask again? separate method
        listEjemplares(reader);
        String ejemplarId = Utilities.ask(reader, "Ejemplar Id?");
        // TODO check if ejemplar exists and is ENABLED to be lend; if no exit or ask again? separate method
        HashMap<String, String> createLendingRequest = new HashMap<>();

        //fill createLending request data hashmap object
        createLendingRequest.put("operation", "createLending");
        createLendingRequest.put("userId", userId);
        createLendingRequest.put("ejemplarId", ejemplarId);

        //send data to controller and get the createLending response
        HashMap<String, String> createLendingResponse = FrontController.mainLoopController(createLendingRequest);
        String createLendingStatus = createLendingResponse.get("status");
        System.out.println("status lending: " + createLendingStatus + "\n");

        return createLendingStatus;
    }

    private static String createItem(Scanner reader) {
        //Let s introduce data to create a User
        String title = Utilities.ask(reader, "Title?");
        String author = Utilities.ask(reader, "Author?");
        //create hashmap to send data to controller
        HashMap<String, String> createItemRequest = new HashMap<>();
        //fill data hashmap object
        createItemRequest.put("operation", "createItem");
        createItemRequest.put("title", title);
        createItemRequest.put("author", author);

        //send data to controller and get the response
        HashMap<String, String> createItemResponse = FrontController.mainLoopController(createItemRequest);
        String createUserStatus = createItemResponse.get("status");
        System.out.println("status item: " + createUserStatus + "\n");

        return createUserStatus;

    }

    private static String listUsers(Scanner reader) {
        HashMap<String, String> createUsersRequest = new HashMap<>();
        createUsersRequest.put("operation", "listUsers");

        HashMap<String, String> createListUsersResponse = FrontController.mainLoopController(createUsersRequest);
        String createListUsersStatus = createListUsersResponse.get("status");
        System.out.println("status list users: " + createListUsersStatus + "\n");
        System.out.println("Users: " + createListUsersResponse.get("message") + "\n");

        return createListUsersStatus;
    }


    private static String listLendings(Scanner reader) {
        HashMap<String, String> createLendingRequest = new HashMap<>();
        createLendingRequest.put("operation", "listLendings");

        HashMap<String, String> createListLendingsResponse = FrontController.mainLoopController(createLendingRequest);
        String createListLendingsStatus = createListLendingsResponse.get("status");
        System.out.println("status list lendings: " + createListLendingsStatus + "\n");
        System.out.println("Lendings: " + createListLendingsResponse.get("message") + "\n");

        return createListLendingsStatus;
    }

    private static String listEjemplares(Scanner reader) {
        HashMap<String, String> createItemsRequest = new HashMap<>();
        createItemsRequest.put("operation", "listItems");

        HashMap<String, String> createListEjemplaresResponse = FrontController.mainLoopController(createItemsRequest);
        String createListEjemplaresStatus = createListEjemplaresResponse.get("status");
        System.out.println("status list items: " + createListEjemplaresStatus + "\n");
        System.out.println("Items: " + createListEjemplaresResponse.get("message") + "\n");

        return createListEjemplaresStatus;

    }

}
