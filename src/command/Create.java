package command;

import game.GameFactory;
import user.Publisher;
import user.User;
import user.UserFactory;

public class Create extends Command {
    public Create(GameFactory game, UserFactory user) {
        super(game, user);
    }

    public void execute() {
        if (user instanceof Publisher) {
            user.addGame(game);

            // set execution detail & datetime after execution complete
            String executedDetail = "Create " + game.getName() + "to store.\n";
            setExecutedDateTime(executedDetail);
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
