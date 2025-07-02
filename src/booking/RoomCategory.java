package booking;

/**
 * Enumeration of valid room categories.
 * @author ujnaa
 */
public enum RoomCategory {
    /** Single room category. */
    SINGLE("Single"),

    /** Double room category. */
    DOUBLE("Double"),

    /** Suite room category. */
    SUITE("Suite");

    private final String label;

    RoomCategory(String label) {
        this.label = label;
    }


    @Override
    public String toString() {
        return label;
    }

    /**
     * Parses a room category from a string.
     *
     * @param value the string value
     * @return the matching category or null if none matches
     */
    public static RoomCategory fromString(String value) {
        for (RoomCategory category : values()) {
            if (category.label.equalsIgnoreCase(value)) {
                return category;
            }
        }
        return null;
    }
}
