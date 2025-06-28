package command.command;

import booking.Hotel;
import command.Command;

import java.util.Map;

/**
 * Command to add a new hotel to the system.
 *
 * If the hotel ID is already used or the format is incorrect,
 * an error message is printed. Otherwise, a new hotel is added.
 *
 * @author ujnaa
 */
public class AddHotelCommand implements Command {

    private final Map<Integer, Hotel> hotels;

    /**
     * Constructs the command with the shared hotel map.
     *
     * @param hotels the map of hotels, indexed by their hotel ID
     */
    public AddHotelCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * Executes the command to add a hotel.
     *
     * @param args the input arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 4) {
            System.out.println("Error, usage: add hotel <HotelID> <City>");
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);
            String city = args[3];

            if (hotelId < 1 || hotelId > 99999) {
                System.out.println("Error, invalid HotelID");
                return;
            }

            if (city.contains(" ")) {
                System.out.println("Error, usage: add hotel <HotelID> <City>");
                return;
            }

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

    /**
     * Returns the command keyword that this command responds to.
     *
     * @return the keyword "add hotel"
     */
    @Override
    public String keyword() {
        return "add hotel";
    }
}
