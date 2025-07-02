package booking;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a hotel with a unique ID, city, and a set of rooms.
 * Each room is identified by a unique room number within the hotel.
 *
 * @author ujnaa
 */
public class Hotel {

    private final int id;
    private final String city;
    private final Map<Integer, Room> rooms;

    /**
     * Constructs a new Hotel.
     *
     * @param id the unique hotel ID
     * @param city the city where the hotel is located
     */
    public Hotel(int id, String city) {
        this.id = id;
        this.city = city;
        this.rooms = new HashMap<>();
    }

    /**
     * Returns the unique hotel ID.
     *
     * @return the hotel ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the city where the hotel is located.
     *
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns an unmodifiable view of the hotel's rooms.
     *
     * @return a map of room number to room
     */
    public Map<Integer, Room> getRooms() {
        return Collections.unmodifiableMap(rooms);
    }

    /**
     * Searches for a room that contains the specified booking ID.
     *
     * @param bookingId the ID of the booking
     * @return the room containing the booking, or null if not found
     */
    public Room findRoomWithBooking(int bookingId) {
        for (Room room : rooms.values()) {
            if (room.hasBooking(bookingId)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Adds a new room to the hotel if no room with the same number exists.
     *
     * @param room the room to be added
     */
    public void addRoom(Room room) {
        rooms.putIfAbsent(room.getNumber(), room);
    }

    /**
     * Removes a room with the given room number.
     *
     * @param number the room number to remove
     */
    public void removeRoom(int number) {
        rooms.remove(number);
    }
}
