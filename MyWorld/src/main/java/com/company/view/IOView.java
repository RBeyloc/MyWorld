package com.company.view;

import com.company.controller.UserController;
import com.company.frontcontroller.FrontController;
import com.company.test.EjemplarTest;
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
                EjemplarTest.test();
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
            } else if (command.equals("listEnabledUsers")) {
                //call-operation to list enabled users to borrow books
                listEnabledUsers(reader);
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
            } else if (command.equalsIgnoreCase("listAvailableEjemplares")) {
                listAvailableEjemplares(reader);
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

    public static String createItem(Scanner reader) {
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

    public static String createLending(Scanner reader) {
        String userId = "";
        while (true) {
            System.out.println("Choose and write one of users Id (Quit to scape):\n");
            listEnabledUsers(reader);
            String command = Utilities.ask(reader, "User Id?");
            if (command.equals("Quit")) {
                userId = "Quit";
                break;
            } else if(checkUserEnabledByUUID(reader, command)) {
                userId = command;
                break;
            }
        }

        String ejemplarId = "";
        while (true) {
            System.out.println("Choose and write one of books Id (Quit to scape):\n");
            listAvailableEjemplares(reader);
            String command = Utilities.ask(reader, "Ejemplar Id?");
            if (command.equals("Quit")) {
                ejemplarId = "Quit";
                break;
            } else if(checkEjemplarAvailableByUUID(reader, command)) {
                ejemplarId = command;
                break;
            }
        }

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

    public static boolean checkUserEnabledByUUID(Scanner reader, String userId) {
        HashMap<String, String> checkUserEnabledByUUIDRequest = new HashMap<>();
        checkUserEnabledByUUIDRequest.put("operation", "checkUserEnabledByUUID");
        checkUserEnabledByUUIDRequest.put("uuid", userId);
        HashMap<String, String> checkUserEnabledByUUIDResponse = FrontController.mainLoopController(checkUserEnabledByUUIDRequest);
        String checkUserEnabledByUUIDStatus = checkUserEnabledByUUIDResponse.get("status");
        boolean checked = checkUserEnabledByUUIDStatus.equals("Enabled user");
        return checked;
    }

    public static boolean checkEjemplarAvailableByUUID(Scanner reader,String ejemplarId) {
        HashMap<String, String> checkEjemplarAvailableByUUIDRequest = new HashMap<>();
        checkEjemplarAvailableByUUIDRequest.put("operation", "checkEjemplarAvailableByUUID");
        checkEjemplarAvailableByUUIDRequest.put("uuid", ejemplarId);
        HashMap<String, String> checkEjemplarAvailableByUUIDResponse = FrontController.mainLoopController(checkEjemplarAvailableByUUIDRequest);
        String checkEjemplarAvailableByUUIDStatus = checkEjemplarAvailableByUUIDResponse.get("status");
        boolean checked = checkEjemplarAvailableByUUIDStatus.equals("Book available");
        return checked;
    }


    public static String listUsers(Scanner reader) {
        HashMap<String, String> listUsersRequest = new HashMap<>();
        listUsersRequest.put("operation", "listUsers");

        HashMap<String, String> listUsersResponse = FrontController.mainLoopController(listUsersRequest);
        String listUsersStatus = listUsersResponse.get("status");
        System.out.println("status list users: " + listUsersStatus + "\n");
        System.out.println("Users: " + listUsersResponse.get("message") + "\n");

        return listUsersStatus;
    }

    public static String listEnabledUsers(Scanner reader) {
        HashMap<String, String> listEnabledUsersRequest = new HashMap<>();
        listEnabledUsersRequest.put("operation", "listEnabledUsers");

        HashMap<String, String> listEnabledUsersResponse = FrontController.mainLoopController(listEnabledUsersRequest);
        String listEnabledUsersStatus = listEnabledUsersResponse.get("status");
        System.out.println("status list enabled users: " + listEnabledUsersStatus + "\n");
        System.out.println("Enabled users: " + listEnabledUsersResponse.get("message") + "\n");

        return listEnabledUsersStatus;
    }


    public static String listLendings(Scanner reader) {
        HashMap<String, String> listLendingRequest = new HashMap<>();
        listLendingRequest.put("operation", "listLendings");

        HashMap<String, String> listLendingsResponse = FrontController.mainLoopController(listLendingRequest);
        String listLendingsStatus = listLendingsResponse.get("status");
        System.out.println("status list lendings: " + listLendingsStatus + "\n");
        System.out.println("Lendings: " + listLendingsResponse.get("message") + "\n");

        return listLendingsStatus;
    }

    public static String listAvailableEjemplares(Scanner reader) {
        HashMap<String, String> listAvailableEjemplaresRequest = new HashMap<>();
        listAvailableEjemplaresRequest.put("operation", "listAvailableEjemplares");

        HashMap<String, String> listAvailableEjemplaresResponse = FrontController.mainLoopController(listAvailableEjemplaresRequest);
        String listAvailableEjemplaresStatus = listAvailableEjemplaresResponse.get("status");
        System.out.println("status list available ejemplares: " + listAvailableEjemplaresStatus + "\n");
        System.out.println("Available ejemplares: " + listAvailableEjemplaresResponse.get("message") + "\n");

        return listAvailableEjemplaresStatus;
    }

    public static String listEjemplares(Scanner reader) {
        HashMap<String, String> listItemsRequest = new HashMap<>();
        listItemsRequest.put("operation", "listItems");

        HashMap<String, String> listEjemplaresResponse = FrontController.mainLoopController(listItemsRequest);
        String listEjemplaresStatus = listEjemplaresResponse.get("status");
        System.out.println("status list ejemplares: " + listEjemplaresStatus + "\n");
        System.out.println("ejemplares: " + listEjemplaresResponse.get("message") + "\n");

        return listEjemplaresStatus;

    }

}
