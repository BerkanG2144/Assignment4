package command;

import booking.Hotel;
import booking.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Command to list all rooms sorted by hotel ID and room number.
 * Each line contains: {@code <HotelID> <RoomNumber> <Category> <Price>}.
 *
 *
 * @author ujnaa
 */
public class ListRoomsCommand implements Command {

    /** Command keyword to list all hotel rooms. */
    public static final String COMMAND_LIST_ROOMS = "list rooms";
    /** Error message when the input format or content is invalid. */
    public static final String ERROR_INVALID_INPUT = "Error, invalid input";

    private static final int EXPECTED_ARGUMENT_COUNT = 2;
    private static final int EMPTY_LIST = 0;
    private static final String OUTPUT_FORMAT = "%05d %d %s %.2f€";
    private final Map<Integer, Hotel> hotels;

    /**
     * Constructs a command to list all rooms.
     *
     * @param hotels the map of hotels to extract rooms from
     */
    public ListRoomsCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_INVALID_INPUT);
            return;
        }

        List<RoomEntry> entries = new ArrayList<>();

        for (Map.Entry<Integer, Hotel> entry : hotels.entrySet()) {
            int hotelId = entry.getKey();
            Hotel hotel = entry.getValue();
            for (Room room : hotel.getRooms().values()) {
                entries.add(new RoomEntry(hotelId, room));
            }
        }

        Collections.sort(entries);

        for (RoomEntry entry : entries) {
            System.out.println(entry.toString());
        }
    }

    /**
     * Helper class for room sorting and output.
     */
    private record RoomEntry(int hotelId, Room room) implements Comparable<RoomEntry> {

        @Override
        public int compareTo(RoomEntry other) {
            int result = Integer.compare(this.hotelId, other.hotelId);
            if (result != EMPTY_LIST) {
                return result;
            }
            return Integer.compare(this.room.getNumber(), other.room.getNumber());
        }

        @Override
        public String toString() {
            return String.format(OUTPUT_FORMAT,
                    hotelId,
                    room.getNumber(),
                    room.getCategory(),
                    room.getPrice());
        }
    }

    @Override
    public String keyword() {
        return COMMAND_LIST_ROOMS;
    }
}
