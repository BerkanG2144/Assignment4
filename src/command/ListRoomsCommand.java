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
        if (args.length != 2) {
            System.out.println("Error, invalid input");
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
            if (result != 0) {
                return result;
            }
            return Integer.compare(this.room.getNumber(), other.room.getNumber());
        }

        @Override
        public String toString() {
            return String.format("%05d %d %s %.2f€",
                    hotelId,
                    room.getNumber(),
                    room.getCategory(),
                    room.getPrice());
        }
    }

    @Override
    public String keyword() {
        return "list rooms";
    }
}
