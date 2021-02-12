package model;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private float price;

    /**
     *
     * @param name numele produsului
     * @param quant cantitatea produsului
     * @param price pretul produsului
     */
    public Product( String name, int quant, float price)
    {

        this.name=name;
        this.quantity=quant;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {this.price = price; }

    public String toString()
    {
        return( " Product ID : "+getId() + " Name : " + getName() + " quantity : " + getQuantity() + " price : " + getPrice());
    }
}
