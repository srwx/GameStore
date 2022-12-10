package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import game.GameFactory;
import user.UserFactory;

public abstract class Command {
    GameFactory game;
    UserFactory user;
    String executedDateTime;

    public Command(GameFactory game, UserFactory user) {
        this.game = game;
        this.user = user;
    }

    void setExecutedDateTime(String executedDetail) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        
        String executedDateTime = "Executed on: " + dateTime.format(dateTimeFormatter);
        this.executedDateTime = executedDetail + executedDateTime;
    }

    public String getExecutedDateTime() {
        return executedDateTime;
    }

    abstract public void execute();    
}
