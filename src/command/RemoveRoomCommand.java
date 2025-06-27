package command;

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
            System.out.println("Error, usage: remove room <HotelID> <RoomId>");
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);
            int roomNumber = Integer.parseInt(args[3]);

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println("Error, hotel does not exist");
                return;
            }

            if (!hotel.getRooms().containsKey(roomNumber)) {
                System.out.println("Error, room does not exist");
                return;
            }

            hotel.removeRoom(roomNumber);
            System.out.println("OK");
        } catch (NumberFormatException e) {
            System.out.println("Error, invalid HotelId");
        }
    }

    @Override
    public String keyword() {
        return "remove room";
    }

}