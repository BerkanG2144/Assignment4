package booking;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a date range from a start date (inclusive) to an end date (exclusive).
 * A date range is considered valid only if the start date is strictly before the end date.
 * Two date ranges are equal if both start and end dates are equal.
 *
 * @author ujnaa
 */
public final class DateRange {

    /** Detailed message when a date range is invalid (start date is not before end date). */
    public static final String MESSAGE_INVALID_DATE_RANGE = "Invalid date range: from must be before to.";

    private static final String TO_STRING_PREFIX = "[";
    private static final String TO_STRING_SEPARATOR = " to ";
    private static final String TO_STRING_SUFFIX = ")";

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs a new DateRange.
     *
     * @param from the start date (inclusive)
     * @param to the end date (exclusive)
     * @throws IllegalArgumentException if from is null, to is null, or from is not before to
     */
    public DateRange(LocalDate from, LocalDate to) {
        if (from == null || to == null || !from.isBefore(to)) {
            throw new IllegalArgumentException(MESSAGE_INVALID_DATE_RANGE);
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date (inclusive).
     *
     * @return the start date
     */
    public LocalDate from() {
        return from;
    }

    /**
     * Returns the end date (exclusive).
     *
     * @return the end date
     */
    public LocalDate to() {
        return to;
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
        return Objects.hash(from, to);
    }

    /**
     * Returns a string representation of the date range.
     *
     * @return a string in the format [from to)
     */
    @Override
    public String toString() {
        return TO_STRING_PREFIX + from + TO_STRING_SEPARATOR + to + TO_STRING_SUFFIX;
    }
}
