package command;

import booking.Hotel;
import booking.Room;

import java.util.Map;

/**
 * Command to add a new room to a hotel.
 *
 * If the hotel does not exist or the format is invalid, an error is printed.
 * If the room already exists in the hotel, an error is printed.
 * Otherwise, the room is added successfully.
 *
 * @author ujnaa
 */
public class AddRoomCommand implements Command {

    private final Map<Integer, Hotel> hotels;

    /**
     * Constructs the command with the map of hotels.
     *
     * @param hotels the map of hotels, indexed by hotel ID
     */
    public AddRoomCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * Executes the command to add a room.
     *
     * @param args the user input split into string arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 6) {
            System.out.println("Error, usage: add room <HotelID> <RoomId> <Category> <Price>");
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);
            int roomNumber = Integer.parseInt(args[3]);
            String category = args[4];
            double price = Double.parseDouble(args[5]);

            if (price <= 0) {
                System.out.println("Error, price must be greater than 0");
                return;
            }

            if (!category.equals("Single") && !category.equals("Double") && !category.equals("Suite")) {
                System.out.println("Error, usage: add room <HotelID> <RoomId> <Category> <Price>");
                return;
            }

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println("Error, hotel does not exist");
                return;
            }

            if (hotel.getRooms().containsKey(roomNumber)) {
                System.out.println("Error, room already exists");
                return;
            }

            hotel.addRoom(new Room(roomNumber, category, price));
            System.out.println("OK");

        } catch (NumberFormatException e) {
            System.out.println("Error, number format");
        }
    }

    /**
     * Returns the command keyword that triggers this command.
     *
     * @return the keyword "add room"
     */
    @Override
    public String keyword() {
        return "add room";
    }
}
