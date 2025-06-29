package booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in a hotel.
 *
 * Each room has a unique number within a hotel, a category (e.g., Single, Double, Suite),
 * a price per night, and a list of bookings.
 *
 * @author ujnaa
 */
public class Room {

    private final int number;
    private final RoomCategory category;
    private final double price;
    private final List<Booking> bookings;

    /**
     * Constructs a new Room with the specified number, category, and price.
     *
     * @param number the room number (unique within a hotel)
     * @param category the room category
     * @param price the price per night
     */
    public Room(int number, RoomCategory category, double price) {
        this.number = number;
        this.category = category;
        this.price = price;
        this.bookings = new ArrayList<>();
    }

    /**
     * Returns the room number.
     *
     * @return the room number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the room category.
     *
     * @return the room category
     */
    public RoomCategory getCategory() {
        return category;
    }

    /**
     * Returns the room price per night.
     *
     * @return the price per night
     */
    public double getPrice() {
        return price;
    }

    /**
     * Checks if the room is available for the given date range.
     * A room is available if there is no overlapping booking.
     *
     * @param range the date range to check
     * @return true if the room is available, false otherwise
     */
    public boolean isAvailable(DateRange range) {
        for (Booking booking : bookings) {
            if (!(range.to().compareTo(booking.dateRange().from()) <= 0
                    || range.from().compareTo(booking.dateRange().to()) >= 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the room contains a booking with the given ID.
     *
     * @param bookingId the booking ID to check
     * @return true if a matching booking exists, false otherwise
     */
    public boolean hasBooking(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.bookingId() == bookingId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all bookings for this room.
     *
     * @return the list of bookings
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Adds a booking to the room.
     *
     * @param booking the booking to add
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Removes a booking with the specified ID from the room.
     *
     * @param bookingId the ID of the booking to remove
     */
    public void removeBooking(int bookingId) {
        bookings.removeIf(b -> b.bookingId() == bookingId);
    }
}
