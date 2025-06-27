package command;

import booking.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListBookingsCommand implements Command{

    private final BookingManager bookingManager;

    public ListBookingsCommand(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Error, invalid list bookings command");
            return;
        }

        List<Booking> bookings = bookingManager.getAllBookings().stream()
                .sorted(Comparator.comparingInt(Booking::bookingId))
                .toList();

        for (Booking booking : bookings) {
            System.out.printf("%d %d %s %s%n",
                    booking.bookingId(),
                    booking.customer().customerId(),
                    booking.dateRange().from(),
                    booking.dateRange().to()
            );
        }
    }

    @Override
    public String keyword() {
        return "list bookings";
    }

}
