package booking.booking;

import booking.Room;

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
    private final Map<Integer, booking.Room> rooms;

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
    public Map<Integer, booking.Room> getRooms() {
        return Collections.unmodifiableMap(rooms);
    }

    /**
     * Searches for a room that contains the specified booking ID.
     *
     * @param bookingId the ID of the booking
     * @return the room containing the booking, or null if not found
     */
    public booking.Room findRoomWithBooking(int bookingId) {
        for (booking.Room room : rooms.values()) {
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
     * @return true if the room was added successfully, false if a room with the same number already exists
     */
    public boolean addRoom(booking.Room room) {
        return rooms.putIfAbsent(room.getNumber(), room) == null;
    }

    /**
     * Removes a room with the given room number.
     *
     * @param number the room number to remove
     * @return the removed room, or null if no such room existed
     */
    public Room removeRoom(int number) {
        return rooms.remove(number);
    }
}
