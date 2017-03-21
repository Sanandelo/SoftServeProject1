package com.company.User;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersList {

    private List<User> users;

    public UsersList(){
        users = new ArrayList<>();
    }

    //Check whether base is empty
    public boolean isBaseEmpty(){
        return users.isEmpty();
    }

    //CRUD realisation
    //add user to base
    public void addUser(User user){
        if(isUniqueUser(user)){
            users.add(user);
        }
    }

    //return list

    public boolean deleteUserByPhoneNumber(String number){
        User user = findUserByPhoneNumber(number);
        if(!users.isEmpty() && user!=null){
            users.remove(user);
            return true;
        }
        return false;
    }

    //check user uniqueness
    private boolean isUniqueUser(User user) {
        if(users.isEmpty()){
            return true;
        }else{
            for(User u : users){
                if(!u.getPhoneNumber().equals(user.getPhoneNumber())){
                    return true;
                }
            }
        }
        return false;
    }

    public List<User> getUsers(){
        return users;
    }

    public void setUsers(ArrayList<User> users){
        this.users.clear();
        this.users = users;
    }


    public User findUserByName(String name){
        User result= null;
        for(User user : users){
            if(user.getName().contains(name)){
                result = user;
            }
        }
        return result;
    }

    //Finding user in given collection by matching surname or part of surname
    public User findUserBySurname(String surname){
        User result = null;
        for(User user : users){
            if(user.getSurname().contains(surname)){
                result = user;
            }
        }
        return  result;
    }
    //Finding user in given collection by matching only valid number

    public User findUserByPhoneNumber(String number){
        User result = null;
        for (User user : users) {
            if (user.getPhoneNumber().equals(number)) {
                result = user;
            }
        }
        return result;
    }
    public void sortByName(){
        Collections.sort(users, (user1, user2) -> user1.getName().compareTo(user2.getName()));
    }
}