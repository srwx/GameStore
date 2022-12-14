package example.ui;

import java.util.ArrayList;
import java.util.HashMap;

import game.Dlc;
import game.Game;
import game.GameFactory;

public class PublisherUi {
    
    public static int publisherMainPage(ArrayList<Game> game, String username) {
        String input = "";
        boolean isInputCorrected = false;
        int menuSelected = -1;
        int i = -1;
        boolean hasGame = game.isEmpty();

        while(!isInputCorrected) {
            InputLogic.clearScreen();
            System.out.println("\n============ Welcome, " + username + " ============");
            System.out.println("Please select an action\n");
            System.out.println("1.) Create a game to publish");
            System.out.println("2.) View History");
            if(!hasGame) {
                System.out.println("\nSelect game to edit");
                for(i = 0; i < game.size(); i++) {
                    System.out.println((i+3) + ".) " + game.get(i).getName());
                }
            } 
            System.out.println((!hasGame) ? "\n0.) Exit" : "0.) Exit");
            System.out.print("\nYour action: ");
            input = InputLogic.getInput(true);
            menuSelected = Integer.parseInt(input);
            if(!(menuSelected > game.size() + 3 || menuSelected < 0)) isInputCorrected = true;
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
            while(name == "") {
                System.out.print(type + " name: ");
                name = InputLogic.getInput(false);
            }
            while(description == "") {
                System.out.print(type + " description: ");
                description = InputLogic.getInput(false);
            }
            if(!isDlc) {
                while(category == "") {
                    System.out.print(type + " category: ");
                    category = InputLogic.getInput(false);
                }
            }
            while(price == -1) {
                System.out.print(type + " price: ");
                input = InputLogic.getInput(false);
                if(!input.isEmpty()) {
                    if(InputLogic.doublePrasingGard(input)) price = Double.parseDouble(input);
                }
            }
            boolean isInputCorrected = false;
            while(!isInputCorrected) {
                InputLogic.clearScreen();
                System.out.println(type + " name: " + name);
                System.out.println(type + " description: " + description);
                if(!isDlc) System.out.println("Game category: " + category);
                System.out.println(type + " price: " + price);
                System.out.print("\nConfirm? (y/n): ");
                input = InputLogic.getInput(false).toLowerCase();
                if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                    isInputCorrected = true;
                    isCreating = false;
                } 
            }
        }
        if(input.equalsIgnoreCase("y")) {
            return (!isDlc) ? new Game(name, description, price, category) : new Dlc(name, description, price, gameId);
        } else {
            return (!isDlc) ? new Game() : new Dlc();
        }
    }

    public static int gameOptionMenu(Game game) {
        String input = "";
        int menuSelected = -1;
        boolean isInputCorrected = false;
        

        while(!isInputCorrected) {
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
            if(!(menuSelected > 5 || menuSelected < 1)) isInputCorrected = true;
        }
        return menuSelected;
    }

    public static HashMap<String, String> updatePage(GameFactory game) {
        String input = "";
        boolean isInputCorrected = false;
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
            while(price == -1) {
                System.out.print(type + " price: ");
                input = InputLogic.getInput(false);
                if(!input.isEmpty()) {
                    if(InputLogic.doublePrasingGard(input)) price = Double.parseDouble(input);
                } else {
                    price = game.getPrice();
                }
            }

            boolean nameChange = (!name.isEmpty() && !name.equals(game.getName()));
            Boolean desChange = (!description.isEmpty() && !description.equals(game.getDescription()));
            boolean priceChange = (price != game.getPrice());
            while(!isInputCorrected) {
                input = "";
                InputLogic.clearScreen();
                if(nameChange) {
                    System.out.println(type + " name: " + name);
                    detail.put("name", name);
                } 
                if(desChange) {
                    System.out.println(type + " description: " + description);
                    detail.put("description", description);
                } 
                if(priceChange) {
                    System.out.println(type + " price: " + price);
                    detail.put("price", String.valueOf(price));
                }
                if(!(nameChange || desChange || priceChange))  {
                    System.out.println("You didn't change anything, press enter to continue...");
                    InputLogic.getInput(false);
                    isInputCorrected = true;
                    isEditing = true;
                } else {
                    System.out.print("\nConfirm? (y/n): ");
                    input = InputLogic.getInput(false).toLowerCase();
                    if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
                        isEditing = true;
                        isInputCorrected = true;
                    }
                }
            }
        }
        if(input.equalsIgnoreCase("y")) {
            return detail;
        } else {
            return null;
        }
    }

    public static Dlc dlcListPage(ArrayList<Dlc> dlc) {
        String input = "";
        boolean isInputCorrected = false;
        int menuSelected = -1;
        int i = -1;

        while(!isInputCorrected) {
            if(!dlc.isEmpty()) {
                InputLogic.clearScreen();
                System.out.println("Please select a DLC to edit\n");
                for(i = 0; i < dlc.size(); i++) {
                    System.out.println((i+1) + ".) " + dlc.get(i).getName());
                }
                System.out.println("0.) Back");
                System.out.print("\nYour action: ");
                input = InputLogic.getInput(true);
                menuSelected = Integer.parseInt(input);
                if(!(menuSelected > dlc.size() || menuSelected < 0)) isInputCorrected = true;
            } else {
                InputLogic.clearScreen();
                System.out.println("No DLCs in this game\n");
                System.out.println("Press enter to continue");
                InputLogic.getInput(false);
                menuSelected = 0;
                isInputCorrected = true;
            }
            
        } 
        return (menuSelected == 0) ? new Dlc() : dlc.get(menuSelected-1);
    }
}
