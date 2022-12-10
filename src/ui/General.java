package ui;
import java.util.ArrayList;
import java.util.HashMap;

import game.Dlc;
import game.Game;
import game.GameFactory;

public class General {

    public static void cover() {
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

    public static HashMap<String, String> authorizationPage(boolean toLoginPage) {
        String input = "";
        boolean isUser = true;

        InputLocgic.clearScreen();
        if(toLoginPage) System.out.println("Log in to ypur account\n");
        else System.out.println("Create an account to Get a new Game!\n");
        System.out.print("Enter your username: ");
        input = InputLocgic.getInput(false);
        if(!toLoginPage) isUser = getUserRole();

        HashMap<String, String> user = new HashMap<String, String>();
        user.put("username", input);
        user.put("role", isUser == true ? "user" : "publisher");
        return user;
    }

    public static GameFactory home(ArrayList<GameFactory> games) {
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
            int menuSelected = Integer.parseInt(input);
            if(!(menuSelected >= gameNumber || menuSelected < 1)) check = true;
        }
        return games.get(Integer.parseInt(input));
    }

    public static Game gameDetailPage(Game game) {
        String input = "";
        boolean check = false;

        while(!check) {
            InputLocgic.clearScreen();
            ArrayList<Dlc> dlc = game.getExtension();
            game.printDetail();
            if(!dlc.isEmpty()) {
                for (Dlc item : dlc) {
                    item.printDetail();
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
}
