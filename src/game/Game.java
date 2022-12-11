package game;

import java.util.ArrayList;

public class Game extends GameFactory {
    private String category;
    private ArrayList<Dlc> extension = new ArrayList<Dlc>();

    public Game() {};

    public Game(String id) {
        super(id);
    }

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

    private ArrayList<String> getExtensionNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < extension.size(); i++) {
            names.add(extension.get(i).getName());
        }
        return names;
    }

    // TODO
    @Override
    public void printDetail() {
        System.out.println("Game name: " + this.getName());
        System.out.println("Game description: " + this.getDescription());
        System.out.println("Game price: " + this.getPrice());
        System.out.println("DLCs: " + (extension.size() > 0 ? String.join(", ", getExtensionNames()) : "-"));
        System.out.println("------------------------------------");
    }

    public void addExtension(Dlc dlc) {
        this.extension.add(dlc);
    }

    public ArrayList<Dlc> getExtension() {
        return this.extension;
    }
}
