package com.company.Utilists;

import com.company.User.User;
import com.company.User.UsersList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

public class AdapterToJson implements SerializationInterface {
    Gson gson = new Gson();

    public void writeUsers(UsersList usersList, String path) throws IOException {
        FileWriter file = new FileWriter(path);
        gson.toJson(usersList.getUsers(), file);
        file.close();

    }
    public void writeUser(User user, String path) throws IOException {
        FileWriter file = new FileWriter(path);
        gson.toJson(user,file);
        file.close();
    }


    public List<User> readUsers(String path) throws FileNotFoundException {
        FileReader file = new FileReader(path);
        return gson.fromJson(file,new TypeToken<List<User>>(){}.getType());
    }

    public User readUser(String path) throws IOException{
        FileReader file = new FileReader(path);
        return gson.fromJson(file,User.class);
    }
}
