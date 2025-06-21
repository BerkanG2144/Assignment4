package booking;

import java.util.HashMap;
import java.util.Map;

public class Hotel {
    private final int id;
    private final String city;
    private final Map<Integer, Room> rooms;

    public Hotel(int id, String city) {
        this.id = id;
        this.city = city;
        this.rooms = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    //Wenn noch kein Zimmer mit dieser Nummer existiert, wird es eingefügt → Rückgabe: null.
    //Wenn schon ein Zimmer mit dieser Nummer da ist, passiert nichts → Rückgabe: das bereits vorhandene Booking.Room-Objekt.
    public boolean addRoom(Room room) {
        return rooms.putIfAbsent(room.getNumber(), room) == null;
    }

    public Room removeRoom(int number) {
        return rooms.remove(number);
    }

}
