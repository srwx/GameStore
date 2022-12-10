package user;

import java.util.ArrayList;
import game.GameFactory;

public class User extends UserFactory {
    private ArrayList<GameFactory> cart;

    public User() {};

    public User(String username) {
        super(username);
        this.cart = new ArrayList<GameFactory>();
    }
    
    public User(String username, ArrayList<GameFactory> ownedGames) {
        super(username, ownedGames);
        this.cart = new ArrayList<GameFactory>();
    }

    public ArrayList<GameFactory> getCart() {
        return this.cart;
    }

    public void addToCart(GameFactory game) {
        cart.add(game);
    }   

    public void removeFromCart(GameFactory game) {
        cart.remove(game);
    }   

    public void removeFromCart(int index) {
        cart.remove(index);
    } 
}
