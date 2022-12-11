package game;

public class Dlc extends GameFactory {
    private String gameId;

    public Dlc() {
    }

    public Dlc(String id) {
        super(id);
    }

    public Dlc(String name, String description, double price, String gameId) {
        super(name, description, price);
        setGameId(gameId);
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    // TODO
    public void printDetail() {
        String format = "%-20s%s%n";
        System.out.println("detail for dlc:");
        System.out.printf(format, "- DLC name: ", this.getName(), "\n");
        System.out.printf(format, "- DLC description: ", this.getDescription(), "\n");
        System.out.printf(format, "- DLC price: ", this.getPrice(), "\n");
        System.out.println("");
    }
}
