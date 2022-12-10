import java.util.ArrayList;
import game.GameFactory;
import user.Publisher;
import user.User;
import user.UserFactory;

public class Market {
    private ArrayList<Publisher> publishers;
    private UserFactory user;
    // history

    public Market(ArrayList<Publisher> publishers) {
        this.publishers = publishers;
    }

    public ArrayList<GameFactory> getGames() {
        ArrayList<GameFactory> games = new ArrayList<GameFactory>();
        for(int i = 0; i < publishers.size(); i ++) {
            ArrayList<GameFactory> pubisher_games = publishers.get(i).getOwnedGames();
            games.addAll(pubisher_games);
        }
        return games;
    }

    public UserFactory createUser(String username, boolean isPublisher) {
        if(isPublisher) return new Publisher(username);
        else return new User(username);
    }

    public void login(String username) {
        this.user = new UserFactory(username);
    }

    public UserFactory getLoggedInUser() {
        return this.user;
    }
}
