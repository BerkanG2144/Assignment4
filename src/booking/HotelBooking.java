package booking;

import command.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the hotel booking system.
 * Handles user input and delegates commands.
 *
 * @author ujnaa
 */
public final class HotelBooking {

    /** Error message when the entered command is not recognized by the system. */
    public static final String ERROR_UNKNOWN_COMMAND = "Error, unknown command";

    private static final int RUNNING_FLAG_INDEX = 0;
    private static final int DEFAULT_COMMAND_INDEX = 0;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int MIN_COMMAND_PARTS = 2;
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SPACE = " ";


    private HotelBooking() {
        // prevents instantiation
    }
    /**
     * Main method for starting the booking system.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<Integer, Hotel> hotels = new HashMap<>();
            CustomerManager customerManager = new CustomerManager();
            BookingManager bookingManager = new BookingManager();
            boolean[] running = {true};


            List<Command> commandList = List.of(
                    new AddHotelCommand(hotels),
                    new RemoveHotelCommand(hotels, bookingManager),
                    new AddRoomCommand(hotels),
                    new RemoveRoomCommand(hotels),
                    new ListRoomsCommand(hotels),
                    new FindAvailableCommand(hotels),
                    new FindCheapestCommand(hotels),
                    new BookCommand(hotels, customerManager, bookingManager),
                    new ListBookingsCommand(bookingManager),
                    new CancelCommand(bookingManager, hotels),
                    new QuitCommand(() -> running[RUNNING_FLAG_INDEX] = false)
            );

            Map<String, Command> commands = new HashMap<>();
            for (Command c : commandList) {
                commands.put(c.keyword(), c);
            }

            while (running[RUNNING_FLAG_INDEX]) {
                String input = scanner.nextLine().trim();
                String[] parts = input.split(WHITESPACE_REGEX);

                String commandKey = extractCommandKey(parts, commands);

                if (commandKey == null) {
                    System.out.println(ERROR_UNKNOWN_COMMAND);
                    continue;
                }

                commands.get(commandKey).execute(parts);
            }

        }
    }

    /**
     * Extracts the command key from the input parts.
     *
     * @param parts    the split input string
     * @param commands the map of available commands
     * @return the matched command key or null if unknown
     */
    private static String extractCommandKey(String[] parts, Map<String, Command> commands) {
        if (parts.length >= MIN_COMMAND_PARTS) {
            String twoWord = parts[FIRST_INDEX] + SPACE + parts[SECOND_INDEX];
            if (commands.containsKey(twoWord)) {
                return twoWord;
            }
        }
        if (commands.containsKey(parts[DEFAULT_COMMAND_INDEX])) {
            return parts[DEFAULT_COMMAND_INDEX];
        }
        return null;
    }
}