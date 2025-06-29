package booking;

/**
 * Holds shared constant values for the booking system.
 */
public final class Constants {
    private Constants() {
    }

    public static final String ERROR_INVALID_DATE_FORMAT = "Error, invalid date format";
    public static final String ERROR_ADD_ROOM_USAGE = "Error, usage: add room <HotelID> <RoomId> <Category> <Price>";
    public static final String ERROR_ADD_HOTEL_USAGE = "Error, usage: add hotel <HotelID> <City>";
    public static final String ERROR_INVALID_NUMBER_FORMAT = "Error, invalid number format";
    public static final String ERROR_INVALID_HOTEL_ID = "Error, invalid HotelId";
    public static final String ERROR_HOTEL_NOT_FOUND = "Error, hotel not found";
    public static final String ERROR_HOTEL_DOES_NOT_EXIST = "Error, hotel does not exist";
    public static final String ERROR_FIND_AVAILABLE_USAGE = "Error, find available < Stadt > < Kategorie > < Datum > < Datum >";
    public static final String ERROR_UNKNOWN_COMMAND = "Error, unknown command";
}
