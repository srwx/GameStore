package ui;

import java.util.Scanner;

public class InputLocgic {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInput(boolean isMenuSelection) {
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

    public static void closeScanner() {
        scanner.close();
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static void inputError() {
        System.out.println("Invalid input, please try again");
    }
}
