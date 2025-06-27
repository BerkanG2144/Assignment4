package booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in a hotel.
 * @author uXXXX
 */
public class Room {
    private final int number;
    private final String category;
    private final double price;
    private final List<Booking> bookings;

    public Room(int number, String category, double price) {
        this.number = number;
        this.category = category;
        this.price = price;
        this.bookings = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public boolean isAvailable(DateRange range) {
        for (Booking booking : bookings) {
            if (booking.dateRange().overlaps(range)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasBooking(int bookingId) {
        return bookings.stream().anyMatch(b -> b.bookingId() == bookingId);
    }


    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(int bookingId) {
        bookings.removeIf(b -> b.bookingId() == bookingId);
    }

}