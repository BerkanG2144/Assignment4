package booking;

import java.util.List;

import booking.RoomCategory;

/**
 * Application wide constants used by several commands.
 */
public final class Constants {
    private Constants() {
        // prevent instantiation
    }

    /** Maximum allowed hotel id. */
    public static final int MAX_HOTEL_ID = 99999;

    /** Valid room categories. */
    public static final List<RoomCategory> VALID_CATEGORIES =
            List.of(RoomCategory.SINGLE, RoomCategory.DOUBLE, RoomCategory.SUITE);

    // Common error messages
    public static final String ERROR_USAGE_ADD_HOTEL = "Error, usage: add hotel <HotelID> <City>";
    public static final String ERROR_INVALID_HOTEL_ID = "Error, invalid HotelID";
    public static final String ERROR_HOTEL_EXISTS = "Error, hotel already exists";
    public static final String ERROR_INVALID_NUMBER_FORMAT = "Error, invalid number format";

    public static final String ERROR_USAGE_ADD_ROOM = "Error, usage: add room <HotelID> <RoomId> <Category> <Price>";
    public static final String ERROR_PRICE_GREATER_ZERO = "Error, price must be greater than 0";
    public static final String ERROR_HOTEL_DOES_NOT_EXIST = "Error, hotel does not exist";
    public static final String ERROR_ROOM_ALREADY_EXISTS = "Error, room already exists";
    public static final String ERROR_NUMBER_FORMAT = "Error, number format";

    public static final String ERROR_ROOM_DOES_NOT_EXIST = "Error, room does not exist";
    public static final String ERROR_HOTEL_NOT_FOUND = "Error, hotel not found";
    public static final String ERROR_ROOM_NOT_FOUND = "Error, room not found";
    public static final String ERROR_ROOM_NOT_AVAILABLE = "Error, room not available";
    public static final String ERROR_INVALID_DATE_RANGE = "Error, invalid date range";
    public static final String ERROR_INVALID_FORMAT = "Error, invalid format";
    public static final String ERROR_INVALID_CANCEL_FORMAT = "Error, invalid cancel format";
    public static final String ERROR_BOOKING_NOT_FOUND = "Error, booking not found";
    public static final String ERROR_CUSTOMER_MISMATCH = "Error, customer mismatch";
    public static final String ERROR_INVALID_NUMBERS = "Error, invalid numbers";
    public static final String ERROR_INVALID_INPUT = "Error, invalid input";
    public static final String ERROR_INVALID_LIST_BOOKINGS_COMMAND = "Error, invalid list bookings command";
    public static final String ERROR_INVALID_QUIT_COMMAND = "Error, invalid quit command";
    public static final String ERROR_UNKNOWN_COMMAND = "Error, unknown command";

    public static final String ERROR_UNKNOWN_CATEGORY = "Error, unknown category";
    public static final String ERROR_INVALID_DATE_FORMAT = "Error, invalid date format";
    public static final String ERROR_START_DATE_BEFORE_END = "Error, start date must be before end date";
}
