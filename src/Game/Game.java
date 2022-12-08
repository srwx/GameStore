package Game;

import java.util.ArrayList;
import java.util.UUID;

public class Game {
    private final String gameId;
    private String description;
    private String category;
    private double price;
    private ArrayList<Dlc> extension;

    public Game(String description, String category, double price) {
        this.gameId = UUID.randomUUID().toString();
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public String getGameId() {
        return this.gameId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addExtension(Dlc dlc) {
        this.extension.add(dlc);
    }

    public ArrayList<Dlc> getExtension() {
        return this.extension;
    }

    // TODO
    public void printDetail() {
        System.out.println("------- Information of Game: " + this.gameId + " -------");
        System.out.println("category: " + this.category);
        System.out.println("price: " + this.price);
        System.out.println("description: " + this.description);
    }
}
