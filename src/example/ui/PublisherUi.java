package example.ui;

import java.util.ArrayList;
import java.util.HashMap;

import game.Dlc;
import game.Game;
import game.GameFactory;

public class PublisherUi {
    
    public static int publisherMainPage(ArrayList<Game> game, String username) {
        String input = "";
        boolean check = false;
        int menuSelected = -1;
        int i = -1;
        boolean hasGame = game.isEmpty();

        while(!check) {
            InputLogic.clearScreen();
            System.out.println("\n============ Welcome, " + username + " ============");
            System.out.println("Please select an action\n");
            System.out.println("1.) Create a game to publish");
            if(!hasGame) {
                System.out.println("\nSelect game to edit");
                for(i = 0; i < game.size(); i++) {
                    System.out.println((i+2) + ".) " + game.get(i).getName());
                }
                // System.out.println("\n" + (i+2) + ".) Exit");
            } 
            System.out.println((!hasGame) ? "\n0.) Exit" : "0.) Exit");
            System.out.print("\nYour action: ");
            input = InputLogic.getInput(true);
            menuSelected = Integer.parseInt(input);
            if(!(menuSelected > game.size() + 2 || menuSelected < 0)) check = true;
        } 
        return menuSelected;
    }

    public static GameFactory createPage(boolean isDlc, String gameId) {
        String name = "";
        String description = "";
        String category = "";
        double price = -1;
        String input = "";
        String type = (isDlc) ? "DLC" : "Game";
        boolean isCreating = true;

        while(isCreating) {
            InputLogic.clearScreen();
            System.out.println("Add your " + type + "!\n");
            System.out.print(type + " name: ");
            name = InputLogic.getInput(false);
            System.out.print(type + " description: ");
            description = InputLogic.getInput(false);
            if(!isDlc) {
                System.out.print(type + " category: ");
                category = InputLogic.getInput(false);
            }
            while(price == -1) {
                System.out.print(type + " price: ");
                input = InputLogic.getInput(true);
                price = Double.parseDouble(input);
            }
            boolean check = false;
            while(!check) {
                InputLogic.clearScreen();
                System.out.println(type + " name: " + name);
                System.out.println(type + " description: " + description);
                if(!isDlc) System.out.println("Game category: " + category);
                System.out.println(type + " price: " + price);
                System.out.print("\nConfirm? (y/n): ");
                input = InputLogic.getInput(false).toLowerCase();
                if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) check = true;
            }
            if(input.equalsIgnoreCase("y"))  isCreating = false;
        }
        return (!isDlc) ? new Game(name, description, price, category) : new Dlc(name, description, price, gameId);
    }

    public static int gameOptionMenu(Game game) {
        String input = "";
        int menuSelected = -1;
        boolean check = false;
        

        while(!check) {
            InputLogic.clearScreen();
            System.out.println("Your game detail");
            game.printDetail();
            System.out.println();
            System.out.println("Select your action");
            System.out.println("1.) Edit game detail");
            System.out.println("2.) Edit game's DLC");
            System.out.println("3.) Add game's DLC");
            System.out.println("4.) Delete game");
            System.out.println("5.) Back");
            System.out.print("Your action: ");
            input = InputLogic.getInput(true);
            menuSelected = Integer.parseInt(input);
            if(!(menuSelected > 5 || menuSelected < 1)) check = true;
        }
        return menuSelected;
    }

    public static HashMap<String, String> updatePage(GameFactory game) {
        String input = "";
        boolean check = false;
        String name = "";
        String description = "";
        double price = -1;
        boolean isEditing = false;
        String type = (game instanceof Game) ? "Game" : "DLC";
        HashMap<String, String> detail = new HashMap<String, String>();

        while(!isEditing) {
            InputLogic.clearScreen();
            game.printDetail();
            System.out.println();
            System.out.println("Enter your detail (press enter to skip a topic you don't want to change)");
            System.out.print(type + " name: ");
            name =  InputLogic.getInput(false);
            System.out.print(type + " description: ");
            description =  InputLogic.getInput(false);
            System.out.print(type + " price: ");
            input =  InputLogic.getInput(true);
            if(!input.isEmpty()) price = Double.parseDouble(input);

            while(!check) {
                InputLogic.clearScreen();
                if(!name.isEmpty()) {
                    System.out.println(type + " name: " + name);
                    detail.put("name", name);
                } 
                if(!description.isEmpty()) {
                    System.out.println(type + " description: " + description);
                    detail.put("description", description);
                } 
                if(price != -1) {
                    System.out.println(type + " price: " + price);
                    detail.put("price", String.valueOf(price));
                } 
                System.out.print("\nConfirm? (y/n): ");
                input = InputLogic.getInput(false).toLowerCase();
                if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) check = true;
            }
            if(input.equalsIgnoreCase("y"))  isEditing = true;
        }
        return detail;
    }

    public static Dlc dlcListPage(ArrayList<Dlc> dlc) {
        String input = "";
        boolean check = false;
        int menuSelected = -1;
        int i = -1;

        while(!check) {
            if(!dlc.isEmpty()) {
                InputLogic.clearScreen();
                System.out.println("Please select a DLC to edit\n");
                for(i = 0; i < dlc.size(); i++) {
                    System.out.println((i+1) + ".) " + dlc.get(i).getName());
                }
                System.out.println((i+1) + ".) Back");
                System.out.print("\nYour action: ");
                input = InputLogic.getInput(true);
                menuSelected = Integer.parseInt(input);
                if(!(menuSelected > dlc.size() + 2 || menuSelected < 1)) check = true;
            } else {
                InputLogic.clearScreen();
                System.out.println("No DLCs in this game\n");
                System.out.println("Press enter to continue");
                InputLogic.getInput(false);
                menuSelected = 2;
                check = true;
            }
            
        } 
        return (menuSelected == dlc.size() + 2) ? new Dlc() : dlc.get(menuSelected-1);
    }
}
