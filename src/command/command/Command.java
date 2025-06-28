package command.command;

/**
 * Represents a console command to be executed in the hotel booking system.
 *
 * Each command must define its execution behavior and the keyword it responds to.
 *
 * @author ujnaa
 */
public interface Command {

    /**
     * Executes the command with the given arguments.
     *
     * @param args the arguments passed to the command, including the keyword
     */
    void execute(String[] args);

    /**
     * Returns the keyword that identifies this command.
     *
     * @return the command keyword
     */
    String keyword();
}
