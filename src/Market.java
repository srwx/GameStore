import java.util.ArrayList;

import command.CommandExecutor;
import example.dummy_data.GameStoreDatabase;
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
    }

    public ArrayList<Game> getGames() {
        ArrayList<Game> games = new ArrayList<Game>();
        for (Publisher publisher : GameStoreDatabase.getPublisherList()) {
            ArrayList<Game> pubisher_games = publisher.getOwnedGames();
            games.addAll(pubisher_games);
        }
        return games;
    }

    public void addPublisher(Publisher publisher) {
        this.publishers.add(publisher);
    }

    public void removePublisher(Publisher publisher) {
        if(this.publishers.contains(publisher)) this.publishers.remove(publisher);
    }

    public void removePublisher(int index) {
        if(index < this.publishers.size()) this.publishers.remove(index);
    }

    public UserFactory createUser(String username, boolean isPublisher) {
        executor = new CommandExecutor();
        if(isPublisher) {
            Publisher newPublisher = new Publisher(username);
            GameStoreDatabase.addPublisher(newPublisher);
            return newPublisher; 
        }   
        else {
            User newUser = new User(username);
            GameStoreDatabase.addUser(newUser);
            return newUser; 
        }
    }

    public void login(String username, boolean isPublisher) {
        executor = new CommandExecutor();
        // if(isPublisher) this.user = new Publisher(username);
        // else this.user = new User(username);
        if(isPublisher) this.user = GameStoreDatabase.searchPublisher(username);
        else this.user = GameStoreDatabase.searchUser(username);
    }

    public void logout() {
        this.user = null;
    }

    public UserFactory getLoggedInUser() {
        return this.user;
    }

    public CommandExecutor getExecutor() {
        return this.executor;
    }

    public ArrayList<Publisher> getPublishers() {
        return this.publishers;
    }
}
