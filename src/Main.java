
import java.util.ArrayList;
import java.util.HashMap;

import command.AddDlc;
import command.AddToCart;
import command.Buy;
import command.CommandExecutor;
import command.Create;
import command.Delete;
import command.Update;
import example.dummy_data.TempGame;
import example.ui.InputLogic;
import game.Dlc;
import game.Game;
import game.GameFactory;
import payment.UserWallet;
import example.ui.Ui;
import example.ui.PublisherUi;
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
        UserFactory user = market.getLoggedInUser();

        menuSelected = Ui.cover();
        if(menuSelected == 1) {
            // Create an account
            HashMap<String, String> detail = Ui.createUserPage();
            String username = detail.get("username");
            String role = detail.get("role");
            UserFactory dummyUser = new UserFactory();
            dummyUser = market.createUser(username, role == "user" ? false : true);
            market.login(dummyUser.getUsername(), dummyUser instanceof Publisher);
            if(dummyUser instanceof Publisher) market.addPublisher((Publisher)market.getLoggedInUser());
        } 
        else if(menuSelected == 2) {
            // Login
            String username = Ui.loginPage();
            // In this demo will query user form logged in user of market if system has this user,
            // otherwise will create an new instance
            if(user == null || !user.getUsername().equalsIgnoreCase(username)) {
                if(username.equals("publisher")) {
                    market.login(username, true);
                    market.addPublisher((Publisher)market.getLoggedInUser());
                } 
                else market.login(username, false);
            }
        } else if(menuSelected == 3) {
            // Exit program
            InputLogic.closeScanner();
            System.exit(0);
        }
    } 

    /////////////////////////////////////////////// User/////////////////////////////////////////////
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
                System.out.print("> Do you want to add any dlc to cart? (select by command dlc[number] or n if you don't): ");
                input = InputLogic.getInput(false).toLowerCase();
                if(input.equalsIgnoreCase("n") || dlcOption.contains(input)) checkInput = true;
            }
            if(!input.equalsIgnoreCase("n")) {
                String index = Character.toString(input.charAt(3));
                int dlcIndex = Integer.parseInt(index)-1;
                Dlc selectedDlc = dlc.get(dlcIndex);
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
            if(selectedGame.getId() == null) break; // Back to menu
            game = Ui.gameDetailPage((Game)selectedGame); // return object game to add to cart
            if(game.getId() != null && !cart.contains(game)) {
                executor.executeCommand(new AddToCart(game, user));
            } 
            dlc = ((Game)selectedGame).getExtension();
            // Ask user to add any dlc of the selected game to cart
            selectDlcDemo(market, dlc);
            
            checkInput = false;
            while(!checkInput) {
                System.out.print("> Add more game?(y/n): ");
                input = InputLogic.getInput(false).toLowerCase();
                if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) checkInput = true;
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
            while(!check) {
                InputLogic.clearScreen();
                Ui.cartPage(user);
                System.out.print("> Select game to buy (or n to back to menu): ");
                input = InputLogic.getInput(false);
                if(input.equalsIgnoreCase("n")) {
                    check = true;
                    isBuying = false;
                } 
                if(InputLogic.integerPrasingGard(input)) {
                    menuSelected = Integer.parseInt(input);
                    if(!(menuSelected > cart.size() || menuSelected < 1)) check = true;
                }
            }
            InputLogic.clearScreen();
            if(!input.equalsIgnoreCase("n")) {
                executor.executeCommand(new Buy(cart.get(menuSelected-1), user, wallet));
                check = false;
                if(!cart.isEmpty()) {
                    while(!check) {
                        System.out.print("\n> Buy more in your cart?(y/n): ");
                        input = InputLogic.getInput(false).toLowerCase();
                        if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) check = true;
                    }
                    if(input.equalsIgnoreCase("n")) isBuying = false;
                } else isBuying = false;
            }
        }
    }

    // Demo for user: show cart/ownedGame -> select game to cart/show cart -> buy -> show ownedGame
    static void userDemo(Market market) {
        User user = (User)market.getLoggedInUser();
        String userSelect = "";
        InputLogic.clearScreen();
        System.out.println("\n============ Welcome, " + user.getUsername() + " ============");
        boolean isLogin = true;

        while (isLogin) {
            // Menu to show
            System.out.println("\nWhat you need to do?");
            System.out.println("\t1.) View owned game & cart");
            System.out.println("\t2.) View game market");
            System.out.println("\t3.) Buy game(s) in cart");
            System.out.println("\t4.) View command history");
            System.out.println("\t5.) Log out");
            System.out.print("\n> Select (1-5): ");
            userSelect = InputLogic.getInput(true);
            System.out.println();
            switch (userSelect) {
                case ("1"):
                    Ui.ownedGamePage(user);
                    Ui.cartPage(user);
                    System.out.println("\n*****************************************");
                    break;
                case ("2"):
                    selectGameDemo(market);
                    System.out.println("\n*****************************************");
                    break;
                case ("3"):
                    buyDemo(market);
                    System.out.println("\n*****************************************");
                    break;
                case ("4"):
                    market.getExecutor().printHistoryCommand();
                    break;
                case ("5"):
                    // TODO: log out
                    market.logout();
                    isLogin = false;
                    break;
                default:
                    System.out.println("Error, Please enter menu's number (1, 2, 3, 4, 5)");
            }
        }
    }

    //////////////////////////////// Publisher /////////////////////////////////////////////////
    static void createGameDemo(Publisher user, CommandExecutor executor) {
        Game newGame = (Game)PublisherUi.createPage(false, "");
        executor.executeCommand(new Create(newGame, user));
    }

    static void gameOptionDemo(Publisher user, Game game, CommandExecutor executor) {
        int selected = PublisherUi.gameOptionMenu(game);
        if(selected == 1) {
            HashMap<String, String> detail = PublisherUi.updatePage(game);
            executor.executeCommand(new Update(game, user, detail));
        } else if(selected == 2) {
            Dlc dlc = PublisherUi.dlcListPage(game.getExtension());
            if(dlc.getId() != null) {
                HashMap<String, String> detail = PublisherUi.updatePage(dlc);
                executor.executeCommand(new Update(dlc, user, detail));
            }
        } else if(selected == 3) {
            Dlc newDlc = (Dlc)PublisherUi.createPage(true, game.getId());
            executor.executeCommand(new AddDlc(game, user, newDlc));
        } else if(selected == 4) {
            boolean check = false;
            String input = "";
            while(!check) {
                System.out.print("Confirm delete (y/n): ");
                input = InputLogic.getInput(false);
                if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) check = true;
            }
            if(input.equalsIgnoreCase("y")) executor.executeCommand(new Delete(game, user));
        }
    }

    // Demo for publisher: show list of game -> create game-> add dlc to game -> delete game 
    static void publisherDemo(Market market) {
        Publisher user = (Publisher)market.getLoggedInUser();
        CommandExecutor executor = market.getExecutor();
        while(true) {
            int menuSelected = PublisherUi.publisherMainPage(user.getOwnedGames(), user.getUsername());
            if(menuSelected == 0) break; // Exit to authentication page
            if(menuSelected > 2) {
                // UI edit/delete/add dlc... for a game
                gameOptionDemo(user, user.getOwnedGames().get(menuSelected-3), executor);
            } 
            if(menuSelected == 1) {
                // Go to form create game
                createGameDemo(user, executor);
            }
            if(menuSelected == 2) {
                // View history
                Ui.printHistory(executor);
            }
        }
        InputLogic.clearScreen();
        market.logout();
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
        }
    }
}