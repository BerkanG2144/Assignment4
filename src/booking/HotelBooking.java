package booking;
import command.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelBooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Hotel> hotels = new HashMap<>();

        Map<String, Command> commands = new HashMap<>();
        commands.put("add hotel", new AddHotelCommand(hotels));
        commands.put("remove hotel", new RemoveHotelCommand(hotels));
        commands.put("add room", new AddRoomCommand(hotels));
        commands.put("remove room", new RemoveRoomCommand(hotels));
        commands.put("list rooms", new ListRoomsCommand(hotels));

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
                command.execute(parts); // args enthalten alle Teile, z.adB. ["add", "room", "11", "101", "Single", "99.99"]
            } else {
                System.out.println("Error, unknown command");
            }
        }
    }
}
