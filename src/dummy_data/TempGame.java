package dummy_data;

import java.util.ArrayList;

import game.*;

// This class used for generate dummy data of game
public class TempGame {
    public static Game temp1 = new Game("GameName1", "GameDescription1", 100, "GameCat1");
    public static Game temp2 = new Game("GameName2", "GameDescription2", 200, "GameCat2");
    public static Game temp3 = new Game("GameName3", "GameDescription3", 300, "GameCat3");

    public static Dlc dlc1 = new Dlc("DlcName1", "DlcDescription1", 50, temp1.getId());
    public static Dlc dlc2 = new Dlc("DlcName2", "DlcDescription2", 60, temp2.getId());
    public static Dlc dlc3 = new Dlc("DlcName3", "DlcDescription3", 70, temp3.getId());

    public static ArrayList<Game> gameList = new ArrayList<Game>();

    public static ArrayList<Game> getGameList() {
        // Add DLCs to game
        temp1.addExtension(dlc1);
        temp1.addExtension(dlc2);
        temp2.addExtension(dlc3);

        gameList.add(temp1);
        gameList.add(temp2);
        gameList.add(temp3);

        return gameList;
    }
}
