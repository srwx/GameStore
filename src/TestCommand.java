import command.*;
import game.Dlc;
import game.Game;
import user.Publisher;

import java.util.HashMap;

public class TestCommand {
    public static void main(String[] args) {
        Game temp1 = new Game("GameName1", "GameDescription1", 100, "GameCat1");
        Game temp2 = new Game("GameName1", "GameDescription1", 100, "GameCat1");
        Publisher pub1 = new Publisher("yo pub1");
        CommandExecutor executor = new CommandExecutor();

        // Add game
        System.out.println("Before add game: " + pub1.getOwnedGames());
        executor.executeCommand(new Create(temp1, pub1));
        System.out.println("======== After add game ========");
        pub1.getOwnedGames().get(0).printDetail();

        // Add DLC
        Dlc dlc1 = new Dlc("DlcName1", "DlcDescription1", 50, temp1.getId());
        Dlc dlc2 = new Dlc("DlcName2", "DlcDescription2", 60, temp1.getId());
        executor.executeCommand(new AddDlc(temp2, pub1, dlc1));
        executor.executeCommand(new AddDlc(temp1, pub1, dlc2));
        System.out.println("======== After add DLC ========");
        pub1.getOwnedGames().get(0).printDetail();

        // Update game
        HashMap<String, String> detail = new HashMap<String, String>();
        detail.put("name", "yoyoyo");
        detail.put("description", "yahooo!");
        detail.put("price", "9999999");
        executor.executeCommand(new Update(temp2, pub1, detail));
        System.out.println("======== After update game ========");
        pub1.getOwnedGames().get(0).printDetail();

        // Update DLC
        HashMap<String, String> dlcDetail = new HashMap<String, String>();
        dlcDetail.put("name", "yayy new dlc name");
        executor.executeCommand(new Update(dlc1, pub1, dlcDetail));
        System.out.println("======== After update DLC ========");
        pub1.getOwnedGames().get(0).printDetail();

        // Delete game
        System.out.println("Before delete game: " + pub1.getOwnedGames());
        executor.executeCommand(new Delete(temp1, pub1));
        System.out.println("After delete game: " + pub1.getOwnedGames());
    }
}
