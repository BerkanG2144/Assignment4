package command;

import booking.*;

import java.time.LocalDate;
import java.util.Map;


/**
 * Command to book a room for a customer.
 * A new booking and customer are created if valid. If a customer already exists
 * (same name), their existing ID is reused.
 *
 * @author ujnaa
 */
public class BookCommand implements Command {

    /** Error message for general invalid input format. */
    public static final String ERROR_INVALID_FORMAT = "Error, invalid format";
    /** Error message for incorrectly formatted date input. */
    public static final String ERROR_INVALID_DATE_FORMAT = "Error, invalid date format";
    /** Command keyword to book a hotel room. */
    public static final String COMMAND_BOOK = "book";
    /** Error message when the date range is not valid (e.g., start is after end). */
    public static final String ERROR_INVALID_DATE_RANGE = "Error, invalid date range";
    /** Error message when the room is not available during the requested period. */
    public static final String ERROR_ROOM_NOT_AVAILABLE = "Error, room not available";
    /** Error message when a room with the given ID was not found. */
    public static final String ERROR_ROOM_NOT_FOUND = "Error, room not found";
    /** Error message when a hotel with the given ID was not found. */
    public static final String ERROR_HOTEL_NOT_FOUND = "Error, hotel not found";
    /** Error message when number format is invalid (e.g., non-numeric input). */
    public static final String ERROR_INVALID_NUMBER_FORMAT = "Error, invalid number format";

    private static final int EXPECTED_ARGUMENT_COUNT = 7;
    private static final int INDEX_HOTEL_ID = 1;
    private static final int INDEX_ROOM_NUMBER = 2;
    private static final int INDEX_FROM_DATE = 3;
    private static final int INDEX_TO_DATE = 4;
    private static final int INDEX_FIRST_NAME = 5;
    private static final int INDEX_LAST_NAME = 6;
    private static final String SPACE = " ";
    private final Map<Integer, Hotel> hotels;
    private final CustomerManager customerManager;
    private final BookingManager bookingManager;

    /**
     * Constructs the book command with shared hotel, customer, and booking data.
     *
     * @param hotels the map of hotels
     * @param customerManager the manager for customer registration
     * @param bookingManager the manager for booking creation
     */
    public BookCommand(Map<Integer, Hotel> hotels,
                       CustomerManager customerManager,
                       BookingManager bookingManager) {
        this.hotels = hotels;
        this.customerManager = customerManager;
        this.bookingManager = bookingManager;
    }

    /**
     * Executes the booking command.
     * Expects 7 arguments: hotel ID, room number, from date, to date, first name, last name.
     *
     * @param args the user input split into arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_INVALID_FORMAT);
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[INDEX_HOTEL_ID]);
            int roomNumber = Integer.parseInt(args[INDEX_ROOM_NUMBER]);
            String fromStr = args[INDEX_FROM_DATE];
            String toStr = args[INDEX_TO_DATE];
            String firstName = args[INDEX_FIRST_NAME];
            String lastName = args[INDEX_LAST_NAME];

            LocalDate from = LocalDate.parse(fromStr);
            LocalDate to = LocalDate.parse(toStr);

            if (!from.isBefore(to)) {
                System.out.println(ERROR_INVALID_DATE_RANGE);
                return;
            }

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println(ERROR_HOTEL_NOT_FOUND);
                return;
            }

            Room room = hotel.getRooms().get(roomNumber);
            if (room == null) {
                System.out.println(ERROR_ROOM_NOT_FOUND);
                return;
            }

            DateRange range = new DateRange(from, to);

            if (!room.isAvailable(range)) {
                System.out.println(ERROR_ROOM_NOT_AVAILABLE);
                return;
            }

            int customerId = customerManager.getOrAddCustomerId(firstName, lastName);
            Customer customer = customerManager.getCustomer(firstName, lastName);

            Booking booking = bookingManager.createBooking(customer, range);
            room.addBooking(booking);

            System.out.println(booking.bookingId() + SPACE + customerId);

        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_NUMBER_FORMAT);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(ERROR_INVALID_DATE_FORMAT);
        }
    }


        /**
         * Returns the keyword that triggers this command.
         *
         * @return the keyword "book"
         */
    @Override
    public String keyword() {
        return COMMAND_BOOK;
    }
}
