package user;

import java.util.ArrayList;
import game.GameFactory;

public class Publisher extends UserFactory {
    private String publisherUrl;

    public Publisher(String username) {
        super(username);
    }
    
    public Publisher(String username, ArrayList<GameFactory> ownedGames) {
        super(username, ownedGames);
    }

    public void setePublisherUrl(String url) {
        this.publisherUrl = url;
    }

    public String getPubliserUrl() {
        return this.publisherUrl;
    }

    public void removeGame(int index) {
        ownedGames.remove(index);
    }

    public void removeGame(GameFactory game) {
        ownedGames.remove(game);
    }
}
