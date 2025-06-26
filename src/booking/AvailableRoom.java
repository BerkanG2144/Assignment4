package booking;

/**
 * Represents an available room for display in the find available command.
 * Contains hotel ID, room number, and price per night.
 *
 * @author uXXXX
 */
public final class AvailableRoom {

    private final int hotelId;
    private final int roomNumber;
    private final double price;

    /**
     * Constructs an available room result object.
     *
     * @param hotelId the ID of the hotel
     * @param roomNumber the number of the room
     * @param price the price per night
     */
    public AvailableRoom(int hotelId, int roomNumber, double price) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.price = price;
    }

    /**
     * Returns the hotel ID.
     *
     * @return the hotel ID
     */
    public int hotelId() {
        return hotelId;
    }

    /**
     * Returns the room number.
     *
     * @return the room number
     */
    public int roomNumber() {
        return roomNumber;
    }

    /**
     * Returns the price per night.
     *
     * @return the price
     */
    public double price() {
        return price;
    }
}
