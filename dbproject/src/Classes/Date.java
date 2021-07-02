package src.Classes;

import java.io.Serializable;

public class Date implements Serializable {
    private int day;
    private int month;
    private  int year;

    public Date(){
        day = 1;
        month = 1;
        year = 1970;
    }
    public Date(Date dt){
        this.day = dt.day;
        this.month = dt.month;
        this.year = dt.year;
    }
    public Date(int days, int mth, int yr){
        this.day = days;
        this.month = mth;
        this.year = yr;   
    }
    

    public void setDay(int day){
        this.day = day;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setYear(int year){
        this.year = year;
    }

    
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

}

