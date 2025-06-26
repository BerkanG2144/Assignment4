package command;

import booking.AvailableRoom;
import booking.DateRange;
import booking.Hotel;
import booking.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindCheapestCommand implements Command{

    private final Map<Integer, Hotel> hotels;


    public FindCheapestCommand(Map<Integer, Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 6) {
            System.out.println("Error, find cheapest <Stadt> <Kategorie> <Startdatum> <Enddatum>");
            return;
        }

        String city = args[2];
        String category = args[3];
        String fromStr = args[4];
        String toStr = args[5];

        String regex = "\\d{4}-\\d{2}-\\d{2}";

        if (!category.equals("Single") && !category.equals("Double") && !category.equals("Suite")) {
            System.out.println("Error, unknown category");
            return;
        }

        if (!fromStr.matches(regex) || !toStr.matches(regex)) {
            System.out.println("Error, invalid date format");
            return;
        }

        LocalDate from = LocalDate.parse(fromStr);
        LocalDate to = LocalDate.parse(toStr);

        DateRange range = new DateRange(from, to);
        List<AvailableRoom> result = new ArrayList<>();

        if (!from.isBefore(to)) {
            System.out.println("Error, invalid date range");
            return;
        }


        // 1. Sammle alle verfügbaren passenden Zimmer
        for (Hotel hotel : hotels.values()) {
            if (!hotel.getCity().equals(city)) {
                continue;
            }

            for (Room room : hotel.getRooms().values()) {
                if (!room.getCategory().equals(category)) {
                    continue;
                }

                if (!room.isAvailable(range)) {
                    continue;
                }

                result.add(new AvailableRoom(hotel.getId(), room.getNumber(), room.getPrice()));
            }
        }

// 2. Finde günstigstes Zimmer aus result-Liste
        AvailableRoom bestRoom = null;
        double bestTotalPrice = Double.MAX_VALUE;

        for (AvailableRoom room : result) {
            double totalPrice = room.price() * ChronoUnit.DAYS.between(from, to);

            if (totalPrice < bestTotalPrice) {
                bestRoom = room;
                bestTotalPrice = totalPrice;
            } else if (totalPrice == bestTotalPrice) {
                if (room.hotelId() < bestRoom.hotelId() ||
                        (room.hotelId() == bestRoom.hotelId() && room.roomNumber() < bestRoom.roomNumber())) {
                    bestRoom = room;
                }
            }
        }

// 3. Ausgabe
        if (bestRoom != null) {
            System.out.printf("%05d %d %.2fe%n", bestRoom.hotelId(), bestRoom.roomNumber(), bestTotalPrice);
        }

    }

}

