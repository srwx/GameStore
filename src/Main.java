import java.util.ArrayList;
import java.util.HashMap;

import dummy_data.TempGame;
import ui.General;
import user.Publisher;
import user.UserFactory;

public class Main {

    static ArrayList<Publisher> init() {
        TempGame.init();
        ArrayList<Publisher> publishersList = new ArrayList<Publisher>();
        publishersList.add(TempGame.publisher1);
        publishersList.add(TempGame.publisher2);
        return publishersList;
    }

    public static void main(String[] args) {
        Market market = new Market(init());
        int menuSelected = -1;
        UserFactory dummyUser = new UserFactory();

        menuSelected = General.cover();
        if(menuSelected == 1) {
            // Create an account
            HashMap<String, String> detail = General.createUserPage();
            String username = detail.get("username");
            String role = detail.get("role");
            dummyUser = market.createUser(username, role == "user" ? false : true);
            market.login(dummyUser.getUsername(), dummyUser instanceof Publisher);
        } 
        else if(menuSelected == 2) {
            // Login
            System.out.println("For demo application");
            System.out.println("please enter username as\'publisher\' to login as publisher");
            System.out.println("otherwise will be an user role");
            String username = General.loginPage();
            if(username == "publisher") market.login(username, true);
            else market.login(username, false);
        } else if(menuSelected == 3) {
            // Exit program
            System.exit(0);
        }

        // General.home(market.getGames());
    }
}