package command;

import booking.Hotel;
import booking.Room;
import booking.RoomCategory;

import java.util.Map;

/**
 * Command to add a new room to a hotel.
 * If the hotel does not exist or the format is invalid, an error is printed.
 * If the room already exists in the hotel, an error is printed.
 * Otherwise, the room is added successfully.
 *
 * @author ujnaa
 */
public class AddRoomCommand implements Command {

    /** Error message when usage of 'add room' command is incorrect. */
    public static final String ERROR_USAGE_ADD_ROOM = "Error, usage: add room <HotelID> <RoomId> <Category> <Price>";
    /** Success message for valid command execution. */
    public static final String MESSAGE_OK = "OK";
    /** Error message when room price is not greater than zero. */
    public static final String ERROR_PRICE_GREATER_ZERO = "Error, price must be greater than 0";
    /** Error message when the referenced hotel does not exist. */
    public static final String ERROR_HOTEL_DOES_NOT_EXIST = "Error, hotel does not exist";
    /** Error message when a room already exists in the specified hotel. */
    public static final String ERROR_ROOM_ALREADY_EXISTS = "Error, room already exists";
    /** Error message for general number format issues. */
    public static final String ERROR_NUMBER_FORMAT = "Error, number format";
    /** Command keyword to add a room to a hotel. */
    public static final String COMMAND_ADD_ROOM = "add room";

    private static final int EXPECTED_ARGUMENT_COUNT = 6;
    private static final int INDEX_HOTEL_ID = 2;
    private static final int INDEX_ROOM_NUMBER = 3;
    private static final int INDEX_CATEGORY = 4;
    private static final int INDEX_PRICE = 5;
    private static final double MIN_PRICE = 0.0;

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
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_USAGE_ADD_ROOM);
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[INDEX_HOTEL_ID]);
            int roomNumber = Integer.parseInt(args[INDEX_ROOM_NUMBER]);
            RoomCategory category = RoomCategory.fromString(args[INDEX_CATEGORY]);
            double price = Double.parseDouble(args[INDEX_PRICE]);

            if (price <= MIN_PRICE) {
                System.out.println(ERROR_PRICE_GREATER_ZERO);
                return;
            }

            if (category == null) {
                System.out.println(ERROR_USAGE_ADD_ROOM);
                return;
            }

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println(ERROR_HOTEL_DOES_NOT_EXIST);
                return;
            }

            if (hotel.getRooms().containsKey(roomNumber)) {
                System.out.println(ERROR_ROOM_ALREADY_EXISTS);
                return;
            }

            hotel.addRoom(new Room(roomNumber, category, price));
            System.out.println(MESSAGE_OK);

        } catch (NumberFormatException e) {
            System.out.println(ERROR_NUMBER_FORMAT);
        }
    }

    /**
     * Returns the command keyword that triggers this command.
     *
     * @return the keyword "add room"
     */
    @Override
    public String keyword() {
        return COMMAND_ADD_ROOM;
    }
}
