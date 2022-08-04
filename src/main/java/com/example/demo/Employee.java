package com.example.demo;

public class Employee {

    private int code;
    private String name;
    private String availability;
    private String price;

    public int getCode() {
        return code;
    }

    public void setCode(int id) {
        this.code = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}