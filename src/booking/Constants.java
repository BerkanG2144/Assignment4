package booking;

/**
 * Application wide constants used by several commands.
 *
 * @author ujnaa
 */
public final class Constants {

    /** Maximum allowed hotel ID. */
    public static final int MAX_HOTEL_ID = 99999;

    // Common error messages

    /** Error when usage of add hotel command is incorrect. */
    public static final String ERROR_USAGE_ADD_HOTEL = "Error, usage: add hotel <HotelID> <City>";

    /** Error when hotel ID is invalid. */
    public static final String ERROR_INVALID_HOTEL_ID = "Error, invalid HotelID";

    /** Error when trying to add a hotel that already exists. */
    public static final String ERROR_HOTEL_EXISTS = "Error, hotel already exists";

    /** Error when number format is invalid. */
    public static final String ERROR_INVALID_NUMBER_FORMAT = "Error, invalid number format";

    /** Error when usage of add room command is incorrect. */
    public static final String ERROR_USAGE_ADD_ROOM = "Error, usage: add room <HotelID> <RoomId> <Category> <Price>";

    /** Error when room price is not greater than 0. */
    public static final String ERROR_PRICE_GREATER_ZERO = "Error, price must be greater than 0";

    /** Error when referenced hotel does not exist. */
    public static final String ERROR_HOTEL_DOES_NOT_EXIST = "Error, hotel does not exist";

    /** Error when room already exists. */
    public static final String ERROR_ROOM_ALREADY_EXISTS = "Error, room already exists";

    /** Error when a number cannot be parsed. */
    public static final String ERROR_NUMBER_FORMAT = "Error, number format";

    /** Error when room does not exist. */
    public static final String ERROR_ROOM_DOES_NOT_EXIST = "Error, room does not exist";

    /** Error when hotel was not found. */
    public static final String ERROR_HOTEL_NOT_FOUND = "Error, hotel not found";

    /** Error when room was not found. */
    public static final String ERROR_ROOM_NOT_FOUND = "Error, room not found";

    /** Error when room is not available. */
    public static final String ERROR_ROOM_NOT_AVAILABLE = "Error, room not available";

    /** Error when date range is invalid. */
    public static final String ERROR_INVALID_DATE_RANGE = "Error, invalid date range";

    /** Error when input format is invalid. */
    public static final String ERROR_INVALID_FORMAT = "Error, invalid format";

    /** Error when cancel command format is invalid. */
    public static final String ERROR_INVALID_CANCEL_FORMAT = "Error, invalid cancel format";

    /** Error when booking cannot be found. */
    public static final String ERROR_BOOKING_NOT_FOUND = "Error, booking not found";

    /** Error when customer does not match booking. */
    public static final String ERROR_CUSTOMER_MISMATCH = "Error, customer mismatch";

    /** Error when numeric inputs are invalid. */
    public static final String ERROR_INVALID_NUMBERS = "Error, invalid numbers";

    /** Error when general input is invalid. */
    public static final String ERROR_INVALID_INPUT = "Error, invalid input";

    /** Error when list bookings command is malformed. */
    public static final String ERROR_INVALID_LIST_BOOKINGS_COMMAND = "Error, invalid list bookings command";

    /** Error when quit command is malformed. */
    public static final String ERROR_INVALID_QUIT_COMMAND = "Error, invalid quit command";

    /** Error when command is unknown. */
    public static final String ERROR_UNKNOWN_COMMAND = "Error, unknown command";

    /** Error when category is not recognized. */
    public static final String ERROR_UNKNOWN_CATEGORY = "Error, unknown category";

    /** Error when date format is invalid. */
    public static final String ERROR_INVALID_DATE_FORMAT = "Error, invalid date format";

    /** Error when start date is not before end date. */
    public static final String ERROR_START_DATE_BEFORE_END = "Error, start date must be before end date";

    /** Success message for commands that complete without issues. */
    public static final String MESSAGE_OK = "OK";

    /** Error when find available command format is incorrect. */
    public static final String ERROR_USAGE_FIND_AVAILABLE = "Error, find available < Stadt > < Kategorie > < Datum > < Datum >";

    /** Error when find cheapest command format is incorrect. */
    public static final String ERROR_USAGE_FIND_CHEAPEST = "Error, find cheapest <Stadt> <Kategorie> <Startdatum> <Enddatum>";

    /** Error when remove hotel command format is incorrect. */
    public static final String ERROR_USAGE_REMOVE_HOTEL = "Error, usage: remove hotel <HotelID>";

    /** Error when remove room command format is incorrect. */
    public static final String ERROR_USAGE_REMOVE_ROOM = "Error, usage: remove room <HotelID> <RoomId>";

    /** Keyword used for the add hotel command. */
    public static final String COMMAND_ADD_HOTEL = "add hotel";

    /** Keyword used for the remove hotel command. */
    public static final String COMMAND_REMOVE_HOTEL = "remove hotel";

    /** Keyword used for the add room command. */
    public static final String COMMAND_ADD_ROOM = "add room";

    /** Keyword used for the remove room command. */
    public static final String COMMAND_REMOVE_ROOM = "remove room";

    /** Keyword used for the book command. */
    public static final String COMMAND_BOOK = "book";

    /** Keyword used for the cancel command. */
    public static final String COMMAND_CANCEL = "cancel";

    /** Keyword used for the find available command. */
    public static final String COMMAND_FIND_AVAILABLE = "find available";

    /** Keyword used for the find cheapest command. */
    public static final String COMMAND_FIND_CHEAPEST = "find cheapest";

    /** Keyword used for the list bookings command. */
    public static final String COMMAND_LIST_BOOKINGS = "list bookings";

    /** Keyword used for the list rooms command. */
    public static final String COMMAND_LIST_ROOMS = "list rooms";

    /** Keyword used for the quit command. */
    public static final String COMMAND_QUIT = "quit";

    /** Initial value for booking ID generation. */
    public static final int INITIAL_BOOKING_ID = 1;

    /** Initial value for customer ID generation. */
    public static final int INITIAL_CUSTOMER_ID = 1;

    /** Placeholder ID representing no customer. */
    public static final int ID_NONE = 0;

    /** Detailed message when a date range is invalid. */
    public static final String MESSAGE_INVALID_DATE_RANGE = "Invalid date range: from must be before to.";

    /**
     * Private constructor to prevent instantiation.
     */
    private Constants() {
        // prevent instantiation
    }
}
