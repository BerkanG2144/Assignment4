package command;

import booking.Booking;
import booking.BookingManager;
import booking.Hotel;
import booking.Room;

import java.util.Map;

/**
 * Command to remove a hotel from the system.
 * Usage: {@code remove hotel <HotelID>}
 *
 * The hotel is removed only if it exists.
 *
 * @author ujnaa
 */
public class RemoveHotelCommand implements Command {

    /** Success message for valid command execution. */
    public static final String MESSAGE_OK = "OK";
    /** Error message when the 'remove hotel' command usage is incorrect. */
    public static final String ERROR_USAGE_REMOVE_HOTEL = "Error, usage: remove hotel <HotelID>";
    /** Error message when no hotel with the specified ID was found. */
    public static final String ERROR_HOTEL_NOT_FOUND = "Error, hotel not found";
    /** Command keyword to remove a hotel from the system. */
    public static final String COMMAND_REMOVE_HOTEL = "remove hotel";
    /** Error message when a hotel ID is outside the valid range or malformed. */
    public static final String ERROR_INVALID_HOTEL_ID = "Error, invalid HotelID";

    private static final int EXPECTED_ARGUMENT_COUNT = 3;
    private static final int INDEX_HOTEL_ID = 2;
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
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_USAGE_REMOVE_HOTEL);
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[INDEX_HOTEL_ID]);
            Hotel hotel = hotels.get(hotelId);

            if (hotel == null) {
                System.out.println(ERROR_HOTEL_NOT_FOUND);
                return;
            }

            for (Room room : hotel.getRooms().values()) {
                for (Booking booking : room.getBookings()) {
                    bookingManager.cancelBooking(booking.bookingId());
                }
            }

            hotels.remove(hotelId);
            System.out.println(MESSAGE_OK);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_HOTEL_ID);
        }
    }

    @Override
    public String keyword() {
        return COMMAND_REMOVE_HOTEL;
    }

}