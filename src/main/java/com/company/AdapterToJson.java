package com.company;

import com.company.User.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

public class AdapterToJson {
    Gson gson = new Gson();

    public void usersToJson(List<User> list, String address) throws IOException {
        FileWriter file = new FileWriter(address);
        gson.toJson(list, file);
        file.close();

    }
    public List<User> jsonToUsers(String address) throws FileNotFoundException {
        FileReader file = new FileReader(address);
        return gson.fromJson(file,new TypeToken<List<User>>(){}.getType());

    }
}
