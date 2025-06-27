package command;

/**
 * Represents a console command to be executed in the hotel booking system.
 */
public interface Command {
    void execute(String[] args);

    String keyword();
}