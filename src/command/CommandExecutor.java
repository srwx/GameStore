package command;

import java.util.ArrayList;

public class CommandExecutor {
    private ArrayList<Command> history = new ArrayList<Command>();

    public void executeCommand(Command command) {
        command.execute();
        if (command.executedDateTime != null)
            history.add(command);
    }

    public void printHistoryCommand() {
        for (int i = history.size()-1; i >= 0; i--) {
            Command command = history.get(i);
            System.out.println(command.getExecutedDateTime());
            System.out.println("==================================");
        }
    }
}
