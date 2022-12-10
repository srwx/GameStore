import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import dummy_data.TempGame;
import ui.General;
import user.Publisher;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    static ArrayList<Publisher> init() {
        TempGame.init();
        ArrayList<Publisher> publishersList = new ArrayList<Publisher>();
        publishersList.add(TempGame.publisher1);
        publishersList.add(TempGame.publisher2);
        return publishersList;
    }

    public static String getInput(Scanner scanner ,boolean isMenuSelection) {
        if(!isMenuSelection) {
            return scanner.nextLine();
        } 
        try {
            int selected = Integer.parseInt(scanner.nextLine());
            return String.valueOf(selected);
        } catch (Exception e) {
            return String.valueOf(-1);
        } 
    }

    public static void main(String[] args) {
        Market market = new Market(init());
        Stack<String> router = new Stack<String>();
    }
}