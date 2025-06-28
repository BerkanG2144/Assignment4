package command;

/**
 * Command to quit the booking system.
 *
 * Usage: {@code quit}
 *
 * Invokes a callback to stop the main loop without calling the JVM directly.
 *
 * @author ujnaa
 */
public class QuitCommand implements Command {

    private final Runnable quitCallback;

    /**
     * Constructs a quit command that triggers the given callback.
     *
     * @param quitCallback the action to perform on quit (e.g., stopping main loop)
     */
    public QuitCommand(Runnable quitCallback) {
        this.quitCallback = quitCallback;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Error, invalid quit command");
            return;
        }
        quitCallback.run(); // Beendet die Schleife in main
    }

    @Override
    public String keyword() {
        return "quit";
    }
}
