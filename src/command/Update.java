package command;

import game.Game;
import game.GameFactory;
import user.Publisher;
import user.UserFactory;

import java.util.HashMap;

public class Update extends Command {
    private HashMap<String, String> detail; // key: "name" | "price" | "description"

    public Update(GameFactory game, UserFactory user, HashMap<String, String> detail) {
        super(game, user);
        setDetail(detail);
    }

    public HashMap<String, String> getDetail() {
        return detail;
    }

    public void setDetail(HashMap<String, String> detail) {
        this.detail = detail;
    }

    public void execute() {
        if (user instanceof Publisher) {
            Publisher requetedPublisher = (Publisher) user;
            boolean isSuccess = requetedPublisher.editGame(game, detail);

            if(isSuccess) {
                // set execution detail & datetime after execution complete
                String executedDetail = "Update " + game.getName() + " information.\n";
                setExecutedDateTime(executedDetail);
            } else {
                System.out.println("Update " + (game instanceof Game ? "game " : "dlc ") + "failed.");
            }
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
