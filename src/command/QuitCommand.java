package command;

public class QuitCommand implements Command {

    private final Runnable quitCallback;

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
