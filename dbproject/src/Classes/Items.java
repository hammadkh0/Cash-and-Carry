package src.Classes;

public class Items{
    private int id;
    private String name;
    private int quantity;
    private int price;
    private String category;

    public Items(){}

    public Items(int id, String name, int quantity, int price, String category) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
   

    public int getQuantity() {
        return this.quantity;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String toString(){
        return this.getId()+"   "+this.getName()+"   "+this.getCategory()+"   "+this.getPrice();
    }

}
