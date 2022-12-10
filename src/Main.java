import java.util.ArrayList;
import java.util.HashMap;

import command.AddToCart;
import command.Buy;
import command.CommandExecutor;
import dummy_data.TempGame;
import game.Game;
import game.GameFactory;
import payment.UserWallet;
import ui.InputLocgic;
import ui.Ui;
import user.Publisher;
import user.User;
import user.UserFactory;

public class Main {
    
    static ArrayList<Publisher> init() {
        TempGame.init();
        ArrayList<Publisher> publishersList = new ArrayList<Publisher>();
        publishersList.add(TempGame.publisher1);
        publishersList.add(TempGame.publisher2);
        return publishersList;
    }

    static void authentication(Market market) {
        int menuSelected = -1;

        menuSelected = Ui.cover();
        if(menuSelected == 1) {
            // Create an account
            HashMap<String, String> detail = Ui.createUserPage();
            String username = detail.get("username");
            String role = detail.get("role");
            UserFactory dummyUser = new UserFactory();
            dummyUser = market.createUser(username, role == "user" ? false : true);
            market.login(dummyUser.getUsername(), dummyUser instanceof Publisher);
        } 
        else if(menuSelected == 2) {
            // Login
            String username = Ui.loginPage();
            if(username.equals("publisher")) market.login(username, true);
            else market.login(username, false);
        } else if(menuSelected == 3) {
            // Exit program
            System.exit(0);
        }
    } 

    // Demo for user: show cart/ownedGame -> select game to cart/show cart -> buy -> show ownedGame
    static void userDemo(Market market) {
        CommandExecutor executor = new CommandExecutor();
        ArrayList<GameFactory> cart = ((User)market.getLoggedInUser()).getCart();
        UserWallet wallet = new UserWallet(1000);
        User user = (User)market.getLoggedInUser();
        String input = "";

        // Show cart/ownedGame
        Ui.ownedGamePage(user);
        Ui.cartPage(user);
        InputLocgic.getInput(false);

        // select game to cart
        while(!input.equalsIgnoreCase("n")) {
            GameFactory game;
            game = Ui.home(market.getGames());
            game = Ui.gameDetailPage((Game)game);
            if(game.getId() != null && !cart.contains(game)) executor.executeCommand(new AddToCart(game, user));
            boolean checkInput = false;
            while(!checkInput) {
                System.out.print("Add more game?(y/n): ");
                input = InputLocgic.getInput(false).toLowerCase();
                if(input.equals("y") || input.equals("n")) checkInput = true;
            }
        }

        // select game to buy in cart
        boolean check = false;
        int menuSelected = -1;
        while(!check) {
            Ui.cartPage(user);
            System.out.print("Selecte game to buy: ");
            input = InputLocgic.getInput(true);
            if(InputLocgic.integerPrasingGard(input)) {
                menuSelected = Integer.parseInt(input);
                if(!(menuSelected > cart.size() || menuSelected < 1)) check = true;
            }
        }
        executor.executeCommand(new Buy(cart.get(menuSelected-1), user, wallet));

        // show ownedGame
        Ui.ownedGamePage(user);
    }

    static void publisherDemo(Market market) {
        System.out.println("Welcome publisher");
    }

    public static void main(String[] args) {
        Market market = new Market(init());
        
        authentication(market);
        if(market.getLoggedInUser() instanceof User) {
            userDemo(market);
        } else {
            publisherDemo(market);
        }
    }
}