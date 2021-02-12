package model;

public class Client {
    private int id;
    private String name;
    private String address;

    /**
     *
     * @param nume numele clientului nou
     * @param address adresa noului client
     */
    public Client(String nume, String address)
    {
        this.name=nume;
        this.address=address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String toString()
    {
        return("Client Id : "+getId() + "Name : "+getName() + " Address : "+getAddress());
    }
}
