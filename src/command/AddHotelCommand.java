package command;

import booking.Hotel;

import java.util.Map;


/**
 * Command to add a new hotel to the system.
 * If the hotel ID is already used or the format is incorrect,
 * an error message is printed. Otherwise, a new hotel is added.
 *
 * @author ujnaa
 */
public class AddHotelCommand implements Command {

    /** Maximum allowed hotel ID (inclusive). */
    public static final int MAX_HOTEL_ID = 99999;
    /** Success message for valid command execution. */
    public static final String MESSAGE_OK = "OK";
    /** Command keyword to add a hotel. */
    public static final String COMMAND_ADD_HOTEL = "add hotel";
    /** Error message for invalid number format. */
    public static final String ERROR_INVALID_NUMBER_FORMAT = "Error, invalid number format";
    /** Error message when trying to add a hotel that already exists. */
    public static final String ERROR_HOTEL_EXISTS = "Error, hotel already exists";
    /** Error message when hotel ID is not valid. */
    public static final String ERROR_INVALID_HOTEL_ID = "Error, invalid HotelID";
    /** Error message when usage of 'add hotel' command is incorrect. */
    public static final String ERROR_USAGE_ADD_HOTEL = "Error, usage: add hotel <HotelID> <City>";

    private static final int EXPECTED_ARGUMENT_COUNT = 4;
    private static final int HOTEL_ID_INDEX = 2;
    private static final int CITY_INDEX = 3;
    private static final int MIN_HOTEL_ID = 1;
    private static final String SPACE = " ";

    private final Map<Integer, Hotel> hotels;

    /**
     * Constructs the command with the shared hotel map.
     *
     * @param hotels the map of hotels, indexed by their hotel ID
     */
    public AddHotelCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * Executes the command to add a hotel.
     *
     * @param args the input arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_USAGE_ADD_HOTEL);
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[HOTEL_ID_INDEX]);
            String city = args[CITY_INDEX];

            if (hotelId < MIN_HOTEL_ID || hotelId > MAX_HOTEL_ID) {
                System.out.println(ERROR_INVALID_HOTEL_ID);
                return;
            }

            if (city.contains(SPACE)) {
                System.out.println(ERROR_USAGE_ADD_HOTEL);
                return;
            }

            if (hotels.containsKey(hotelId)) {
                System.out.println(ERROR_HOTEL_EXISTS);
                return;
            }

            Hotel newHotel = new Hotel(hotelId, city);
            hotels.put(hotelId, newHotel);
            System.out.println(MESSAGE_OK);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_NUMBER_FORMAT);
        }
    }

    /**
     * Returns the command keyword that this command responds to.
     *
     * @return the keyword "add hotel"
     */
    @Override
    public String keyword() {
        return COMMAND_ADD_HOTEL;
    }
}
