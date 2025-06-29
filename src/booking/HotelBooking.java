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
                    new QuitCommand(() -> running[0] = false)
            );

            Map<String, Command> commands = new HashMap<>();
            for (Command c : commandList) {
                commands.put(c.keyword(), c);
            }

            while (running[0]) {
                String input = scanner.nextLine().trim();
                String[] parts = input.split("\\s+");

            String commandKey = extractCommandKey(parts, commands);

            if (commandKey == null) {
                System.out.println("Error, unknown command");
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
        if (parts.length >= 2) {
            String twoWord = parts[0] + " " + parts[1];
            if (commands.containsKey(twoWord)) {
                return twoWord;
            }
        }
        if (commands.containsKey(parts[0])) {
            return parts[0];
        }
        return null;

    }
}
