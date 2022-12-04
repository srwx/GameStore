package user;

import java.util.ArrayList;

public class GeneralUser {
    private String userId;
    private String username;
    // private ArrayList<Game> ownedGames;

    public GeneralUser(String username) {
        this.username = username;
        String id = "";
        for(int i = 0; i < username.length(); i++) {
            char aplabeth = username.charAt(i);
            int ascii = aplabeth;
            id.concat(String.valueOf(ascii));
        }
        this.userId = id;
        // this.ownedGames = new ArrayList<Game>();
    }

    // public GeneralUser(String username, ArrayList<Game> ownedGames) {
    //     this.username = username;
    //     String id = "";
    //     for(int i = 0; i < username.length(); i++) {
    //         char aplabeth = username.charAt(i);
    //         int ascii = aplabeth;
    //         id.concat(String.valueOf(ascii));
    //     }
    //     this.userId = id;
    //     this.ownedGames = ownedGames;
    // }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void printDetail() {
        System.out.println("username: " + this.username);
        System.out.println("userId: " + this.userId);
    }
}
