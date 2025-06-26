package booking;

import java.time.LocalDate;

/**
 * Represents a range between two dates (inclusive start, exclusive end).
 *
 * @author uXXXX
 */
public record DateRange(LocalDate from, LocalDate to) {

    /**
     * Constructs a date range.
     *
     * @param from start date (inclusive)
     * @param to   end date (exclusive)
     * @throws IllegalArgumentException if from is after or equal to to
     */
    public DateRange {
        if (from == null || to == null || !from.isBefore(to)) {
            throw new IllegalArgumentException("Invalid date range: from must be before to.");
        }
    }

    /**
     * Checks whether this range overlaps with another.
     *
     * @param other the other date range
     * @return true if the ranges overlap
     */
    public boolean overlaps(DateRange other) {
        return !this.to.isBefore(other.from) && !this.from.isAfter(other.to.minusDays(1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateRange other)) return false;
        return from.equals(other.from) && to.equals(other.to);
    }

    @Override
    public int hashCode() {
        return from.hashCode() * 31 + to.hashCode();
    }

    @Override
    public String toString() {
        return "[" + from + " to " + to + ")";
    }
}
