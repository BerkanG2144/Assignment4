package command;

import booking.*;

import java.util.Map;

public class CancelCommand implements Command {

    private final BookingManager bookingManager;
    private final Map<Integer, Hotel> hotels;

    public CancelCommand(BookingManager bookingManager, Map<Integer, Hotel> hotels) {
        this.bookingManager = bookingManager;
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Error, invalid cancel format");
            return;
        }

        try {
            int bookingId = Integer.parseInt(args[1]);
            int customerId = Integer.parseInt(args[2]);

            Booking booking = bookingManager.getBookingById(bookingId);
            if (booking == null || booking.isCancelled()) {
                System.out.println("Error, booking not found");
                return;
            }

            if (booking.customer().customerId() != customerId) {
                System.out.println("Error, customer mismatch");
                return;
            }

            // Finde das Hotelzimmer und entferne die Buchung auch dort
            for (Hotel hotel : hotels.values()) {
                Room room = hotel.findRoomWithBooking(bookingId);
                if (room != null) {
                    room.removeBooking(bookingId);
                    break;
                }
            }

            bookingManager.cancelBooking(bookingId);
            System.out.println("OK");

        } catch (NumberFormatException e) {
            System.out.println("Error, invalid numbers");
        }
    }

    @Override
    public String keyword() {
        return "cancel";
    }
}
