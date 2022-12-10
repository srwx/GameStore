package user;

import java.util.ArrayList;
import java.util.HashMap;

import game.Dlc;
import game.Game;
import game.GameFactory;

public class Publisher extends UserFactory {
    private String publisherUrl;

    public Publisher() {};

    public Publisher(String username) {
        super(username);
    }
    
    public Publisher(String username, ArrayList<GameFactory> ownedGames) {
        super(username, ownedGames);
    }

    public void setPublisherUrl(String url) {
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

    // detail key: name, price, description
    public boolean editGame(GameFactory game, HashMap<String, String> detail) {
        if(ownedGames.contains(game)) {
            if(detail.containsKey("name")) game.setName(detail.get("name"));
            if(detail.containsKey("price")) game.setPrice(Double.parseDouble(detail.get("price")));
            if(detail.containsKey("description")) game.setDescription(detail.get("description"));
            return true;
        } else return false;
    }

    public boolean addDlc(Game game, Dlc dlc) {
        if(ownedGames.contains(game)) {
            game.addExtension(dlc);
            return true;
        } else {
            return false;
        }
    }
}
