package command;

import game.GameFactory;
import user.UserFactory;
import payment.Payment;

public class Buy extends Command {
    private Payment payment;

    public Buy(GameFactory game, UserFactory user, Payment payment) {
        super(game, user);
        this.payment = payment;
    }

    public void execute() {
        boolean paymentValidated = payment.validatePayment(game.getPrice());
        if (paymentValidated) {
            user.addGame(game);
            System.out.println(game.getName() + " added to your library");

            // set execution detail & datetime after execution complete
            String executedDetail = "Successfully Buy " + game.getName() + "\n";
            setExecutedDateTime(executedDetail);
        } else {
            System.out.println("Failed to buy " + game.getName());
        }
    }
}
