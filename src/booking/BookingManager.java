package booking;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingManager {

    private final Map<Integer, Booking> bookings = new HashMap<>();
    private final AtomicInteger bookingIdGenerator;

    public BookingManager() {
        this.bookingIdGenerator = new AtomicInteger(1); // IDs beginnen bei 1
    }

    public Booking createBooking(Customer customer, DateRange range) {
        int bookingId = bookingIdGenerator.getAndIncrement();
        Booking booking = new Booking(bookingId, customer, range);
        bookings.put(bookingId, booking);
        return booking;
    }

    public void cancelBooking(int bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.cancel();
        }
    }

    public Booking getBookingById(int bookingId) {
        return bookings.get(bookingId);
    }

    public Collection<Booking> getAllBookings() {
        return bookings.values();
    }
}
