package command;

import booking.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookCommand implements Command {

    private final Map<Integer, Hotel> hotels;
    private final CustomerManager customerManager;
    private final BookingManager bookingManager;

    public BookCommand(Map<Integer, Hotel> hotels,
                       CustomerManager customerManager,
                       BookingManager bookingManager) {
        this.hotels = hotels;
        this.customerManager = customerManager;
        this.bookingManager = bookingManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length!= 7) {
            System.out.println("Error, invalid format");
            return;
        }

        int hotelId = Integer.parseInt(args[1]);
        int roomNumber = Integer.parseInt(args[2]);
        String fromStr = args[3];
        String toStr = args[4];
        String firstName = args[5];
        String lastName = args[6];

        LocalDate from = LocalDate.parse(fromStr);
        LocalDate to = LocalDate.parse(toStr);

        DateRange range = new DateRange(from, to);

        int customerId = customerManager.getOrAddCustomerId(firstName, lastName);
        Customer customer = customerManager.getCustomer(firstName, lastName);

        Hotel hotel = hotels.get(hotelId);
        if (hotel == null) {
            System.out.println("Error, hotel not found");
            return;
        }
        Room room = hotel.getRooms().get(roomNumber);
        if (room == null) {
            System.out.println("Error, room not found");
            return;
        }

        if (!from.isBefore(to)) {
            System.out.println("Error, invalid date range");
            return;
        }

        if (!room.isAvailable(range)) {
            System.out.println("Error, room not available");
            return;
        }


        Booking booking = bookingManager.createBooking(customer, range);
        room.addBooking(booking);

        System.out.println(booking.bookingId() + " " + customerId);

    }

    @Override
    public String keyword() {
        return "book";
    }

}
