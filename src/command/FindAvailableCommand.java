package command;

import booking.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Command to find available rooms in a given city and category for a date range.
 * Prints matching rooms sorted by hotel ID and room number.
 *
 * @author ujnaa
 */
public class FindAvailableCommand implements Command {

    /** Error message when the provided date format does not match the required pattern (YYYY-MM-DD). */
    public static final String ERROR_INVALID_DATE_FORMAT = "Error, invalid date format";
    /** Error message when the start date is not before the end date. */
    public static final String ERROR_START_DATE_BEFORE_END = "Error, start date must be before end date";
    /** Error message when the 'find available' command usage is incorrect. */
    public static final String ERROR_USAGE_FIND_AVAILABLE =
            "Error, find available <City> <Category> <Start> <End>";
    /** Command keyword to search for available rooms. */
    public static final String COMMAND_FIND_AVAILABLE = "find available";

    private static final String OUTPUT_FORMAT = "%05d %d %.2f€%n";
    private static final int EXPECTED_ARGUMENT_COUNT = 6;
    private static final int INDEX_CITY = 2;
    private static final int INDEX_CATEGORY = 3;
    private static final int INDEX_FROM_DATE = 4;
    private static final int INDEX_TO_DATE = 5;
    private static final int EMPTY_LIST_SIZE = 0;
    private final Map<Integer, Hotel> hotels;

    /**
     * Constructs the command with access to the hotel map.
     *
     * @param hotels the map of hotels
     */
    public FindAvailableCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_USAGE_FIND_AVAILABLE);
            return;
        }
        String city = args[INDEX_CITY];
        RoomCategory category = RoomCategory.fromString(args[INDEX_CATEGORY]);
        LocalDate from;
        LocalDate to;

        try {
            from = LocalDate.parse(args[INDEX_FROM_DATE]);
            to = LocalDate.parse(args[INDEX_TO_DATE]);

            if (!from.isBefore(to)) {
                System.out.println(ERROR_START_DATE_BEFORE_END);
                return;
            }

            if (category == null) {
                System.out.println(ERROR_USAGE_FIND_AVAILABLE);
                return;
            }

        } catch (DateTimeParseException e) {
            System.out.println(ERROR_INVALID_DATE_FORMAT);
            return;
        }


        DateRange range = new DateRange(from, to);
        List<AvailableRoom> result = new ArrayList<>();

        for (Hotel hotel : hotels.values()) {
            if (!hotel.getCity().equals(city)) {
                continue;
            }

            for (Room room : hotel.getRooms().values()) {
                if (room.getCategory() == category && room.isAvailable(range)) {
                    result.add(new AvailableRoom(hotel.getId(), room.getNumber(), room.getPrice()));
                }
            }
        }

        result.sort(new Comparator<AvailableRoom>() {
            @Override
            public int compare(AvailableRoom a, AvailableRoom b) {
                int hotelCmp = Integer.compare(a.hotelId(), b.hotelId());
                if (hotelCmp != EMPTY_LIST_SIZE) {
                    return hotelCmp;
                }
                return Integer.compare(a.roomNumber(), b.roomNumber());
            }
        });

        for (AvailableRoom room : result) {
            System.out.printf(OUTPUT_FORMAT, room.hotelId(), room.roomNumber(), room.price());
        }
    }

    @Override
    public String keyword() {
        return COMMAND_FIND_AVAILABLE;
    }

}
