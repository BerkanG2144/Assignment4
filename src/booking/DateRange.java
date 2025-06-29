package booking;

import java.time.LocalDate;

import booking.Constants;

/**
 * Represents a date range from a start date (inclusive) to an end date (exclusive).
 * A date range is considered valid only if the start date is strictly before the end date.
 *
 * Two date ranges are equal if both start and end dates are equal.
 *
 * @param from the start date (inclusive)
 * @param to the end date (exclusive)
 * @author ujnaa
 */
public record DateRange(LocalDate from, LocalDate to) {

    /**
     * Constructs a new DateRange.
     *
     * @param from the start date (inclusive)
     * @param to the end date (exclusive)
     * @throws IllegalArgumentException if from is null, to is null, or from is not before to
     */
    public DateRange {
        if (from == null || to == null || !from.isBefore(to)) {
            throw new IllegalArgumentException(Constants.MESSAGE_INVALID_DATE_RANGE);
        }
    }

    /**
     * Checks if this date range overlaps with another date range.
     * Overlap is defined as having at least one common day.
     *
     * @param other the other date range to compare with
     * @return true if the date ranges overlap, false otherwise
     */
    public boolean overlaps(DateRange other) {
        return !this.to.isBefore(other.from) && !this.from.isAfter(other.to.minusDays(1));
    }

    /**
     * Compares this DateRange to another object for equality.
     *
     * @param o the object to compare with
     * @return true if both ranges have the same start and end dates
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DateRange other)) {
            return false;
        }
        return from.equals(other.from) && to.equals(other.to);
    }

    /**
     * Returns the hash code of this date range.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return from.hashCode() * 31 + to.hashCode();
    }

    /**
     * Returns a string representation of the date range.
     *
     * @return a string in the format [from to)
     */
    @Override
    public String toString() {
        return "[" + from + " to " + to + ")";
    }
}
