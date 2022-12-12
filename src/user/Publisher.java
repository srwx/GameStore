package user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import game.Dlc;
import game.Game;
import game.GameFactory;

public class Publisher extends UserFactory {
    private String publisherUrl;

    public Publisher() {};

    public Publisher(String username) {
        super(username);
    }
    
    public Publisher(String username, ArrayList<Game> ownedGames) {
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
        boolean flag = false;
        if(game instanceof Dlc) {
            for (GameFactory ownedGame : ownedGames) {
                if (Objects.equals(ownedGame.getId(), ((Dlc) game).getGameId())) {
                    flag = true;
                    break;
                }
            }
        }
        else if(ownedGames.contains(game)) {
            flag = true;
        }

        if(flag) {
            if(detail.containsKey("name")) game.setName(detail.get("name"));
            if(detail.containsKey("price")) game.setPrice(Double.parseDouble(detail.get("price")));
            if(detail.containsKey("description")) game.setDescription(detail.get("description"));
            return true;
        }
        return false;
    }
}
