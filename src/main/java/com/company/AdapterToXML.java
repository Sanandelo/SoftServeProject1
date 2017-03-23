package com.company;

import com.company.User.User;
import com.company.User.UsersList;
import com.thoughtworks.xstream.XStream;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AdapterToXML implements SerializationInterface {
    XStream xstream = new XStream();

    public void writeUsers(UsersList userList, String path) throws IOException  {
        FileWriter fileWriter = new FileWriter(path);
       xstream.alias("user", User.class);
       xstream.alias("users", UsersList.class);
       xstream.addImplicitCollection(UsersList.class, "users");

       xstream.useAttributeFor(User.class, "Id");

       String xml = xstream.toXML(userList.getUsers());
       fileWriter.write(xml);
       fileWriter.close();
    }

    @Override
    public void writeUser(User user, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        String xml = xstream.toXML(user);
        fileWriter.write(xml);
        fileWriter.close();
    }

    public List<User> readUsers(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = bufferedReader.readLine())!=null){
            sb.append(line.trim());
        }
        bufferedReader.close();
        String xml = sb.toString();

        return (ArrayList)xstream.fromXML(xml);

    }

    @Override
    public User readUser(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = bufferedReader.readLine())!=null){
            sb.append(line.trim());
        }
        bufferedReader.close();
        String xml = sb.toString();
        return  (User)xstream.fromXML(xml);
    }
}
