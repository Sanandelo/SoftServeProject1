package com.company.User;

import com.company.Car.Car;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class User {
    private String Id;//atribute in xml for user
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private List<Car> carsCollection;

    private User(String name, String surname, LocalDate dateOfBirth, String phoneNumber){
        this.Id = UUID.randomUUID().toString();
        this.carsCollection = new ArrayList<>();

        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getId() {
        return Id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Car> getCarsOfUser() {
        return carsCollection;
    }
    public void setCarsCollection(List<Car> list){

        this.carsCollection = list;
    }
    public void setId(String id ){
        this.Id = id;
    }

    public boolean addCar(Car car){
        if(carsCollection.isEmpty()) {
            carsCollection.add(car);
            return true;
        }else{
            for (Car c : carsCollection) {
                if (!c.getNumber().equals(car.getNumber())) {
                    carsCollection.add(car);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteCar(Car car){
        if(carsCollection.isEmpty()) {
            return false;
        }else{
            for (Car c : carsCollection) {
                if (c.equals(car)) {
                    carsCollection.remove(car);
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "User{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", carsCollection=" + carsCollection  +
                '}';
    }

    public static class UserBuilder{
        private String nestedName;
        private String nestedSurname;
        private LocalDate nestedDateOfBirth;
        private String nestedPhoneNumber;

        public UserBuilder setName(String name){
            this.nestedName = name;
            return this;
        }

        public UserBuilder setSurname(String surname){
            this.nestedSurname = surname;
            return this;
        }

        public UserBuilder setDatOfBirth(LocalDate date){
            this.nestedDateOfBirth = date;
            return this;
        }

        public UserBuilder setPhoneNumber(String number){
            this.nestedPhoneNumber = number;
            return this;
        }

        public User build(){
            return new User(nestedName,nestedSurname,nestedDateOfBirth,nestedPhoneNumber);
        }
    }
}