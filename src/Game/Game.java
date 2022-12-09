package game;

import java.util.ArrayList;

public class Game extends GameFactory {
    private String category;
    private ArrayList<Dlc> extension = new ArrayList<Dlc>();

    public Game(String name, String description, double price, String category) {
        super(name, description, price);
        setCategory(category);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // TODO
    @Override
    public void printDetail() {
        System.out.println("Game name: " + this.getName());
        System.out.println("Game description: " + this.getDescription());
        System.out.println("List of DLC: " + this.getExtension());
        System.out.println("------------------------------------");
    }

    public void addExtension(Dlc dlc) {
        this.extension.add(dlc);
    }

    public ArrayList<Dlc> getExtension() {
        return this.extension;
    }
}
