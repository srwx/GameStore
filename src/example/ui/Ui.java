package example.ui;

import java.util.ArrayList;
import java.util.HashMap;

import command.CommandExecutor;
import game.Dlc;
import game.Game;
import game.GameFactory;
import user.User;

public class Ui {

    public static int cover() {
        String input = "";
        ArrayList<String> menu = new ArrayList<String>() {
            {
                add("1");
                add("2");
                add("3");
            }
        };
        boolean check = false;

        while(!check) {
            InputLogic.clearScreen();
            System.out.println("Welcome to Game Store\n");
            System.out.println("Please select an action:");
            System.out.println("\t1.) Create an account");
            System.out.println("\t2.) Log in to your account");
            System.out.println("\t3.) Exit");
            System.out.print("\n> Your action: ");
            input = InputLogic.getInput(true);
            if(menu.contains(input)) check = true;
        }
        return Integer.parseInt(input);
    }

    public static boolean getUserRole() {
        String input = "";
        ArrayList<String> menu = new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        };
        boolean check = false;

        while(!check) {
            InputLogic.clearScreen();
            System.out.println("Select your role by number");
            System.out.println("\t1.) User");
            System.out.println("\t2.) Publisher");
            System.out.print("\n> Your are: ");
            input = InputLogic.getInput(true);
            if(menu.contains(input)) check = true;
        }
        // true is user, false is publisher
        return input.equalsIgnoreCase(menu.get(0)) ? true : false;
    }

    public static HashMap<String, String> loginPage() {
        String input = "";
        boolean isUser;

        isUser = getUserRole();
        System.out.println("Log in to your account\n");

        ////////////////////////////////////// Demo ////////////////////////////////////////
        // System.out.println("For demo application");
        // System.out.println("please enter username as\'publisher\' to login as publisher");
        // System.out.println("otherwise will be an user role");
        ////////////////////////////////////////////////////////////////////////////////////

        InputLogic.clearScreen();
        System.out.print("Enter your username: ");
        input = InputLogic.getInput(false);
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("username", input);
        user.put("role", isUser == true ? "user" : "publisher");
        return user;
    }

    public static HashMap<String, String> createUserPage() {
        String input = "";
        boolean isUser = true;

        InputLogic.clearScreen();
        System.out.print("> Enter your username: ");
        input = InputLogic.getInput(false);
        isUser = getUserRole();

        HashMap<String, String> user = new HashMap<String, String>();
        user.put("username", input);
        user.put("role", isUser == true ? "user" : "publisher");
        return user;
    }

    public static GameFactory home(ArrayList<Game> games) {
        String input = "";
        boolean check = false;
        int gameNumber = games.size();
        int menuSelected = -1;

        while(!check) {
            InputLogic.clearScreen();
            System.out.println("All games in our store");
            System.out.println("Select game to see detail by number\n");
            for(int i = 0; i < gameNumber; i++) {
                System.out.println(i+1);
                games.get(i).printDetail();
            }
            System.out.print("> Enter game's number to view game information (or enter 0 to back to menu): ");
            input = InputLogic.getInput(true);
            menuSelected = Integer.parseInt(input);
            if(!(menuSelected > gameNumber || menuSelected < 0)) check = true;
            System.out.println();
        }
        return (menuSelected != 0) ? games.get(Integer.parseInt(input)-1) : new Game();
    }

    public static Game gameDetailPage(Game game) {
        String input = "";
        int i = 0;
        boolean check = false;

        while(!check) {
            InputLogic.clearScreen();
            ArrayList<Dlc> dlc = game.getExtension();
            game.printDetail();
            if(!dlc.isEmpty()) {
                System.out.println("=================== DLC List ===================\n");
                for (i = 0; i < dlc.size(); i++) {
                    System.out.println("dlc" + (i+1));
                    dlc.get(i).printDetail();
                }
            }
            System.out.print("> Add " + game.getName() + " to cart(y/n): ");
            input = InputLogic.getInput(false).toLowerCase();
            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) check = true;
        }
        return input.equalsIgnoreCase("y") ? game : new Game();
    }

    public static Dlc dlcDetailPage(Dlc dlc) {
        String input = "";
        boolean check = false;

        while(!check) {
            InputLogic.clearScreen();
            dlc.printDetail();
            System.out.print("> Add " + dlc.getName() + " to cart(y/n): ");
            input = InputLogic.getInput(false).toLowerCase();
            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) check = true;
        }
        return input.equalsIgnoreCase("y") ? dlc : new Dlc();
    }

    public static void cartPage(User user) {
        ArrayList<GameFactory> game = user.getCart();
        System.out.print("\uD83D\uDED2 " + user.getUsername() + "'s cart: ");
        if(game.size() == 0) {
            System.out.println("-");
        }
        else {
            System.out.println();
            for (int i = 0; i < game.size(); i++) {
                System.out.println("\t" + (i+1) + ".) " + game.get(i).getName());
            }
            System.out.println();
        }
    }

    public static void ownedGamePage(User user) {
        ArrayList<Game> game = user.getOwnedGames();
        System.out.print("\u2705 " + user.getUsername() + "'s owned game: ");
        if(game.size() == 0) {
            System.out.println("-");
        }
        else {
            System.out.println();
            for (int i = 0; i < game.size(); i++) {
                System.out.println("\t" + (i+1) + ".) " + game.get(i).getName());
                ArrayList<Dlc> dlcs = game.get(i).getExtension();
                for (int j = 0; j < dlcs.size(); j++) {
                    System.out.println("\t    " + (i+1) + "." + (j+1) + ") " + dlcs.get(j).getName());
                }
            }
            System.out.println();
        }
    }
    
    public static void printHistory(CommandExecutor executor) {
        InputLogic.clearScreen();
        executor.printHistoryCommand();
        System.out.println("Press enter to continue...");
        InputLogic.getInput(false);
    }

    public static int paymentSelectionPage() {
        String input = "";
        ArrayList<String> menu = new ArrayList<String>() {
            {
                add("0");
                add("1");
                add("2");
                add("3");
            }
        };
        boolean check = false;

        while(!check) {
            InputLogic.clearScreen();
            System.out.println("Select payment method");
            System.out.println("\t1.) Online banking");
            System.out.println("\t2.) Credit card");
            System.out.println("\t3.) Store wallet");
            System.out.println("\nPress 0 to back to menu");
            System.out.print("\n> Your action: ");
            input = InputLogic.getInput(true);
            if(menu.contains(input)) check = true;
        }
        return Integer.parseInt(input);
    }
}
