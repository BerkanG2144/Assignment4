package booking;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Manages all bookings in the system.
 *
 * Provides functionality to create, cancel, retrieve, and list bookings.
 * Each booking is assigned a unique booking ID starting from 1.
 *
 * @author ujnaa
 */
public class BookingManager {

    private final Map<Integer, Booking> bookings = new HashMap<>();
    private int bookingIdGenerator = Constants.INITIAL_BOOKING_ID;


    /**
     * Creates a new booking for the given customer and date range.
     *
     * @param customer the customer making the booking
     * @param range the date range of the booking
     * @return the created Booking
     * @throws NullPointerException if customer or range is null
     */
    public Booking createBooking(Customer customer, DateRange range) {
        int bookingId = bookingIdGenerator++;
        Booking booking = new Booking(bookingId, customer, range);
        bookings.put(bookingId, booking);
        return booking;
    }

    /**
     * Cancels the booking with the given ID, if it exists.
     *
     * @param bookingId the ID of the booking to cancel
     */
    public void cancelBooking(int bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.cancel();
        }
    }

    /**
     * Returns the booking associated with the given ID.
     *
     * @param bookingId the ID of the booking
     * @return the Booking if found, or null otherwise
     */
    public Booking getBookingById(int bookingId) {
        return bookings.get(bookingId);
    }

    /**
     * Returns a collection of all bookings.
     *
     * @return all bookings currently managed
     */
    public Collection<Booking> getAllBookings() {
        return bookings.values();
    }
}
