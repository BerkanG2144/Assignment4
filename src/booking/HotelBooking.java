package booking;
import command.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HotelBooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Hotel> hotels = new HashMap<>();
        CustomerManager customerManager = new CustomerManager();
        BookingManager bookingManager = new BookingManager();
        boolean[] running = {true};

        List<Command> commandList = List.of(
                new AddHotelCommand(hotels),
                new RemoveHotelCommand(hotels),
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


        // === Testdaten aus der Beispielinteraktion (optional abschaltbar) ===
        String[] testInput = {
                "add hotel 11 Berlin",
                "add hotel 12 Karlsruhe",
                "add hotel 13 Karlsruhe",
                "add room 11 101 Single 11.99",
                "add room 11 102 Single 11.99",
                "add room 11 103 Double 24.99",
                "add room 11 104 Double 25.99",
                "remove room 11 104",
                "add room 12 1 Single 8.99",
                "add room 12 2 Suite 399.99",
                "add room 13 001 Single 25.99",
                "add room 13 002 Double 25.99",
                "list rooms",
                "find available Berlin Suite 2025-08-01 2025-08-12",
                "find available Berlin Double 2025-08-01 2025-08-12",
                "find available Karlsruhe Double 2025-08-01 2025-08-12",
                "find cheapest Berlin Single 2025-08-01 2025-08-12",
                "find cheapest Karlsruhe Single 2025-08-01 2025-08-12",
                "book 00012 1 2025-08-01 2025-08-12 Simon Student",
                "list bookings",
                "find cheapest Karlsruhe Single 2025-08-01 2025-08-12",
                "find cheapest Karlsruhe Single 2025-08-05 2025-08-07",
                "find cheapest Karlsruhe Single 2025-08-11 2025-08-22",
                "remove hotel 13",
                "find cheapest Karlsruhe Single 2025-08-11 2025-08-22",
                "cancel 1 1",
                "find cheapest Karlsruhe Single 2025-08-11 2025-08-22"
        };

        for (String input : testInput) {
            String[] parts = input.split("\\s+");
            String commandKey = extractCommandKey(parts, commands);

            if (commandKey == null) {
                System.out.println("Error, unknown command");
                continue;
            }

            commands.get(commandKey).execute(parts);
        }


        while (running[0]) {
            System.out.print("> ");
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
