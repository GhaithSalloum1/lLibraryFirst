import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Database {

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Flight> flights = new ArrayList<>();



//          Flights

//seats , dest, departure-ap, arrivalDate, departureDate , Price ,Availability , numOfTickets

    public static Flight flightParse(String str) {
        String[] attributes = str.split("---");
        Flight flight = new Flight(true);

        if (!str.isBlank() && attributes.length >= 9) { // Ensure enough attributes exist
            try {
                flight.setNumOfSeats(Integer.parseInt(attributes[1].trim()));
                flight.setDestination(attributes[2]);
                flight.setDepartureAirPort(attributes[3]);
                flight.setPrice(Double.parseDouble(attributes[6]));
                flight.setAvailability(Boolean.parseBoolean(attributes[7]));

                String arrivalDateTimeString = attributes[4].trim();
                String departureDateTimeString = attributes[5].trim();

                flight.setNumOfTickets(Integer.parseInt(attributes[8]));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                flight.setArrivalDateTime(LocalDateTime.parse(arrivalDateTimeString, formatter));
                flight.setDepartureDateTime(LocalDateTime.parse(departureDateTimeString, formatter));


            } catch (NumberFormatException e) {
                System.out.println("Error parsing number values: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing date-time values: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("General error parsing flight: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid input string format or missing attributes.");
        }

        return flight;
    }


    public static ArrayList<Flight> readFlightsFromFile(String fileName){
//        ArrayList<Flight> flights = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null){
                flights.add(flightParse(line));

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return flights;
    }



    public static void writeFlightsToFile(String fileName, ArrayList<Flight> flights) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            for (Flight flight : flights) {

                // Format the date and time explicitly to avoid the "T"
                String arrivalDateTime = flight.getArrivalDateTime().format(formatter);
                String departureDateTime = flight.getDepartureDateTime().format(formatter);

//Id,numOfSeats,destination,departureAirPort,arrivalDateTime,departureDateTime,price,availability,numOfTickets
                String flightData = flight.getId() +
                        "---" +
                        flight.getNumOfSeats() +
                        "---" +
                        flight.getDestination() +
                        "---" +
                        flight.getDepartureAirPort() +
                        "---" +
                        arrivalDateTime +
                        "---" +
                        departureDateTime +
                        "---" +
                        flight.getPrice() +
                        "---" +
                        flight.isAvailability() +
                        "---"  +
                        flight.getNumOfSeats() +
                        "---";


                writer.write(flightData);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }




//          Users

    public static User parseUser(String str){
        String[] attributes = str.split("---");
        User user = new User(true);
        if (!str.isBlank()) {
            user.setName(attributes[1]);
            user.setPassword(attributes[2]);
            user.setNationality(attributes[3]);
            user.setAge(Integer.parseInt(attributes[4]));
            user.setPurse(Float.parseFloat(attributes[5]));
        }
        return user;
    }


    public static ArrayList<User> readUsersFromFile(String fileName){
//        ArrayList<User> users = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null){
                users.add(parseUser(line));

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }


    public static void writeUsersToFile(String fileName, ArrayList<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new  FileWriter(fileName))) {
            for (User user : users) {
                String userData = user.getId() +
                        "---" +
                        user.getName() +
                        "---" +
                        user.getPassword() +
                        "---" +
                        user.getNationality() +
                        "---" +
                        user.getAge() +
                        "---" +
                        user.getPurse();
                writer.write(userData);
                writer.newLine();
            }
            System.out.println("Users written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

//          Reservations


    public static Ticket parseReservations(String str){
        String[] attributes = str.split("---");
        Ticket ticket = new Ticket(UserInterface.getUser());
        if (!str.isBlank()) {
            ticket.setTicketId(Integer.parseInt(attributes[0]));
            ticket.setTicketClass(TicketClass.valueOf(attributes[1].toUpperCase()));
            ticket.setName(attributes[2]);
            ticket.setNationality(attributes[3]);
            ticket.setDestination(attributes[4]);
            ticket.setPrice(Double.parseDouble(attributes[5]));
            ticket.setBooked(Boolean.parseBoolean(attributes[6]));
        }
        return ticket;
    }




    public static ArrayList<Ticket> readReservationsFromFile(String fileName){

        ArrayList<Ticket> tickets = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null){
                tickets.add(parseReservations(line));

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tickets;
    }



}
