package booking;

import java.time.LocalDate;

/**
 * Represents a date range from start to end date (exclusive).
 *
 * @author uXXXX
 */
public record DateRange(LocalDate start, LocalDate end) {

    public boolean overlaps(DateRange other) {
        return !(!end.isAfter(other.start) || !start.isBefore(other.end));
    }

    public int getNights() {
        return (int) start.until(end).getDays();
    }
}
