import java.util.Scanner;

public class UserInterface {

    private final Scanner input;

    private static User user = new User(false);



    public UserInterface() {
        input = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("=+      Welcome to alkarawaan wings    +=");
        System.out.println("=========================================");

        mainMenu();
    }

    private void mainMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("1. SignIn");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choose = input.nextInt();

            switch (choose) {
                case 1:
                    Services.signIn(input);
                    System.out.println("Success! You can login now");
                    mainMenu();
                    break;
                case 2:
                    loginMenu();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void loginMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("1. Admin Account");
            System.out.println("2. User Account");
            System.out.println("3. Back to Main Menu");

            int choose = input.nextInt();

            switch (choose) {
                case 1:
                    Scanner input = new Scanner(System.in);
                    String name;
                    String password;

                    do {
                        System.out.println("Please enter your name: ");
                         name = input.nextLine();
                        System.out.println("Please enter your password: ");
                         password = input.nextLine();
                         if (!name.equals("Admin") || !password.equals("admin200"))
                             System.out.println("Name or Password is wrong please try again!");
                    }while(!name.equals("Admin") || !password.equals("admin200"));
                    adminAccountMenu();
                    break;
                case 2:
                    user = Services.login();
                    userAccountMenu();
                    break;
                case 3:
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void userAccountMenu() {
        boolean backToLogin = false;

        while (!backToLogin) {
            System.out.println("1. Display all flights");
            System.out.println("2. Book a flight");
            System.out.println("3. Cancel a flight");
            System.out.println("4. Back to Login Menu");

            int choose = input.nextInt();

            switch (choose) {
                case 1:
                    Services.showAllFlights();
                    break;
                case 2:
                    System.out.println("Booking a flight...");
//                    Services.bookFlight(user);
                    break;
                case 3:
                    System.out.println("Cancelling a flight...");
                    // Add logic to cancel a flight
                    break;
                case 4:
                    backToLogin = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private void adminAccountMenu(){


        boolean backToLogin = false;


        while (!backToLogin) {
            System.out.println("1. Display all flights");
            System.out.println("2. Book a flight");
            System.out.println("3. Cancel a flight");
            System.out.println("4. Add a flight");
            System.out.println("5. Remove a flight");
            System.out.println("6. Back to Login Menu");

            int choose = input.nextInt();

            switch (choose) {
                case 1:
                    Services.showAllFlights();
                    break;
                case 2:
                    System.out.println("Booking a flight...");
                    // Add logic to book a flight
                    break;
                case 3:
                    System.out.println("Cancelling a flight...");
                    // Add logic to cancel a flight
                    break;
                case 4:
                    System.out.println("Adding a Flight: ");
                    Services.addFlight();
                    break;
                case 5:
                    System.out.println("Removing a Flight: ");
                    Services.removeFlight();
                    break;
                case 6:
                    backToLogin = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }






    }

// getters

    public static User getUser() {
        return user;
    }




}
