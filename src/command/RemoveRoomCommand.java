package command;

import booking.Constants;
import booking.Hotel;

import java.util.Map;

/**
 * Command to remove a room from a hotel.
 *
 * Usage: {@code remove room <HotelID> <RoomId>}
 *
 * The room is removed only if it exists and belongs to an existing hotel.
 *
 * @author ujnaa
 */
public class RemoveRoomCommand implements Command {

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
        if (args.length != 4) {
            System.out.println(Constants.ERROR_USAGE_REMOVE_ROOM);
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);
            int roomNumber = Integer.parseInt(args[3]);

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println(Constants.ERROR_HOTEL_DOES_NOT_EXIST);
                return;
            }

            if (!hotel.getRooms().containsKey(roomNumber)) {
                System.out.println(Constants.ERROR_ROOM_DOES_NOT_EXIST);
                return;
            }

            hotel.removeRoom(roomNumber);
            System.out.println(Constants.MESSAGE_OK);
        } catch (NumberFormatException e) {
            System.out.println(Constants.ERROR_INVALID_HOTEL_ID);
        }
    }

    @Override
    public String keyword() {
        return Constants.COMMAND_REMOVE_ROOM;
    }

}