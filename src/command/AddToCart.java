package command;

import game.GameFactory;
import user.UserFactory;
import user.User;

public class AddToCart extends Command {
    public AddToCart(GameFactory game, UserFactory user) {
        super(game, user);
    }

    public void execute() {
        if (user instanceof User) {
            user.addToCart(game);
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
