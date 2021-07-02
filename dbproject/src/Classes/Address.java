package src.Classes;

import java.io.Serializable;

public class Address implements Serializable {
    private int house;
    private int street;
    private String city;


    public void sethouse(int home){
        house = home;
    }
    public void setStreet(int st){
        street = st;
    }
    public void setCity(String ct){
        city = ct;
    }
    public int getHome() {
        return house;
    }
    public int getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }

    Address (){
        house=0;
        street=0;
        city="";
     }
    
    public Address (int h, int st, String cty){
        house = h;
        street = st;
        city = cty;
    }
    Address(Address add) {
        this.house = add.house;
        this.street = add.street;
        this.city = add.city;
    }

    public String toString() {
        return ("House: " + house + " Street: " + street + " City: " + city);
    }

}
