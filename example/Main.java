
import java.util.ArrayList;
import java.util.HashMap;

import command.AddToCart;
import command.Buy;
import command.CommandExecutor;
import dummy_data.TempGame;
import game.Dlc;
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
            // In this demo will query user form logged in user of market if system has this user,
            // otherwise will create an new instance
            if(market.getLoggedInUser() == null) {
                if(username.equals("publisher")) market.login(username, true);
                else market.login(username, false);
            }
        } else if(menuSelected == 3) {
            // Exit program
            System.exit(0);
        }
    } 

    static void selectDlcDemo(Market market, ArrayList<Dlc> dlc) {
        String input = "";
        boolean checkInput = false;
        ArrayList<GameFactory> cart = ((User)market.getLoggedInUser()).getCart();
        CommandExecutor executor = market.getExecutor();
        User user = (User)market.getLoggedInUser();

        if(!dlc.isEmpty()) {
            ArrayList<String> dlcOption = new ArrayList<String>(){
                {
                    add("dlc0");
                }
            };
            int i = -1;
            for(i = 0; i < dlc.size(); i++) {
                dlcOption.add("dlc" + (i+1));
            }
            input = "";
            while(!checkInput) {
                System.out.print("Do you want to see any dlcs? (select by command dlc[number] or dlc0 if you don't): ");
                input = InputLocgic.getInput(false).toLowerCase();
                if(dlcOption.contains(input)) checkInput = true;
            }
            if(!input.equalsIgnoreCase("dlc0")) {
                String index = Character.toString(input.charAt(3));
                int dlcIndex = Integer.parseInt(index)-1;
                Dlc selectedDlc = Ui.dlcDetailPage(dlc.get(dlcIndex));
                if(selectedDlc.getId() != null && !cart.contains(selectedDlc)) executor.executeCommand(new AddToCart(selectedDlc, user));
            }  
        }
    }

    static void selectGameDemo(Market market) {
        ArrayList<GameFactory> cart = ((User)market.getLoggedInUser()).getCart();
        CommandExecutor executor = market.getExecutor();
        User user = (User)market.getLoggedInUser();
        ArrayList<Dlc> dlc = new ArrayList<Dlc>();
        String input = "";
        boolean checkInput = false;

        // select game to cart
        while(!input.equalsIgnoreCase("n")) {
            dlc = new ArrayList<Dlc>();
            checkInput = false;
            GameFactory game;
            GameFactory selectedGame;
            selectedGame = Ui.home(market.getGames()); // return the selected game
            game = Ui.gameDetailPage((Game)selectedGame); // return object game to add to cart
            if(game.getId() != null && !cart.contains(game)) {
                executor.executeCommand(new AddToCart(game, user));
            } 
            dlc = ((Game)selectedGame).getExtension();
            // Ask user to see any dlc of the selected game
            selectDlcDemo(market, dlc);
            
            checkInput = false;
            while(!checkInput) {
                System.out.print("Add more game?(y/n): ");
                input = InputLocgic.getInput(false).toLowerCase();
                if(input.equals("y") || input.equals("n")) checkInput = true;
            }
        }
    }

    static void buyDemo(Market market) {
        CommandExecutor executor = market.getExecutor();
        ArrayList<GameFactory> cart = ((User)market.getLoggedInUser()).getCart();
        UserWallet wallet = new UserWallet(1000);
        User user = (User)market.getLoggedInUser();
        String input = "";
        boolean isBuying = true;

        // select game to buy in cart
        while(isBuying){
            boolean check = false;
            int menuSelected = -1;
            check = false;
            while(!check) {
                InputLocgic.clearScreen();
                System.out.println("User's cart");
                Ui.cartPage(user);
                System.out.print("Selecte game to buy or n to exit: ");
                input = InputLocgic.getInput(false);
                if(input.equals("n")) {
                    check = true;
                    isBuying = false;
                } 
                if(InputLocgic.integerPrasingGard(input)) {
                    menuSelected = Integer.parseInt(input);
                    if(!(menuSelected > cart.size() || menuSelected < 1)) check = true;
                }
            }
            InputLocgic.clearScreen();
            if(!input.equals("n")) {
                executor.executeCommand(new Buy(cart.get(menuSelected-1), user, wallet));
                check = false;
                if(!cart.isEmpty()) {
                    while(!check) {
                        System.out.print("\nBuy more in your cart?(y/n): ");
                        input = InputLocgic.getInput(false).toLowerCase();
                        if(input.equals("y") || input.equals("n")) check = true;
                    }
                    if(input.equals("n")) isBuying = false;
                } else isBuying = false;
            } 
        }
    }

    // Demo for user: show cart/ownedGame -> select game to cart/show cart -> buy -> show ownedGame
    static void userDemo(Market market) {
        User user = (User)market.getLoggedInUser();

        // Show cart/ownedGame
        InputLocgic.clearScreen();
        Ui.ownedGamePage(user);
        System.out.println();
        Ui.cartPage(user);
        System.out.println("\nPress enter to continue...");
        InputLocgic.getInput(false);

        selectGameDemo(market);
        buyDemo(market);
        
        // show ownedGame
        Ui.ownedGamePage(user);
    }

    static void publisherDemo(Market market) {
        System.out.println("Welcome publisher");
    }

    public static void main(String[] args) {
        Market market = new Market(init());
        while(true) {
            authentication(market);
            if(market.getLoggedInUser() instanceof User) {
                userDemo(market);
            } else {
                publisherDemo(market);
            }

            // Show all transaction 
            System.out.println();
            market.getExecutor().printHistoryCommand();
            System.out.println("Press enter to continue...");
            InputLocgic.getInput(false);
        }
    }
}