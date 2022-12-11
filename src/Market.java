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
    }

    public ArrayList<Game> getGames() {
        ArrayList<Game> games = new ArrayList<Game>();
        for(int i = 0; i < publishers.size(); i ++) {
            ArrayList<Game> pubisher_games = publishers.get(i).getOwnedGames();
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
            Publisher publisher = new Publisher(username);
            this.publishers.add(publisher);
            return publisher;
        } 
        else return new User(username);
    }

    public void login(String username, boolean isPublisher) {
        executor = new CommandExecutor();
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
