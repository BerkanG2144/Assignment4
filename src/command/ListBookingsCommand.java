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

    /** Error message when the 'list bookings' command usage is incorrect. */
    public static final String ERROR_INVALID_LIST_BOOKINGS_COMMAND = "Error, invalid list bookings command";
    /** Command keyword to list all active bookings. */
    public static final String COMMAND_LIST_BOOKINGS = "list bookings";

    private static final String OUTPUT_FORMAT = "%d %d %s %s%n";
    private static final int EXPECTED_ARGUMENT_COUNT = 2;
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
        if (args.length != EXPECTED_ARGUMENT_COUNT) {
            System.out.println(ERROR_INVALID_LIST_BOOKINGS_COMMAND);
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
            System.out.printf(OUTPUT_FORMAT,
                    booking.bookingId(),
                    booking.customer().getCustomerId(),
                    booking.dateRange().from(),
                    booking.dateRange().to()
            );
        }
    }


    @Override
    public String keyword() {
        return COMMAND_LIST_BOOKINGS;
    }

}
