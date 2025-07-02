package command;

import booking.Hotel;

import java.util.Map;

/**
 * Command to remove a room from a hotel.
 * Usage: {@code remove room <HotelID> <RoomId>}
 *
 * The room is removed only if it exists and belongs to an existing hotel.
 *
 * @author ujnaa
 */
public class RemoveRoomCommand implements Command {
    /** Success message for valid command execution. */
    public static final String MESSAGE_OK = "OK";
    /** Error message when the 'remove room' command usage is incorrect. */
    public static final String ERROR_USAGE_REMOVE_ROOM = "Error, usage: remove room <HotelID> <RoomId>";
    /** Error message when the specified room does not exist in the hotel. */
    public static final String ERROR_ROOM_DOES_NOT_EXIST = "Error, room does not exist";
    /** Error message when the specified hotel does not exist. */
    public static final String ERROR_HOTEL_DOES_NOT_EXIST = "Error, hotel does not exist";
    /** Error message when a hotel ID is outside the valid range or malformed. */
    public static final String ERROR_INVALID_HOTEL_ID = "Error, invalid HotelID";
    /** Command keyword to remove a room from a hotel. */
    public static final String COMMAND_REMOVE_ROOM = "remove room";

    private static final int EXPECTED_ARGUMENT_COUNT = 4;
    private static final int INDEX_HOTEL_ID = 2;
    private static final int INDEX_ROOM_NUMBER = 3;
    private final Map<Integer, Hotel> hotels;

    /**
     * Constructs the command with access to the hotel map.
     *
     * @param hotels the map of hotels
     */
    public RemoveRoomCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_USAGE_REMOVE_ROOM);
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[INDEX_HOTEL_ID]);
            int roomNumber = Integer.parseInt(args[INDEX_ROOM_NUMBER]);

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println(ERROR_HOTEL_DOES_NOT_EXIST);
                return;
            }

            if (!hotel.getRooms().containsKey(roomNumber)) {
                System.out.println(ERROR_ROOM_DOES_NOT_EXIST);
                return;
            }

            hotel.removeRoom(roomNumber);
            System.out.println(MESSAGE_OK);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_HOTEL_ID);
        }
    }

    @Override
    public String keyword() {
        return COMMAND_REMOVE_ROOM;
    }

}