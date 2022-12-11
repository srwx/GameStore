package example.dummy_data;

import java.util.ArrayList;

import game.*;
import user.*;

// This class used for generate dummy data of game
public class GameStoreDatabase {
    public static Game temp1 = new Game("Assassin's Creed Valhalla", "Become a legendary Viking on a quest for glory. Raid your enemies and build your political power.", 230, "Action");
    public static Game temp2 = new Game("Divine Knockout (DKO)", "KO the gods in the world’s only 3rd-person platform fighter!", 180, "Action");
    public static Game temp3 = new Game("Need for Speed™ Unbound", "Race to the top, definitely don’t flop. Outsmart the cops, and enter ultimate street race.", 470, "Racing");

    public static Dlc dlc1 = new Dlc("Ragnarok Edition", "Ragnarok DLC for Assassin's Creed Valhalla", 80, temp1.getId());
    public static Dlc dlc2 = new Dlc("Complete Edition", "Fully DLC bundle for Assassin's Creed Valhalla", 120, temp1.getId());
    public static Dlc dlc3 = new Dlc("Unbound Palace Edition", "Palace extension for Need for Speed™ Unbound", 180, temp3.getId());

    public static Publisher publisher1 = new Publisher("publisher1");
    public static Publisher publisher2 = new Publisher("publisher2");
    public static Publisher publisherDemo = new Publisher("publisher");

    public static User user1 = new User("bob");

    public static ArrayList<Publisher> publisherList = new ArrayList<Publisher>();
    public static ArrayList<User> userList = new ArrayList<User>();

    public static void init() {
        // Add DLCs to game
        temp1.addExtension(dlc1);
        temp1.addExtension(dlc2);
        temp3.addExtension(dlc3);

        // Add games to publisher
        publisher1.addGame(temp1);
        publisher1.addGame(temp2);
        publisher2.addGame(temp3);

        // Add game to user
        user1.addGame(temp1);

        // Add publishers to list
        publisherList.add(publisher1);
        publisherList.add(publisher2);
        publisherList.add(publisherDemo);

        // Add user to list
        userList.add(user1);
    }

    public static ArrayList<Publisher> getPublisherList() {
        return publisherList;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static User searchUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static Publisher searchPublisher(String username) {
        for (Publisher  publisher : publisherList) {
            if (publisher.getUsername().equals(username))
                return publisher;
        }
        return null;
    }

    public static void addUser(User user) {
        userList.add(user);
    }

    public static void addPublisher(Publisher publisher) {
        publisherList.add(publisher);        
    }
}
