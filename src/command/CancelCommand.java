package command;

import booking.Booking;
import booking.BookingManager;
import booking.Hotel;
import booking.Room;

import java.util.Map;

/**
 * Command to cancel a booking.
 * Usage: {@code cancel <BookingID> <CustomerID>}
 *
 * The booking is cancelled only if it exists, is not already cancelled, and belongs to the given customer.
 * The booking is also removed from the associated room.
 *
 * @author ujnaa
 */
public class CancelCommand implements Command {

    /** Error message when the format of the cancel command is invalid. */
    public static final String ERROR_INVALID_CANCEL_FORMAT = "Error, invalid cancel format";
    /** Error message when no booking with the specified ID exists. */
    public static final String ERROR_BOOKING_NOT_FOUND = "Error, booking not found";
    /** Error message when the provided customer ID does not match the booking. */
    public static final String ERROR_CUSTOMER_MISMATCH = "Error, customer mismatch";
    /** Error message when one or more provided numbers are invalid. */
    public static final String ERROR_INVALID_NUMBERS = "Error, invalid numbers";
    /** Success message for valid command execution. */
    public static final String MESSAGE_OK = "OK";
    /** Command keyword to cancel a booking. */
    public static final String COMMAND_CANCEL = "cancel";

    private static final int EXPECTED_ARGUMENT_COUNT = 3;
    private static final int INDEX_BOOKING_ID = 1;
    private static final int INDEX_CUSTOMER_ID = 2;
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
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_INVALID_CANCEL_FORMAT);
            return;
        }

        try {
            int bookingId = Integer.parseInt(args[INDEX_BOOKING_ID]);
            int customerId = Integer.parseInt(args[INDEX_CUSTOMER_ID]);

            Booking booking = bookingManager.getBookingById(bookingId);
            if (booking == null || booking.isCancelled()) {
                System.out.println(ERROR_BOOKING_NOT_FOUND);
                return;
            }

            if (booking.customer().getCustomerId() != customerId) {
                System.out.println(ERROR_CUSTOMER_MISMATCH);
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
            System.out.println(MESSAGE_OK);

        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_NUMBERS);
        }
    }

    /**
     * Returns the keyword that triggers this command.
     *
     * @return the keyword "cancel"
     */
    @Override
    public String keyword() {
        return COMMAND_CANCEL;
    }
}
