package com.company.Car;

import java.util.Collections;
import java.util.List;


public class CarService {
    private List<Car> carCollection;
    public CarService(List<Car> car){
        carCollection = car;
    }
    public List<Car> getCarCollection(){
        return carCollection;
    }
    //Sort collection of cars in decline order
    public void sortByDataDes(){
        carCollection.sort((car1, car2) -> car1.getDateOfProduction().compareTo(car2.getDateOfProduction()));
    }
    //Sort collection of cars in incline order

    public void sortByDataAcs(){
        carCollection.sort((car1, car2) -> car2.getDateOfProduction().compareTo(car1.getDateOfProduction()));

    }
    public Car findCarByNumber(String number){
        Car result = null;
        for(Car car : carCollection){
            if(car.getNumber().equals(number)){
                result = car;
            }
        }
        return result;
    }
}