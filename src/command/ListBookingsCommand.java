package command;

import booking.Booking;
import booking.BookingManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * Command to list all active (non-cancelled) bookings sorted by booking ID.
 *
 * @author ujnaa
 */
public class ListBookingsCommand implements Command {

    private final BookingManager bookingManager;

    /**
     * Constructs the command to list bookings.
     *
     * @param bookingManager the manager providing access to all bookings
     */
    public ListBookingsCommand(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Error, invalid list bookings command");
            return;
        }

        List<Booking> bookings = new ArrayList<>(bookingManager.getAllBookings());

        List<Booking> filtered = new ArrayList<>();
        for (Booking b : bookings) {
            if (!b.isCancelled()) {
                filtered.add(b);
            }
        }

        filtered.sort(new Comparator<Booking>() {
            @Override
            public int compare(Booking a, Booking b) {
                return Integer.compare(a.bookingId(), b.bookingId());
            }
        });

        for (Booking booking : filtered) {
            System.out.printf("%d %d %s %s%n",
                    booking.bookingId(),
                    booking.customer().getCustomerId(),
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
