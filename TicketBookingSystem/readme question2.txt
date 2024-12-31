Ticket Booking System
Overview
The Ticket Booking System is a multi-threaded Java application that simulates the process of booking seats in a theater. It allows customers (simulated by threads) to attempt booking seats, checking if the seat is available and handling conflicts if multiple customers try to book the same seat.

Features
Multi-threading: Simulates concurrent seat booking by creating customer threads.
Seat Booking: Allows customers to book a seat if it's not already booked.
Seat Management: Loads seat data from a file, and saves the booking status back to the file.
Error Handling: Catches errors like seat already booked or seat not found.
Technologies Used
Java: The application is developed using Java, leveraging object-oriented programming and multi-threading concepts.
Classes Overview
Seat: Represents a seat in the theater. Each seat has a seat number and a booking status (booked or available).

Methods:
book(): Books the seat if it is not already booked.
toString(): Returns a string representation of the seat.
Theater: Manages the list of seats in the theater. Provides methods to book seats, load seats from a file, and save seat statuses back to a file.

Methods:
bookSeat(): Attempts to book a specific seat.
loadSeatsFromFile(): Loads seats from a text file.
saveSeatsToFile(): Saves seat information to a file.
Customer: Implements Runnable and represents a customer attempting to book a seat. It runs in a separate thread and interacts with the Theater object to book a seat.

TicketBookingSystem (Main): The entry point of the program. It initializes the theater, loads seats from the file, starts multiple customer threads, and saves the updated seat information back to the file.

File Format for Seats (seats.txt)
The seats.txt file contains a list of seat numbers, one per line. Example:
How to Run
Clone or download the repository.
Place a seats.txt file containing the list of seats in the project directory.
Compile the Java files:
bash
Copy code
javac TicketBookingSystem.java
Run the application:
bash
Copy code
java TicketBookingSystem
The system will simulate seat bookings by customers. After execution, the seat booking status will be saved back to seats.txt.
Example Output
yaml
Copy code
Customer 1 successfully booked seat: A1
Customer 2 failed to book seat A1: Seat A1 is already booked.
Customer 3 successfully booked seat: A2
Error Handling
Seat Already Booked: If a customer attempts to book a seat that's already booked, an error message will be displayed.
Seat Does Not Exist: If a customer tries to book a seat that doesn't exist in the file, an error message will be displayed.