package command;

/**
 * Command to quit the booking system.
 * Usage: {@code quit}
 *
 * Invokes a callback to stop the main loop without calling the JVM directly.
 *
 * @author ujnaa
 */
public class QuitCommand implements Command {

    /** Command keyword to terminate the hotel booking system. */
    public static final String COMMAND_QUIT = "quit";
    /** Error message when the 'quit' command is used incorrectly (e.g., with extra parameters). */
    public static final String ERROR_INVALID_QUIT_COMMAND = "Error, invalid quit command";

    private static final int EXPECTED_ARGUMENT_COUNT = 1;
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
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_INVALID_QUIT_COMMAND);
            return;
        }
        quitCallback.run();
    }

    @Override
    public String keyword() {
        return COMMAND_QUIT;
    }
}
