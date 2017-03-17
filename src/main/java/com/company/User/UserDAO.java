package com.company.User;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO {

    private List<User> userBase;

    public UserDAO(){
        userBase = new ArrayList<>();
    }

    //Check whether base is empty
    public boolean isBaseEmpty(){
        return userBase.isEmpty();
    }

    //CRUD realisation
    //add user to base
    public void addUser(User user){
        if(isUniqueUser(user)){
            userBase.add(user);
        }
    }

    //return list

    public boolean deleteUserByPhoneNumber(String number){
        User user = findUserByPhoneNumber(number);
        if(!userBase.isEmpty() && user!=null){
            userBase.remove(user);
            return true;
        }
        return false;
    }

    //check user uniqueness
    private boolean isUniqueUser(User user) {
        if(userBase.isEmpty()){
            return true;
        }else{
            for(User u : userBase){
                if(!u.getPhoneNumber().equals(user.getPhoneNumber())){
                    return true;
                }
            }
        }
        return false;
    }

    public List<User> getAllUserBase(){
        return userBase;
    }


    public User findUserByName(String name){
        User result= null;
        for(User user : userBase){
            if(user.getName().contains(name)){
                result = user;
            }
        }
        return result;
    }

    //Finding user in given collection by matching surname or part of surname
    public User findUserBySurname(String surname){
        User result = null;
        for(User user : userBase){
            if(user.getSurname().contains(surname)){
                result = user;
            }
        }
        return  result;
    }
    //Finding user in given collection by matching only valid number

    public User findUserByPhoneNumber(String number){
        User result = null;
        for (User user : userBase) {
            if (user.getPhoneNumber().equals(number)) {
                result = user;
            }
        }
        return result;
    }
    public void sortByName(){
        Collections.sort(userBase, (user1, user2) -> user1.getName().compareTo(user2.getName()));
    }
}