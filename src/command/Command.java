package command;

import game.GameFactory;
import user.UserFactory;

public abstract class Command {
    GameFactory game;
    UserFactory user;

    public Command(GameFactory game, UserFactory user) {
        this.game = game;
        this.user = user;
    }

    abstract public void execute();
}
