package command;

import game.Dlc;
import game.Game;
import game.GameFactory;
import user.Publisher;
import user.UserFactory;

import java.util.HashMap;

public class AddDlc extends Command {
    private Dlc dlc;

    public AddDlc(GameFactory game, UserFactory user, Dlc dlc) {
        super(game, user);
        setDlc(dlc);
    }

    public void setDlc(Dlc dlc) {
        this.dlc = dlc;
    }

    public void execute() {
        if (user instanceof Publisher) {
            Publisher requetedPublisher = (Publisher) user;
            Game updateGame = (Game) game;
            boolean isSuccess = requetedPublisher.addDlc(updateGame, dlc);

            if(isSuccess) {
                // set execution detail & datetime after execution complete
                String executedDetail = "Add DLC " + dlc.getName() + " to game " + updateGame.getName() + ".\n";
                setExecutedDateTime(executedDetail);
            } else {
                System.out.println("Update dlc failed.");
            }
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
