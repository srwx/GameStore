package command;

import java.util.ArrayList;

import game.*;
import user.User;
import user.UserFactory;
import payment.Payment;

public class Buy extends Command {
    private Payment payment;

    public Buy(GameFactory game, UserFactory user, Payment payment) {
        super(game, user);
        this.payment = payment;
    }

    public void execute() {
        boolean success = false;
        if (user instanceof User) {
            boolean hasMainGame = false;
            if(game instanceof Dlc) {
                int i = 0;
                Dlc thisDlc = (Dlc)game;
                ArrayList<Game> userGame = user.getOwnedGames();
                for(i = 0; i < userGame.size(); i++) {
                    if(userGame.get(i).getId() == thisDlc.getGameId()) {
                        hasMainGame = true;
                        break;
                    }
                }
                if(hasMainGame) {
                    if(!userGame.get(i).getExtension().contains(thisDlc)) {
                        boolean paymentValidated = payment.validatePayment(game.getPrice());
                        if(paymentValidated) {
                            user.getOwnedGames().get(i).addExtension((Dlc)game);
                            System.out.println(game.getName() + " added to your library");

                            // set execution detail & datetime after execution complete
                            String executedDetail = "Successfully Buy " + game.getName() + "\n";
                            setExecutedDateTime(executedDetail);
                            success = true;
                        }
                        else {
                            System.out.println("Failed to buy " + game.getName() + ", please add your balance");
                        } 
                    }
                    else System.out.println("Failed to buy " + game.getName() + ", you already have this DLC");
                } else {
                    System.out.println("Failed to buy " + game.getName() + ", you don't have the main game of this DLC");
                }
            } else if(game instanceof Game) {
                boolean paymentValidated = payment.validatePayment(game.getPrice());
                if (paymentValidated) {
                    user.addGame((Game)game);
                    System.out.println(game.getName() + " added to your library");

                    // set execution detail & datetime after execution complete
                    String executedDetail = "Successfully Buy " + game.getName() + "\n";
                    setExecutedDateTime(executedDetail);
                    success = true;
                } else {
                    System.out.println("Failed to buy " + game.getName() + ", please add your balance");
                }
            }
            if(success) {
                CommandExecutor executor = new CommandExecutor();
                executor.executeCommand(new RemoveFromCart(game, user));
            }      
        } else {
            System.out.println("You have no permission to perform this action.");
        }
    }
}
