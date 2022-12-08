package Game;

import java.util.UUID;

public class Dlc {
    private final String dlcId;
    private String description;
    private double price;

    public Dlc(String description, double price) {
        this.dlcId = UUID.randomUUID().toString();
        this.description = description;
        this.price = price;
    }

    public String getDlcId() {
        return  this.dlcId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // TODO
    public void printDetail() {
        System.out.println("------- Information of DLC: " + this.dlcId + " -------");
        System.out.println("price: " + this.price);
        System.out.println("description: " + this.description);
    }
}
