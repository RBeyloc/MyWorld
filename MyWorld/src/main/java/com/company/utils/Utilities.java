package com.company.utils;

import java.sql.*;
import java.util.Scanner;
import com.company.model.User;
import com.company.model.UserMap;

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

    public static ResultSet DBPersistSQLRequest (String sql) throws SQLException {
        Connection connection = DBEstablishConnection();
        ResultSet resultset = DBRunStatement(connection, sql);
        DBCloseConnection(connection);
        return resultset;
    }

    public static Connection DBEstablishConnection () throws SQLException {
        String stringURLConnection = "jdbc:h2:tcp://localhost/../../../Users/Alumne_mati1/h2/library";
        String username = "ricard";
        String password = "RBR3410";
        Connection connection = DriverManager.getConnection(stringURLConnection, username, password);
        return connection;
    }

    public static ResultSet DBRunStatement(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        connection.close();
        return resultSet;
    }

    public static void DBCloseConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
