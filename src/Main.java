import DummyData.TempGame;
import Game.*;

import java.util.ArrayList;

public class Main {
    public static void viewGameList(ArrayList<Game> games) {
        System.out.println("List of games:");
        for (int i = 0; i < games.size(); i++) {
            System.out.println((i+1) + ".) " + games.get(i).getName());
        }
    }

    public static void main(String[] args) {
        ArrayList<Game> games = TempGame.getGameList(); // Init game list from DummyData package
        viewGameList(games);
    }
}