package booking;

import java.util.Objects;

public final class Booking {

    private final int bookingId;
    private final Customer customer;
    private final DateRange dateRange;
    private boolean cancelled;

    public Booking(int bookingId, Customer customer, DateRange dateRange) {
        this.bookingId = bookingId;
        this.customer = Objects.requireNonNull(customer);
        this.dateRange = Objects.requireNonNull(dateRange);
        this.cancelled = false;
    }

    public int bookingId() {
        return bookingId;
    }

    public Customer customer() {
        return customer;
    }

    public DateRange dateRange() {
        return dateRange;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking other)) return false;
        return bookingId == other.bookingId &&
                Objects.equals(customer, other.customer) &&
                Objects.equals(dateRange, other.dateRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, customer, dateRange);
    }

    @Override
    public String toString() {
        return "Booking[" +
                "bookingId=" + bookingId + ", " +
                "customer=" + customer + ", " +
                "dateRange=" + dateRange + ']';
    }
}
