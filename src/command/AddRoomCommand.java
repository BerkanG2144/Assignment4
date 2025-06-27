package command;
import booking.Hotel;
import booking.Room;

import java.sql.SQLOutput;
import java.util.Map;

/**
 * Command to add a room to a hotel.
 */
public class AddRoomCommand implements Command {
    private final Map<Integer, Hotel> hotels;

    public AddRoomCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 6) {
            System.out.println("Error, usage: add room <HotelID> <RoomId> <Category> <Price>");
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[2]);
            int roomNumber = Integer.parseInt(args[3]);
            String category = args[4];
            double price = Double.parseDouble(args[5]);

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println("Error, hotel does not exist");
                return;
            }

            boolean added = hotel.addRoom(new Room(roomNumber, category, price));
            if (!added) {
                System.out.println("Error, room already exists");
                return;
            }

            System.out.println("OK");

        } catch (NumberFormatException e) {
            System.out.println("Error, number format");
        }
    }

    @Override
    public String keyword() {
        return "add room";
    }

}