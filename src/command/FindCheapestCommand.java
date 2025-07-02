package command;

import booking.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Command to find the cheapest available room for a given city, category and date range.
 * The result includes the hotel ID, room number, and total price, based on the number of nights.
 * If multiple rooms have the same lowest price, the one with the lowest hotel ID and room number is selected.
 *
 * @author ujnaa
 */
public class FindCheapestCommand implements Command {

    /** Error message when the specified room category is not recognized. */
    public static final String ERROR_UNKNOWN_CATEGORY = "Error, unknown category";
    /** Error message when the provided date format does not match the required pattern (YYYY-MM-DD). */
    public static final String ERROR_INVALID_DATE_FORMAT = "Error, invalid date format";
    /** Error message when the start date is not before the end date. */
    public static final String ERROR_START_DATE_BEFORE_END = "Error, start date must be before end date";
    /** Error message when the 'find cheapest' command usage is incorrect. */
    public static final String ERROR_USAGE_FIND_CHEAPEST =
            "Error, find cheapest <City> <Category> <Start> <End>";
    /** Command keyword to find the cheapest available room for a given search. */
    public static final String COMMAND_FIND_CHEAPEST = "find cheapest";

    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private static final String OUTPUT_FORMAT = "%05d %d %.2f€%n";
    private static final int EXPECTED_ARGUMENT_COUNT = 6;
    private static final int INDEX_CITY = 2;
    private static final int INDEX_CATEGORY = 3;
    private static final int INDEX_FROM_DATE = 4;
    private static final int INDEX_TO_DATE = 5;


    private final Map<Integer, Hotel> hotels;

    /**
     * Constructs the command with access to the hotel map.
     *
     * @param hotels the map of hotels indexed by hotel IDR
     */
    public FindCheapestCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_USAGE_FIND_CHEAPEST);
            return;
        }
        String city = args[INDEX_CITY];
        RoomCategory category = RoomCategory.fromString(args[INDEX_CATEGORY]);
        String fromStr = args[INDEX_FROM_DATE];
        String toStr = args[INDEX_TO_DATE];
        if (category == null) {
            System.out.println(ERROR_UNKNOWN_CATEGORY);
            return;
        }
        if (!fromStr.matches(DATE_REGEX) || !toStr.matches(DATE_REGEX)) {
            System.out.println(ERROR_INVALID_DATE_FORMAT);
            return;
        }
        LocalDate from = LocalDate.parse(fromStr);
        LocalDate to = LocalDate.parse(toStr);
        if (!from.isBefore(to)) {
            System.out.println(ERROR_START_DATE_BEFORE_END);
            return;
        }
        DateRange range = new DateRange(from, to);
        List<AvailableRoom> result = new ArrayList<>();
        for (Hotel hotel : hotels.values()) {
            if (!hotel.getCity().equals(city)) {
                continue;
            }
            for (Room room : hotel.getRooms().values()) {
                if (room.getCategory() != category) {
                    continue;
                }
                if (!room.isAvailable(range)) {
                    continue;
                }
                result.add(new AvailableRoom(hotel.getId(), room.getNumber(), room.getPrice()));
            }
        }
        AvailableRoom bestRoom = null;
        double bestTotalPrice = Double.MAX_VALUE;
        for (AvailableRoom room : result) {
            int days = (int) (to.toEpochDay() - from.toEpochDay());
            double totalPrice = room.price() * days;
            if (totalPrice < bestTotalPrice) {
                bestRoom = room;
                bestTotalPrice = totalPrice;
            } else if (totalPrice == bestTotalPrice) {
                assert bestRoom != null;
                if (room.hotelId() < bestRoom.hotelId()
                        || (room.hotelId() == bestRoom.hotelId() && room.roomNumber() < bestRoom.roomNumber())) {
                    bestRoom = room;
                }
            }
        }
        if (bestRoom != null) {
            System.out.printf(OUTPUT_FORMAT, bestRoom.hotelId(), bestRoom.roomNumber(), bestTotalPrice);
        }

    }

    @Override
    public String keyword() {
        return COMMAND_FIND_CHEAPEST;
    }


}

