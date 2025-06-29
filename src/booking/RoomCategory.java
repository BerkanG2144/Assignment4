package booking;

/**
 * Enumeration of available room categories.
 */
public enum RoomCategory {
    SINGLE,
    DOUBLE,
    SUITE;

    /**
     * Parses a room category from a string ignoring case.
     *
     * @param value the string value
     * @return the matching category
     * @throws IllegalArgumentException if no matching category exists
     */
    public static RoomCategory fromString(String value) {
        return RoomCategory.valueOf(value.toUpperCase());
    }
}
