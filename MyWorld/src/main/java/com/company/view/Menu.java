package com.company.view;

import com.company.utils.Utilities;

public class Menu {

    public static void mainMenu() {
        //print mini-menu with 4 options + quit
//
        System.out.println("1-Users");
        System.out.println("2-Items");
        System.out.println("3-Lendings");
        System.out.println("0-Exit");
    }

    public static void usersMenu() {
        //print mini-menu with 1 option + quit
        //Utilities.clearConsole();
        System.out.println("1-Newuser");
        System.out.println("2-listUsers");
        System.out.println("0-Back");
    }

    public static void itemsMenu() {
        //print mini-menu with 1 option + quit
        //Utilities.clearConsole();
        System.out.println("1-Newitem");
        System.out.println("2-listItems");
        System.out.println("0-Back");
    }

    public static void lendingsMenu() {
        //print mini-menu with 1 option + quit
        //Utilities.clearConsole();
        System.out.println("1-Newlending");
        System.out.println("2-listLendings");
        System.out.println("0-Back");
    }

    public static void modeMenu() {
        //print mini-menu with 4 options + quit
        //Utilities.clearConsole();
        System.out.println("1-test");
        System.out.println("2-release");
        System.out.println("0-Quit");
    }
}
