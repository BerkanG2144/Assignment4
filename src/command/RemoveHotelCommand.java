package command;

import booking.Hotel;
import booking.Constants;
import booking.Room;
import booking.BookingManager;
import booking.Booking;

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
                System.out.println(Constants.ERROR_HOTEL_NOT_FOUND);
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
            System.out.println(Constants.ERROR_INVALID_HOTEL_ID);
        }
    }

    @Override
    public String keyword() {
        return "remove hotel";
    }

}