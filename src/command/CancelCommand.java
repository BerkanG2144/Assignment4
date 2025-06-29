package command;

import booking.Booking;
import booking.BookingManager;
import booking.Constants;
import booking.Hotel;
import booking.Room;

import java.util.Map;

/**
 * Command to cancel a booking.
 *
 * Usage: {@code cancel <BookingID> <CustomerID>}
 *
 * The booking is cancelled only if it exists, is not already cancelled, and belongs to the given customer.
 * The booking is also removed from the associated room.
 *
 * @author ujnaa
 */
public class CancelCommand implements Command {

    private final BookingManager bookingManager;
    private final Map<Integer, Hotel> hotels;

    /**
     * Constructs a cancel command.
     *
     * @param bookingManager the manager handling all bookings
     * @param hotels the map of hotels for locating rooms to remove bookings from
     */
    public CancelCommand(BookingManager bookingManager, Map<Integer, Hotel> hotels) {
        this.bookingManager = bookingManager;
        this.hotels = hotels;
    }

    /**
     * Executes the cancel command.
     *
     * @param args the user input split into arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println(Constants.ERROR_INVALID_CANCEL_FORMAT);
            return;
        }

        try {
            int bookingId = Integer.parseInt(args[1]);
            int customerId = Integer.parseInt(args[2]);

            Booking booking = bookingManager.getBookingById(bookingId);
            if (booking == null || booking.isCancelled()) {
                System.out.println(Constants.ERROR_BOOKING_NOT_FOUND);
                return;
            }

            if (booking.customer().getCustomerId() != customerId) {
                System.out.println(Constants.ERROR_CUSTOMER_MISMATCH);
                return;
            }

            for (Hotel hotel : hotels.values()) {
                Room room = hotel.findRoomWithBooking(bookingId);
                if (room != null) {
                    room.removeBooking(bookingId);
                    break;
                }
            }

            bookingManager.cancelBooking(bookingId);
            System.out.println("OK");

        } catch (NumberFormatException e) {
            System.out.println(Constants.ERROR_INVALID_NUMBERS);
        }
    }

    /**
     * Returns the keyword that triggers this command.
     *
     * @return the keyword "cancel"
     */
    @Override
    public String keyword() {
        return "cancel";
    }
}
