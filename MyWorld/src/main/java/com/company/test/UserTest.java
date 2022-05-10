package com.company.test;

import com.company.controller.UserController;
import com.company.model.User;
import com.company.service.UserService;
import com.company.view.IOView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserTest {

    public static void test() {
        //array list users to test
        ArrayList<User> usersTest = new ArrayList<>();
        createFakeUsers(usersTest);
        printUsers(usersTest);
        //unitary tests
        testCreateUserView();
        testCreateUserController();
        //testLoan(users);
        System.out.println("Tests ending... \n");
    }

    public static void createFakeUsers(ArrayList<User> usersTest) {

        User newUser1 = new User("Giselle", "Morales", "1987-07-30", "Muntaner 555, 08032, Barcelona", "gmorales@gmail.com","619111435");
        User newUser2 = new User("Thomas", "Edison", "1982-07-30", "Carrer del Comte de Sert, 25, 08035, Barcelona", "Thomas@gmail.com", "653111345");
        User newUser3 = new User("Susan", "Lane", "1977-07-30", "Paseo de Gracia, 43, 08007 Barcelona", "lane@msn.com", "932 160 306");
        User newUser4 = new User("Marta", "Gross", "1980-07-30", "Paseo de Gracia, 92, 08008 Barcelona", "Martha.L@hotmail.com", "932 14 25 76");
        usersTest.add(newUser1);
        usersTest.add(newUser2);
        usersTest.add(newUser3);
        usersTest.add(newUser4);

        if (usersTest.size() == 4) System.out.println("Test #createFakeUsers OK");
        else System.out.println("Test #createFakeUsers FAIL");
    }

    public static void printUsers(ArrayList<User> users) {
        System.out.println("Users:" + users + "\n");
    }

    public static void testCreateUserView() {
        //test view so we need to send data as string
        String fakeDataUser = "Bruce\n" + "Jones\n" + "1980-03-28\n" + "Llobregos 230, 08032, Barcelona\n" + "bjones@hotmail.com\n" + "993456728\n";
        Scanner fakeReader = new Scanner(fakeDataUser);

        String status = IOView.createUser(fakeReader);
        if (status.equals("created")) System.out.println("Test #testCreateUserView OK");
        else System.out.println("Test #testCreateUserView FAIL");
    }

    public static void testCreateUserController() {
        //to test and discover weather
        // (1) user is created
        // (2) user is saved in arraylist properly
        // (3) get hashmap and unpack it
        // (4) create response hashmap and works
        HashMap<String, String> fakeDataUser = new HashMap();
        fakeDataUser.put("operation", "createUser");
        fakeDataUser.put("name", "Sonia");
        fakeDataUser.put("surname", "Lopes");
        fakeDataUser.put("birthdate", "1990-12-30");
        fakeDataUser.put("address", "Carrer de Còrsega 589, 08025 Barcelona");
        fakeDataUser.put("email", "slopes@upc.es");
        fakeDataUser.put("phoneNumber", "934551728");

        HashMap<String, String> responseHashMap = UserController.createUser(fakeDataUser);

        if (responseHashMap.get("status").equals("created")) System.out.println("Test #testCreateUserController OK");
        else System.out.println("Test #testCreateUserController FAIL");
    }


}
