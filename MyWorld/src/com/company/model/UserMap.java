package com.company.model;

import java.util.HashMap;
import java.util.UUID;

public class UserMap {

    private HashMap<String, User> UserList;

    public UserMap(HashMap<String, User> userList) {
        UserList = userList;
    }

    public UserMap() {
        UserList = new HashMap<String, User>();
    }

    public boolean addUser(User user) {
        try {
            this.UserList.put(String.valueOf(user.getIdNumber()), user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getUserById(String userId) {
        return this.UserList.get(userId);
    }

    public HashMap<String, User> listEnabledUsers() {
        HashMap<String, User> activeUsersMap = null;
        for (User user : this.UserList.values()) {
            if (user.getStatus().equals("enabled")) {
                activeUsersMap.put(String.valueOf(user.getIdNumber()), user);
            }
        }
        return activeUsersMap;
    }

    public HashMap<String, User> getUserList() {
        return UserList;
    }

    public void setUserList(HashMap<String, User> userList) {
        UserList = userList;
    }

    public boolean existsUserUUID(UUID userUUID) {
        return this.UserList.get(userUUID.toString()) != null;
    }

    public boolean isUserEnabled(UUID userUUID) {
        User user = this.UserList.get(userUUID.toString());

        return user != null && user.getStatus() == "enabled";
    }

    @Override
    public String toString() {
        String usersList = "Users Map:\n";
        if (!this.UserList.isEmpty()) {
            for (User user : this.UserList.values()) {
                usersList += user.toString() + "\n";
            }
        }
        return usersList;
    }
}