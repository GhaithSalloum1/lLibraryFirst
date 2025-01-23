import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Services {


        static ArrayList<User> existingUsers = Database.readUsersFromFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\passengers.txt");
        static ArrayList<Flight> existingFlights = Database.readFlightsFromFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\flights.txt");

//                      Made Only For Admins! so they can delete/add a flight
public static void addFlight() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Flight> flights = Database.readFlightsFromFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\flights.txt");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.print("Enter Number of Seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter Departure Airport: ");
        String departure = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Is it available (true/false): ");
        boolean availability = scanner.nextBoolean();
        scanner.nextLine();

        try {
                System.out.print("Enter Departure DateTime (YYYY-MM-DD HH:mm): ");
                String departureTimeInput = scanner.nextLine();
                LocalDateTime departureDateTime = LocalDateTime.parse(departureTimeInput, formatter);

                System.out.print("Enter Arrival DateTime (YYYY-MM-DD HH:mm): ");
                String arrivalTimeInput = scanner.nextLine();
                LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalTimeInput, formatter);

                System.out.println("Enter Number Of Available Tickets: ");
                int tickets = scanner.nextInt();

                flights.add(new Flight(seats, destination, departure, price, availability, arrivalDateTime, departureDateTime,tickets));



                Database.writeFlightsToFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\flights.txt",flights);
                System.out.println("Flight added successfully!");

        } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use 'YYYY-MM-DD HH:mm'.");
        }
}

        public static void removeFlight() {
                Scanner scanner = new Scanner(System.in);
                ArrayList<Flight> flights = Database.readFlightsFromFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\flights.txt");

                System.out.print("Enter Flight ID to remove: ");
                int id = scanner.nextInt();

                boolean removed = flights.removeIf(flight -> flight.getId() == id);

                if (removed) {
                        Database.writeFlightsToFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\flights.txt",flights);
                        System.out.println("Flight removed successfully!");
                } else {
                        System.out.println("No flight found with ID: " + id);
                }
        }

//                      Made For Both Users And Admins! so they can book/cancel flights

        public static void bookFlight(User passenger){
                int choose;
                Scanner scanner = new Scanner(System.in);
        Ticket ticket = new Ticket(passenger);
        do {
                System.out.println("Which flight do you want to book?");
                choose = scanner.nextInt();
                for (Flight flight : existingFlights)
                        if (choose == flight.getId()){
                                ticket.destination = flight.getDestination();
                                System.out.println(ticket.destination);
                        }
        }while(choose > existingFlights.size() || choose < 0);
        scanner.nextLine();



        }

        public static void cancelBooking(){







        }


//                      Login for users
        public static User login(){
                User user = new User(false);
                Scanner input = new Scanner(System.in);
                ArrayList<User> users = Database.readUsersFromFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\passengers.txt");
                if (users.isEmpty())
                {
                        System.out.println("There are no users");
                        return null;
                }
                while(true) {
                        System.out.println("Please enter your name:");
                        user.name = input.nextLine();
                        System.out.println("Please enter your password: ");
                        user.password = input.nextLine();
                                for (User u : users) {
                                        if (user.name.equals(u.getName()) && user.password.equals(u.getPassword())) {
                                                System.out.println("Login was successful");
                                                return u;
                                        }
                                }
                                System.out.println("The name or password is wrong");
                }
        }


//                      SignIn for users
        public static void signIn(Scanner input) {

//                ArrayList<User> existingUsers = Database.readUsersFromFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\passengers.txt");

                User newUser = new User(true);

                input.nextLine();
                System.out.println("Please enter your name: ");
                newUser.setName(input.nextLine());
                System.out.println("Please enter your password: ");
                newUser.setPassword(input.nextLine());
                System.out.println("Please enter your nationality: ");
                newUser.setNationality(input.nextLine());
                System.out.println("Please enter your age: ");
                newUser.setAge(input.nextInt());
                System.out.println("Please enter your purse: ");
                newUser.setPurse(input.nextFloat());


                ArrayList<User> allUsers = new ArrayList<>();
                if (existingUsers != null) {
                        allUsers.addAll(existingUsers);
                }
                allUsers.add(newUser);


                Database.writeUsersToFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\passengers.txt", allUsers);

                System.out.println("User created successfully!");
        }




//                      show all flights
        public static void showAllFlights(){

//                ArrayList<Flight> existingFlights = Database.readFlightsFromFile("C:\\Users\\Ghaith\\IdeaProjects\\lLibraryFirst\\src\\flights.txt");

                System.out.println("+============+========+=================+=================+==========+============+======================+======================+=========+");
                System.out.printf("| %-10s | %-6s | %-15s | %-15s | %-8s | %-10s | %-20s | %-20s | %-7s |\n",
                        "ID", "Seats", "Destination", "Departure-AP", "Price", "Available", "Departure DateTime", "Arrival DateTime", "Tickets");
                System.out.println("+============+========+=================+=================+==========+============+======================+======================+=========+");

                for (Flight schedule : existingFlights) {
                        System.out.printf("| %-10s | %-6d | %-15s | %-15s | $%-7.2f | %-10s | %-20s | %-20s | %-7d |\n",
                                schedule.getId(),
                                schedule.getNumOfSeats(),
                                schedule.getDestination(),
                                schedule.getDepartureAirPort(),
                                schedule.getPrice(),
                                schedule.isAvailability() ? "Yes" : "No",
                                schedule.getDepartureDateTime(),
                                schedule.getArrivalDateTime(),
                                schedule.getNumOfTickets());
                        printLine();
                }

        }

        private static void printLine() {
                System.out.println("+------------+--------+-----------------+-----------------+----------+------------+----------------------+----------------------+---------+");
        }






}
