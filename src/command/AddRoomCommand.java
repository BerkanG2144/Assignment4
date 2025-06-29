package command;

import booking.Constants;
import booking.Hotel;
import booking.Room;
import booking.RoomCategory;

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
            System.out.println(Constants.ERROR_USAGE_ADD_ROOM);
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);
            int roomNumber = Integer.parseInt(args[3]);
            RoomCategory category = RoomCategory.fromString(args[4]);
            double price = Double.parseDouble(args[5]);

            if (price <= 0) {
                System.out.println(Constants.ERROR_PRICE_GREATER_ZERO);
                return;
            }

            if (category == null) {
                System.out.println(Constants.ERROR_USAGE_ADD_ROOM);
                return;
            }

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println(Constants.ERROR_HOTEL_DOES_NOT_EXIST);
                return;
            }

            if (hotel.getRooms().containsKey(roomNumber)) {
                System.out.println(Constants.ERROR_ROOM_ALREADY_EXISTS);
                return;
            }

            hotel.addRoom(new Room(roomNumber, category, price));
            System.out.println(Constants.MESSAGE_OK);

        } catch (NumberFormatException e) {
            System.out.println(Constants.ERROR_NUMBER_FORMAT);
        }
    }

    /**
     * Returns the command keyword that triggers this command.
     *
     * @return the keyword "add room"
     */
    @Override
    public String keyword() {
        return Constants.COMMAND_ADD_ROOM;
    }
}
