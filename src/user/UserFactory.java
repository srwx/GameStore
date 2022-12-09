package user;

import java.util.ArrayList;
import java.util.UUID;

public class UserFactory {
    private final String userId;
    private String username;
    // private ArrayList<GameFactory> ownedGames;

    public UserFactory(String username) {
        this.username = username;
        this.userId = UUID.randomUUID().toString();
        // this.ownedGames = new ArrayList<GameFactory>();
    }

    // public UserFactory(String username, ArrayList<GameFactory> ownedGames) {
    // this.userId = UUID.randomUUID().toString();
    // this.ownedGames = ownedGames;
    // }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void printDetail() {
        System.out.println("username: " + this.username);
        System.out.println("userId: " + this.userId);
    }
}
