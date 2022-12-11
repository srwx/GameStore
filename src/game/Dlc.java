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
        System.out.println("detail for dlc");
        System.out.println("DLC name: " + this.getName());
        System.out.println("DLC description: " + this.getDescription());
        System.out.println("DLC price: " + this.getPrice());
        System.out.println("------------------------------------");
    }
}
