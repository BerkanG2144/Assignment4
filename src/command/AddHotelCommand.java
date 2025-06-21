package command;
import booking.Hotel;

import java.util.Map;

/**
 * Command to add a room to a hotel.
 */
public class AddHotelCommand implements Command {
    private final Map<Integer, Hotel> hotels;

    public AddHotelCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 4) {
            System.out.println("Error, usage: add room <HotelID> <City>");
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);
            String city = args[3];

            if (hotels.containsKey(hotelId)) {
                System.out.println("Error, hotel already exists");
                return;
            }

            Hotel newHotel = new Hotel(hotelId, city);
            hotels.put(hotelId, newHotel);
            System.out.println("OK");
        } catch (NumberFormatException e) {
            System.out.println("Error, invalid number format");
        }
    }
}