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
        ArrayList<Game> userGame = user.getOwnedGames();
        if (user instanceof User) {
            if(game instanceof Dlc) {
                boolean hasMainGame = false;
                int i = 0;
                Dlc thisDlc = (Dlc)game;
                for(i = 0; i < userGame.size(); i++) {
                    System.out.println(userGame.get(i).getId().equals(thisDlc.getGameId())); // print true
                    if(userGame.get(i).getId().equals(thisDlc.getGameId())) {
                        System.out.println("enter set has main game");
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
                    else {
                        success = true;
                        System.out.println("Failed to buy " + game.getName() + ", you already have this DLC");
                    } 
                } else {
                    success = true;
                    System.out.println("Failed to buy " + game.getName() + ", you don't have the main game of this DLC");
                }
            } else if(game instanceof Game) {
                boolean hasGame = false;
                for(int i = 0; i < userGame.size(); i++) {
                    if(userGame.get(i).getId().equals(game.getId())) {
                        hasGame = true;
                    }
                }
                if(hasGame) {
                    success = true;
                    System.out.println("Failed to buy " + game.getName() + ", already have this game in your account");
                } else {
                    boolean paymentValidated = payment.validatePayment(game.getPrice());
                    if (paymentValidated) {
                        // Copy game object to user class
                        Game gameToAdd = new Game(game.getId());
                        gameToAdd.setCategory(((Game)game).getCategory());
                        gameToAdd.setDescription(((Game)game).getDescription());
                        gameToAdd.setName(((Game)game).getName());
                        gameToAdd.setPrice(((Game)game).getPrice());
                        user.addGame(gameToAdd);
                        System.out.println(game.getName() + " added to your library");

                        // set execution detail & datetime after execution complete
                        String executedDetail = "Successfully Buy " + game.getName() + "\n";
                        setExecutedDateTime(executedDetail);
                        success = true;
                    } else {
                        System.out.println("Failed to buy " + game.getName() + ", please add your balance");
                    }
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
