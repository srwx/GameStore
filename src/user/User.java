package user;

import java.util.ArrayList;
import game.GameFactory;

public class User extends UserFactory {
    private ArrayList<GameFactory> cart;

    public User(String username) {
        super(username);
    }
    
    public User(String username, ArrayList<GameFactory> ownedGames) {
        super(username, ownedGames);
    }

    public ArrayList<GameFactory> getCart() {
        return this.cart;
    }

    public void addToCart(GameFactory game) {
        cart.add(game);
    }   

    public void removeFormCart(GameFactory game) {
        int index = cart.size();
        cart.remove(index);
    }   
}