package user;

public class Publisher extends GeneralUser {
    private String publisherUrl;

    public Publisher(String username) {
        super(username);
    }
    
    // public Publisher(String username, ArrayList<Game> ownedGames) {
    //     super(username, ownedGames);
    // }

    public void setePublisherUrl(String url) {
        this.publisherUrl = url;
    }

    public String getPubliserUrl() {
        return this.publisherUrl;
    }
}
