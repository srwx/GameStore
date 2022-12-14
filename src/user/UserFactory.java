package user;

import java.util.ArrayList;
import java.util.UUID;

import game.Dlc;
import game.Game;

public class UserFactory {
    private final String userId;
    private String username;
    protected ArrayList<Game> ownedGames;

    public UserFactory() {
        this.userId = null;
    }

    public UserFactory(String username) {
        this.username = username;
        this.userId = UUID.randomUUID().toString();
        this.ownedGames = new ArrayList<Game>();
    }

    public UserFactory(String username, ArrayList<Game> ownedGames) {
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

    public ArrayList<Game> getOwnedGames() {
        return this.ownedGames;
    }

    public void addGame(Game game) {
        this.ownedGames.add(game);
    }

    public Game getGame(String id) {
        for (Game game : ownedGames) {
            if (game.getId().equals(id))
                return game;
        }
        return null;
    }

    public boolean hasGame(String id) {
        for (Game game : ownedGames) {
            if (game.getId().equals(id))
                return true;
        }
        return false;
    }

    public boolean hasDlc(Dlc dlc) {
        for (Game game : ownedGames) {
            if (game.getExtension().contains(dlc))
                return true;
        }
        return false;
    }

    public boolean addDlc(Game game, Dlc dlc) {
        if(ownedGames.contains(game)) {
            game.addExtension(dlc);
            return true;
        } else {
            return false;
        }
    }

    public void printDetail() {
        System.out.println("userId: " + this.userId);
        System.out.println("username: " + this.username);
    }
}
