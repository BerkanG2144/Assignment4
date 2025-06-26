package booking;
import command.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class HotelBooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Hotel> hotels = new HashMap<>();
        CustomerManager customerManager = new CustomerManager();
        AtomicInteger bookingIdGenerator = new AtomicInteger(1);

        Map<String, Command> commands = new HashMap<>();
        commands.put("add hotel", new AddHotelCommand(hotels));
        commands.put("remove hotel", new RemoveHotelCommand(hotels));
        commands.put("add room", new AddRoomCommand(hotels));
        commands.put("remove room", new RemoveRoomCommand(hotels));
        commands.put("list rooms", new ListRoomsCommand(hotels));
        commands.put("find available", new FindAvailableCommand(hotels));
        commands.put("find cheapest", new FindCheapestCommand(hotels));
        commands.put("book", new BookCommand(hotels, customerManager, bookingIdGenerator));

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
                "book 00012 1 2025-08-01 2025-08-12 Simon Student"
        };

        for (String input : testInput) {
            String[] parts = input.split("\\s+");
            if (parts.length < 2) {
                System.out.println("Error, unknown command");
                continue;
            }
            String commandKey;
            if (parts[0].equals("book") || parts[0].equals("list") || parts[0].equals("cancel") || parts[0].equals("quit")) {
                commandKey = parts[0];
            } else {
                commandKey = parts[0] + " " + parts[1];
            }

            Command command = commands.get(commandKey);
            if (command != null) {
                command.execute(parts);
            } else {
                System.out.println("Error, unknown command");
            }
        }

        // === Danach normale Benutzereingabe starten ===
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            String[] parts = input.split("\\s+");
            if (parts.length < 2) {
                System.out.println("Error, unknown command");
                continue;
            }

            String commandKey = parts[0] + " " + parts[1];
            Command command = commands.get(commandKey);

            if (command != null) {
                command.execute(parts);
            } else {
                System.out.println("Error, unknown command");
            }
        }
    }
}
