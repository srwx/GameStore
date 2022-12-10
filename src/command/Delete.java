package command;

import game.GameFactory;
import user.Publisher;
import user.User;
import user.UserFactory;

public class Delete extends Command {
    public Delete(GameFactory game, UserFactory user) {
        super(game, user);
    }

    public void execute() {
        if (user instanceof Publisher) {
            Publisher requetedPublisher = (Publisher) user;
            requetedPublisher.removeGame(game);

            // set execution detail & datetime after execution complete
            String executedDetail = "Remove " + game.getName() + " from store.\n";
            setExecutedDateTime(executedDetail);
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
