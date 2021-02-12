package model;

public class Order {
    private int id;
    private int id_client ;
    private int id_product;
    private int quantity;

    /**
     *
     * @param client id ul clientului caruia vrem sa ii facem comanda
     * @param product id ul produsului din comanda
     * @param quant cantitatea produsului
     */
    public Order( int client, int product, int quant)
    {

        this.id_client=client;
        this.id_product=product;
        this.quantity=quant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }


    public String toString()
    {
        return("Order id : "+getId() + "Client : "+ getId_client() + " Product : "+ getId_product() + " with quantity : " + getQuantity());
    }


}
