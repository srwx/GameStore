package command;

import game.GameFactory;
import user.UserFactory;

public class Update extends Command {
    public Update(GameFactory game, UserFactory user) {
        super(game, user);
    }

    public void execute() {

    }
}
