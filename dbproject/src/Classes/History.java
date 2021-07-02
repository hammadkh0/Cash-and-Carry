package src.Classes;

public class History {
    private String name; 
    private int totalItems,totalBill;
    private java.sql.Date date;
    private int id;


    public History(String name, int totalItems, int totalBill, java.sql.Date date, int id) {
        this.name = name;
        this.totalItems = totalItems;
        this.totalBill = totalBill;
        this.date = date;
        this.id = id;
    }

    

    public int getTotalItems() {
        return this.totalItems;
    }

    public int getTotalBill() {
        return this.totalBill;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public java.util.Date getDate() {
        return this.date;
    }
}