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
            User requetedUser = (User) user;
            requetedUser.removeFromCart(game);
            // set execution detail & datetime after execution complete
            String executedDetail = "Remove " + game.getName() + " from cart.\n";
            setExecutedDateTime(executedDetail);
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
