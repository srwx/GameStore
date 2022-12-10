package user;

import java.util.ArrayList;
import java.util.UUID;
import game.GameFactory;

public class UserFactory {
    private final String userId;
    private String username;
    protected ArrayList<GameFactory> ownedGames;

    public UserFactory() {
        this.userId = null;
    }

    public UserFactory(String username) {
        this.username = username;
        this.userId = UUID.randomUUID().toString();
        this.ownedGames = new ArrayList<GameFactory>();
    }

    public UserFactory(String username, ArrayList<GameFactory> ownedGames) {
    this.userId = UUID.randomUUID().toString();
    this.username = username;
    this.ownedGames = ownedGames;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public ArrayList<GameFactory> getOwnedGames() {
        return this.ownedGames;
    }

    public void addGame(GameFactory game) {
        this.ownedGames.add(game);
    }

    public void printDetail() {
        System.out.println("userId: " + this.userId);
        System.out.println("username: " + this.username);
    }
}
