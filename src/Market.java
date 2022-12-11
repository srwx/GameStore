import java.util.ArrayList;

import command.CommandExecutor;
import game.Game;
import user.Publisher;
import user.User;
import user.UserFactory;

public class Market {
    private ArrayList<Publisher> publishers;
    private UserFactory user;
    private CommandExecutor executor;

    public Market(ArrayList<Publisher> publishers) {
        this.publishers = publishers;
        executor = new CommandExecutor();
    }

    public ArrayList<Game> getGames() {
        ArrayList<Game> games = new ArrayList<Game>();
        for(int i = 0; i < publishers.size(); i ++) {
            ArrayList<Game> pubisher_games = publishers.get(i).getOwnedGames();
            games.addAll(pubisher_games);
        }
        return games;
    }

    public UserFactory createUser(String username, boolean isPublisher) {
        if(isPublisher) return new Publisher(username);
        else return new User(username);
    }

    public void login(String username, boolean isPublisher) {
        if(isPublisher) this.user = new Publisher(username);
        else this.user = new User(username);
    }

    public UserFactory getLoggedInUser() {
        return this.user;
    }

    public CommandExecutor getExecutor() {
        return this.executor;
    }
}
