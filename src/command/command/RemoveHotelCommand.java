package command.command;

import booking.Booking;
import booking.BookingManager;
import booking.Hotel;
import booking.Room;
import command.Command;

import java.util.Map;

/**
 * Command to remove a hotel from the system.
 *
 * Usage: {@code remove hotel <HotelID>}
 *
 * The hotel is removed only if it exists.
 *
 * @author ujnaa
 */
public class RemoveHotelCommand implements Command {

    private final Map<Integer, Hotel> hotels;
    private final BookingManager bookingManager;

    /**
     * Constructs the command with access to the hotel map.
     *
     * @param hotels the map of hotels
     * @param bookingManager  provide bookings
     */
    public RemoveHotelCommand(Map<Integer, Hotel> hotels, BookingManager bookingManager) {
        this.hotels = hotels;
        this.bookingManager = bookingManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Error, usage: remove hotel <HotelID>");
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);
            Hotel hotel = hotels.get(hotelId);

            if (hotel == null) {
                System.out.println("Error, hotel not found");
                return;
            }

            for (Room room : hotel.getRooms().values()) {
                for (Booking booking : room.getBookings()) {
                    bookingManager.cancelBooking(booking.bookingId());
                }
            }

            hotels.remove(hotelId);
            System.out.println("OK");
        } catch (NumberFormatException e) {
            System.out.println("Error, invalid HotelId");
        }
    }

    @Override
    public String keyword() {
        return "remove hotel";
    }

}