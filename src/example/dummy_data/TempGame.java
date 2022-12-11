package example.dummy_data;

import game.*;
import user.*;

// This class used for generate dummy data of game
public class TempGame {
    public static Game temp1 = new Game("Assassin's Creed Valhalla", "Become a legendary Viking on a quest for glory. Raid your enemies and build your political power.", 230, "Action");
    public static Game temp2 = new Game("Divine Knockout (DKO)", "KO the gods in the world’s only 3rd-person platform fighter!", 180, "Action");
    public static Game temp3 = new Game("Need for Speed™ Unbound", "Race to the top, definitely don’t flop. Outsmart the cops, and enter ultimate street race.", 470, "Racing");

    public static Dlc dlc1 = new Dlc("Ragnarok Edition", "Ragnarok DLC for Assassin's Creed Valhalla", 80, temp1.getId());
    public static Dlc dlc2 = new Dlc("Complete Edition", "Fully DLC bundle for Assassin's Creed Valhalla", 120, temp1.getId());
    public static Dlc dlc3 = new Dlc("Unbound Palace Edition", "Palace extension for Need for Speed™ Unbound", 180, temp3.getId());

    public static Publisher publisher1 = new Publisher("Publisher1");
    public static Publisher publisher2 = new Publisher("Publisher2");
    public static Publisher publisherDemo = new Publisher("publisher");


    public static void init() {
        // Add DLCs to game
        temp1.addExtension(dlc1);
        temp1.addExtension(dlc2);
        temp3.addExtension(dlc3);

        // Add games to publisher
        publisher1.addGame(temp1);
        publisher1.addGame(temp2);
        publisher2.addGame(temp3);
    }
}
