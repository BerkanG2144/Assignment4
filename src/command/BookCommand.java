package command;

import booking.BookingManager;
import booking.CustomerManager;
import booking.Hotel;
import booking.Constants;
import booking.Room;
import booking.DateRange;
import booking.Customer;
import booking.Booking;

import java.time.LocalDate;
import java.util.Map;

/**
 * Command to book a room for a customer.
 *
 * A new booking and customer are created if valid. If a customer already exists
 * (same name), their existing ID is reused.
 *
 * @author ujnaa
 */
public class BookCommand implements Command {

    private final Map<Integer, Hotel> hotels;
    private final CustomerManager customerManager;
    private final BookingManager bookingManager;

    /**
     * Constructs the book command with shared hotel, customer, and booking data.
     *
     * @param hotels the map of hotels
     * @param customerManager the manager for customer registration
     * @param bookingManager the manager for booking creation
     */
    public BookCommand(Map<Integer, Hotel> hotels,
                       CustomerManager customerManager,
                       BookingManager bookingManager) {
        this.hotels = hotels;
        this.customerManager = customerManager;
        this.bookingManager = bookingManager;
    }

    /**
     * Executes the booking command.
     * Expects 7 arguments: hotel ID, room number, from date, to date, first name, last name.
     *
     * @param args the user input split into arguments
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 7) {
            System.out.println(Constants.ERROR_INVALID_FORMAT);
            return;
        }

        try {
            int hotelId = Integer.parseInt(args[1]);
            int roomNumber = Integer.parseInt(args[2]);
            String fromStr = args[3];
            String toStr = args[4];
            String firstName = args[5];
            String lastName = args[6];

            LocalDate from = LocalDate.parse(fromStr);
            LocalDate to = LocalDate.parse(toStr);

            if (!from.isBefore(to)) {
                System.out.println(Constants.ERROR_INVALID_DATE_RANGE);
                return;
            }

            Hotel hotel = hotels.get(hotelId);
            if (hotel == null) {
                System.out.println(Constants.ERROR_HOTEL_NOT_FOUND);
                return;
            }

            Room room = hotel.getRooms().get(roomNumber);
            if (room == null) {
                System.out.println(Constants.ERROR_ROOM_NOT_FOUND);
                return;
            }

            DateRange range = new DateRange(from, to);

            if (!room.isAvailable(range)) {
                System.out.println(Constants.ERROR_ROOM_NOT_AVAILABLE);
                return;
            }

            int customerId = customerManager.getOrAddCustomerId(firstName, lastName);
            Customer customer = customerManager.getCustomer(firstName, lastName);

            Booking booking = bookingManager.createBooking(customer, range);
            room.addBooking(booking);

            System.out.println(booking.bookingId() + " " + customerId);

        } catch (NumberFormatException e) {
            System.out.println(Constants.ERROR_INVALID_NUMBER_FORMAT);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println(Constants.ERROR_INVALID_DATE_FORMAT);
        }
    }


        /**
         * Returns the keyword that triggers this command.
         *
         * @return the keyword "book"
         */
    @Override
    public String keyword() {
        return Constants.COMMAND_BOOK;
    }
}
