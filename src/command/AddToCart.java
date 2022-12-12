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
            User requetedUser = (User) user;
            requetedUser.addToCart(game);
            // set execution detail & datetime after execution complete
            String executedDetail = "Add " + game.getName() + " to cart.\n";
            setExecutedDateTime(executedDetail);
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
