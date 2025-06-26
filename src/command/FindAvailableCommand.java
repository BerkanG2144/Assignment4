package command;

import booking.AvailableRoom;
import booking.DateRange;
import booking.Hotel;
import booking.Room;
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
 * @author uXXXX
 */
public class FindAvailableCommand implements Command {

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
        if (args.length != 6) {
            System.out.println("Error, find available < Stadt > < Kategorie > < Datum > < Datum >");
            return;
        }

        String city = args[2];
        String category = args[3];
        LocalDate from;
        LocalDate to;

        try {
            from = LocalDate.parse(args[4]);
            to = LocalDate.parse(args[5]);
        } catch (DateTimeParseException e) {
            System.out.println("Error, invalid date format");
            return;
        }

        if (!from.isBefore(to)) {
            System.out.println("Error, start date must before end date");
            return;
        }

        DateRange range = new DateRange(from, to);
        List<AvailableRoom> result = new ArrayList<>();

        for (Hotel hotel : hotels.values()) {
            if (!hotel.getCity().equals(city)) {
                continue;
            }

            for (Room room : hotel.getRooms().values()) {
                if (room.getCategory().equals(category) && room.isAvailable(range)) {
                    result.add(new AvailableRoom(hotel.getId(), room.getNumber(), room.getPrice()));
                }
            }
        }

        result.sort(Comparator.comparing(AvailableRoom::hotelId).thenComparing(AvailableRoom::roomNumber));

        for (AvailableRoom room : result) {
            System.out.printf("%05d %d %.2fe%n", room.hotelId(), room.roomNumber(), room.price());
        }
    }
}
