import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flight {


    private static int counter = 0;
    private int id ;
    private int numOfSeats;
    private String destination;
    private String departureAirPort;
    private double price;
    private boolean availability;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime departureDateTime;
    private int numOfTickets = 80;
    private ArrayList<Ticket> ticket = new ArrayList<>();


    public Flight(int numOfSeats, String destination, String departureAirPort,
                  double price, boolean availability, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, int numOfTickets) {
        this.numOfSeats = numOfSeats;
        this.destination = destination;
        this.departureAirPort = departureAirPort;
        this.price = price;
        this.availability = availability;
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.numOfTickets = numOfTickets;
    }

    public Flight(boolean increment){
        if (increment)
        this.id = ++counter;
        else
            this.id = counter;
    }
//    public Flight(){
//        this.id = ++counter;
//    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartureAirPort() {
        return departureAirPort;
    }

    public void setDepartureAirPort(String departureAirPort) {
        this.departureAirPort = departureAirPort;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public ArrayList<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(ArrayList<Ticket> ticket) {
        this.ticket = ticket;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }







}
