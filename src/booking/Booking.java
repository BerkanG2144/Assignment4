package booking;

import java.util.Objects;


/**
 * Represents a booking made by a customer.
 * A booking includes a unique booking ID, the customer who made the booking,
 * a date range representing the booking period, and a cancellation status.
 *
 * @author ujnaa
 */
public final class Booking {

    private static final String PREFIX = "Booking[";
    private static final String FIELD_BOOKING_ID = "bookingId=";
    private static final String FIELD_CUSTOMER = "customer=";
    private static final String FIELD_DATE_RANGE = "dateRange=";
    private static final String COMMA_SEPARATOR = ", ";
    private static final String SUFFIX = "]";
    private final int bookingId;
    private final Customer customer;
    private final DateRange dateRange;
    private boolean cancelled;

    /**
     * Constructs a new booking.
     *
     * @param bookingId the unique ID of the booking
     * @param customer the customer who made the booking
     * @param dateRange the date range of the booking
     * @throws NullPointerException if customer or dateRange is null
     */
    public Booking(int bookingId, Customer customer, DateRange dateRange) {
        this.bookingId = bookingId;
        this.customer = Objects.requireNonNull(customer);
        this.dateRange = Objects.requireNonNull(dateRange);
        this.cancelled = false;
    }

    /**
     * Returns the unique ID of the booking.
     *
     * @return the booking ID
     */
    public int bookingId() {
        return bookingId;
    }

    /**
     * Returns the customer who made the booking.
     *
     * @return the customer
     */
    public Customer customer() {
        return customer;
    }

    /**
     * Returns the date range of the booking.
     *
     * @return the date range
     */
    public DateRange dateRange() {
        return dateRange;
    }

    /**
     * Checks whether the booking has been cancelled.
     *
     * @return true if the booking is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancels the booking.
     * Once cancelled, the booking cannot be reactivated.
     */
    public void cancel() {
        this.cancelled = true;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two bookings are considered equal if their ID, customer, and date range match.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking other)) {
            return false;
        }
        return bookingId == other.bookingId
                && Objects.equals(customer, other.customer)
                && Objects.equals(dateRange, other.dateRange);
    }

    /**
     * Returns a hash code value for the booking.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(bookingId, customer, dateRange);
    }

    /**
     * Returns a string representation of the booking.
     *
     * @return a string describing the booking
     */
    @Override
    public String toString() {
        return PREFIX
                + FIELD_BOOKING_ID + bookingId + COMMA_SEPARATOR
                + FIELD_CUSTOMER + customer + COMMA_SEPARATOR
                + FIELD_DATE_RANGE + dateRange + SUFFIX;
    }
}
