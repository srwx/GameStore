package command;

import game.GameFactory;
import user.Publisher;
import user.UserFactory;

public class Update extends Command {
    public Update(GameFactory game, UserFactory user) {
        super(game, user);
    }

    public void execute() {
        if (user instanceof Publisher) {
            // TODO: Update game logic

            // set execution detail & datetime after execution complete
            String executedDetail = "Update " + game.getName() + "information.\n";
            setExecutedDateTime(executedDetail);
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
