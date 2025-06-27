package command;
import booking.Hotel;

import java.util.Map;

/**
 * Command to add a remove a room from a hotel.
 */
public class RemoveHotelCommand implements Command {
    private final Map<Integer, Hotel> hotels;

    public RemoveHotelCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Error, usage: remove hotel <HotelID>");
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);

            if (!hotels.containsKey(hotelId)) {
                System.out.println("Error, hotel not found");
                return;
            }

            hotels.remove(hotelId);
            System.out.println("OK");
        } catch (NumberFormatException e) {
            System.out.println("Error, invalid HotelId");
        }
    }

    @Override
    public String keyword() {
        return "remove hotel";
    }

}