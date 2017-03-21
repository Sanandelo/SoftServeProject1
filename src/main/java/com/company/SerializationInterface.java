package com.company;

import com.company.User.User;
import com.company.User.UsersList;

import java.io.IOException;
import java.util.List;

public interface SerializationInterface {
    void writeUsers(UsersList userUsers, String path) throws IOException;
    void writeUser(User user, String path) throws IOException;
    List<User> readUsers(String path) throws  IOException;
    User readUser(String path) throws IOException;
}
