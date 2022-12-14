package command;

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
        boolean isSuccess = false;
        boolean IsGameValidated = false;

        if (user instanceof User) {
            // Game Validation
            if(game instanceof Dlc) {                
                Dlc thisDlc = (Dlc)game;
                Boolean hasMainGame = user.hasGame(thisDlc.getGameId());

                // check if user has a main game for dlc
                if(hasMainGame) {
                    boolean hasDlc = user.hasDlc(thisDlc);
                    // check if user already has this dlc in his library
                    if (hasDlc) {
                        isSuccess = true;
                        System.out.println("Failed to buy " + game.getName() + ", you already have this DLC.\n");
                    } else {
                        IsGameValidated = true;
                    } 
                } else {
                    System.out.println("Failed to buy " + game.getName() + ", you don't have the main game of this DLC.\n");
                }
            } else if(game instanceof Game) {
                boolean hasGame = user.hasGame(game.getId());
                // check if user already has this game in his library
                if(hasGame) {
                    isSuccess = true;
                    System.out.println("Failed to buy " + game.getName() + ", already have this game in your account.\n");
                } else {
                    IsGameValidated = true;
                }
            }
            
            if (IsGameValidated) {
                boolean isPaymentValidated = payment.validatePayment(game.getPrice());

                if (isPaymentValidated) {
                    // add game/dlc to user's library
                    if (game instanceof Game) {
                        // Copy game object to user class
                        Game gameToAdd = new Game(game.getId());
                        gameToAdd.setCategory(((Game)game).getCategory());
                        gameToAdd.setDescription(((Game)game).getDescription());
                        gameToAdd.setName(((Game)game).getName());
                        gameToAdd.setPrice(((Game)game).getPrice());
                        user.addGame(gameToAdd);
                    }
                    else {
                        Dlc dlcToAdd = (Dlc) game;
                        Game mainGame = user.getGame(dlcToAdd.getGameId());
                        user.addDlc(mainGame, dlcToAdd);
                    }                    
                    System.out.println("\n" + game.getName() + " added to your library\n");

                    // set execution detail & datetime after execution complete
                    String executedDetail = "Successfully Buy " + game.getName() + "\n";
                    setExecutedDateTime(executedDetail);
                    isSuccess = true;
                } else {
                    System.out.println("Failed to buy " + game.getName() + ", please add your balance\n");
                }
            }
            if(isSuccess) {
                CommandExecutor executor = new CommandExecutor();
                executor.executeCommand(new RemoveFromCart(game, user));
            }      
        } else {
            System.out.println("You have no permission to perform this action.\n");
        }
    }
}
