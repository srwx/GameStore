package example.ui;

import java.util.ArrayList;
import java.util.HashMap;

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
            InputLocgic.clearScreen();
            System.out.println("Welcome to Game Store\n");
            System.out.println("Please select an action:");
            System.out.println("1.) Create an account");
            System.out.println("2.) Log in to your account");
            System.out.println("3.) Exit");
            System.out.print("Your action: ");
            input = InputLocgic.getInput(true);
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
            InputLocgic.clearScreen();
            System.out.println("Select your role by number");
            System.out.println("1.) User");
            System.out.println("2.) Publisher");
            System.out.print("Your are: ");
            input = InputLocgic.getInput(true);
            if(menu.contains(input)) check = true;
        }
        // true is user, false is publisher
        return input.equals(menu.get(0)) ? true : false;
    }

    public static String loginPage() {
        String input = "";

        InputLocgic.clearScreen();
        System.out.println("Log in to ypur account\n");

        ////////////////////////////////////// Demo ////////////////////////////////////////
        System.out.println("For demo application");
        System.out.println("please enter username as\'publisher\' to login as publisher");
        System.out.println("otherwise will be an user role");
        ////////////////////////////////////////////////////////////////////////////////////

        System.out.print("Enter your username: ");
        input = InputLocgic.getInput(false);
        return input;
    }

    public static HashMap<String, String> createUserPage() {
        String input = "";
        boolean isUser = true;

        InputLocgic.clearScreen();
        System.out.println("Create an account to Get a new Game!\n");
        System.out.print("Enter your username: ");
        input = InputLocgic.getInput(false);
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

        while(!check) {
            InputLocgic.clearScreen();
            System.out.println("All games in our store");
            System.out.println("Select game to see detail by number\n");
            for(int i = 0; i < gameNumber; i++) {
                System.out.println(i+1);
                games.get(i).printDetail();
            }
            System.out.print("Select game: ");
            input = InputLocgic.getInput(true);
            if(InputLocgic.integerPrasingGard(input)) {
                int menuSelected = Integer.parseInt(input);
                if(!(menuSelected > gameNumber || menuSelected < 0)) check = true;
            }  
        }
        return games.get(Integer.parseInt(input)-1);
    }

    public static Game gameDetailPage(Game game) {
        String input = "";
        int i = 0;
        boolean check = false;

        while(!check) {
            InputLocgic.clearScreen();
            ArrayList<Dlc> dlc = game.getExtension();
            game.printDetail();
            if(!dlc.isEmpty()) {
                for (i = 0; i < dlc.size(); i++) {
                    System.out.println("dlc" + (i+1));
                    dlc.get(i).printDetail();
                }
            }
            System.out.print("Add game to cart(y/n): ");
            input = InputLocgic.getInput(false).toLowerCase();
            if(input.equals("y") || input.equals("n")) check = true;
        }
        return input.equals("y") ? game : new Game();
    }

    public static Dlc dlcDetailPage(Dlc dlc) {
        String input = "";
        boolean check = false;

        while(!check) {
            InputLocgic.clearScreen();
            dlc.printDetail();
            System.out.print("Add dlc to cart(y/n): ");
            input = InputLocgic.getInput(false).toLowerCase();
            if(input.equals("y") || input.equals("n")) check = true;
        }
        return input.equals("y") ? dlc : new Dlc();
    }

    public static void cartPage(User user) {
        ArrayList<GameFactory> game = user.getCart();
        System.out.println("Show user's cart");
        if(game.size() == 0) {
            System.out.println("No game in cart");
        }
        else {
            for (int i = 0; i < game.size(); i++) {
                System.out.println((i+1) + ".) " + game.get(i).getName());
            }
        }
    }

    public static void ownedGamePage(User user) {
        ArrayList<Game> game = user.getOwnedGames();
        System.out.println("Show user's game");
        if(game.size() == 0) {
            System.out.println("No game in storage");
        }
        else {
            for (int i = 0; i < game.size(); i++) {
                System.out.println((i+1) + ".) " + game.get(i).getName());
            }
        }
    }
}
