package booking;

/**
 * Enumeration of valid room categories.
 */
public enum RoomCategory {
    SINGLE("Single"),
    DOUBLE("Double"),
    SUITE("Suite");

    private final String label;

    RoomCategory(String label) {
        this.label = label;
    }

    /**
     * Returns the display label for the category.
     *
     * @return the label
     */
    public String label() {
        return label;
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
