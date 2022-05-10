package com.company.utils;

import com.company.model.User;
import com.company.model.UserMap;

import java.util.Scanner;

public class Utilities {

    public static String ask(Scanner reader, String string) {
        System.out.println(string);
        return reader.nextLine();
    }

    public static void clearConsole() {
        System.out.println(System.lineSeparator().repeat(100));
    }

    public static String listEnabledUsersToString(UserMap users) {
        String enabledUserList = "Enable users:\n";
        if (!users.listEnabledUsers().isEmpty()) {
            for (User user : users.listEnabledUsers().values()) {
                enabledUserList += user.toString() + "\n";
            }
        }
        return enabledUserList;
    }

}
