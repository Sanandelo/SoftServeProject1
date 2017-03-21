package com.company.Car;

import java.time.LocalDate;

public class Car {
    private String model;
    private String number;
    private Colors color;
    private BodyStyle bodyStyle;
    private LocalDate dateOfProduction;

    public Car(String model, String number, Colors color, BodyStyle bodyStyle, LocalDate dateOfProduction) {
        this.model = model;
        this.number = number;
        this.color = color;
        this.bodyStyle = bodyStyle;
        this.dateOfProduction =  dateOfProduction;
    }

    public LocalDate getDateOfProduction() {
        return dateOfProduction;
    }

    public String getModel() {
        return model;
    }

    public String getNumber() {
        return number;
    }

    public Colors getColor() {
        return color;
    }

    public BodyStyle getBodyStyle() {
        return bodyStyle;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", number='" + number + '\'' +
                ", color=" + color +
                ", bodyStyle=" + bodyStyle +
                ", dateOfProduction=" + dateOfProduction + "."+
                '}';
    }

    //Builder for Car

    public static class CarBuilder{
        private String nestedModel;
        private String nestedNumber;
        private Colors nestedColor;
        private BodyStyle nestedBodyStyle;
        private LocalDate nestedDateOfProduction;

        public CarBuilder setModel(String model){
            this.nestedModel = model;
            return this;
        }
        public CarBuilder setNumber(String number){
            this.nestedNumber = number;
            return this;
        }
        public CarBuilder setColor(Colors color){
            this.nestedColor = color;
            return this;
        }

        public CarBuilder setBodyStyle(BodyStyle bodyStyle){
            this.nestedBodyStyle = bodyStyle;
            return this;
        }
        public CarBuilder setDateOfProduction(LocalDate dateOfProduction){
            this.nestedDateOfProduction = dateOfProduction;
            return this;
        }
        public Car build(){
            return new Car(nestedModel,nestedNumber,nestedColor,nestedBodyStyle,nestedDateOfProduction);
        }
    }
}