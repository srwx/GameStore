package command;

import game.GameFactory;
import user.UserFactory;
import user.User;

public class RemoveFromCart extends Command {
    public RemoveFromCart(GameFactory game, UserFactory user) {
        super(game, user);
    }

    public void execute() {
        if (user instanceof User) {
            user.removeFromCart(game);
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
