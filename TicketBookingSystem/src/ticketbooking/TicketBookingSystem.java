package ticketbooking;

import java.io.*;
import java.util.*;

// Seat class
class Seat {
    private String seatNo;
    private boolean isBooked;

    public Seat(String seatNo) {
        this.seatNo = seatNo;
        this.isBooked = false;
    }

    public String getSeatNumber() {
        return seatNo;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        if (isBooked) {
            throw new IllegalStateException("Seat " + seatNo + " is already booked.");
        }
        isBooked = true;
    }

    @Override
    public String toString() {
        return "Seat{" +
            "seatNumber='" + seatNo + '\'' +
            ", isBooked=" + isBooked +
            '}';
    }
}

// Theater class
class Theater {
    private List<Seat> seats;

    public Theater() {
        seats = new ArrayList<>();
    }

    public synchronized void bookSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                seat.book();
                System.out.println(Thread.currentThread().getName() + " successfully booked seat: " + seatNumber);
                return;
            }
        }
        throw new IllegalArgumentException("Seat " + seatNumber + " does not exist.");
    }

    public void loadSeatsFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                seats.add(new Seat(line.trim()));
            }
        }
    }

    public void saveSeatsToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Seat seat : seats) {
                writer.write(seat.getSeatNumber() + (seat.isBooked() ? " (booked)" : "") + "\n");
            }
        }
    }
}
// Customer class
class Customer implements Runnable {
    private Theater theater;
    private String seatNo;

    public Customer(Theater theater, String seatNumber) {
        this.theater = theater;
        this.seatNo = seatNumber;
    }

    @Override
    public void run() {
        try {
            theater.bookSeat(seatNo);
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " failed to book seat " + seatNo + ": " + e.getMessage());
        }
    }
}

// Main class
public class TicketBookingSystem {
    public static void main(String[] args) {
        Theater theater = new Theater();
        String filename = "seats.txt";

        try {
            // Load seat data from file
            theater.loadSeatsFromFile(filename);

            // Create and start customer threads
            Thread customer1 = new Thread(new Customer(theater, "A1"), "Customer 1");
            Thread customer2 = new Thread(new Customer(theater, "A1"), "Customer 2");
            Thread customer3 = new Thread(new Customer(theater, "A2"), "Customer 3");

            customer1.start();
            customer2.start();
            customer3.start();

            // Wait for threads to complete
            customer1.join();
            customer2.join();
            customer3.join();

            // Save updated seat data to file
            theater.saveSeatsToFile(filename);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
