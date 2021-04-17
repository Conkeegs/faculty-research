import java.util.Date;
import java.sql.*;
import java.util.*;

/**
 * This is the frontend code for the GUI part of the faculty research database. This will use the
 * java swing classes to allow users to interact and search the system for faculty and students.
 * 
 * Faculty Research Database
 * ISTE-330
 * 
 * @author Conor Keegan
 * @author Eli Hopkins
 * @author Evan Hiltzik
 * @author Nicholas Johnson
 */
public class Presentation {

    DataLayer dataLayer = new DataLayer();
    Scanner scanner;
    int recordCount = 0;

    /**
     * No-parameter constructor for this class
     */
    public Presentation() {

        scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String username = scanner.next();
        System.out.print("\n");

        System.out.print("Please enter your password: ");
        String password = scanner.next();
        System.out.print("\n");

        if (dataLayer.loadDriver()) {

            System.out.println("Successfully loaded drivers");

        }
        else {

            System.out.println("Error loading drivers");

        }

        if (dataLayer.connect(username, password)) {

            System.out.println("Succussfully connected to database");

        }
        else {

            System.out.println("Error connecting to database");
            System.exit(1);

        }

        System.out.print("\n");
        System.out.print("Please enter a new PassengerID to add: ");
        String passengerID = scanner.next();

        System.out.print("\n");
        System.out.print("Please enter a new first name to add: ");
        String firstName = scanner.next();

        System.out.print("\n");
        System.out.print("Please enter a new last name to add: ");
        String lastName = scanner.next();
        scanner.nextLine();

        System.out.print("\n");
        System.out.println("Please enter a street name to add: ");
        String street = scanner.nextLine();

        System.out.print("\n");
        System.out.print("Please enter a new zip to add: ");
        String zip = scanner.next();
        
        if (dataLayer.addPassenger(passengerID, firstName, lastName, street, zip)) {

            recordCount++;
            System.out.print("\n");
            System.out.println("Successfully added " + firstName + " " + lastName + " to the database.");

        }
        else {

            System.out.print("\n");
            System.out.println("Error adding " + firstName + " " +  lastName + " to the database.");

        }

        System.out.println("The number of rows in the passenger table is now: " + dataLayer.getPassengerCount());

        System.out.print("\n");
        System.out.print("Please enter the last name of the person you want to delete: ");
        lastName = scanner.next();

        System.out.print("\n");
        System.out.print("Please enter the first name of the person you want to delete: ");
        firstName = scanner.next();
        
        if (dataLayer.deletePassenger(lastName, firstName)) {

            System.out.print("\n");
            System.out.println("Successfully deleted passenger with last name " + lastName + " and first name " + firstName);

        }
        else {

            System.out.print("\n");
            System.out.println("Error deleting passenger with last name " + lastName + " and first name " + firstName);

        }

        System.out.print("\n");
        System.out.print("Please enter a PassengerID to update its street: ");
        passengerID = scanner.next();
        scanner.nextLine();

        System.out.print("\n");
        System.out.print("Please enter the new street name for the passenger: ");
        street = scanner.nextLine();

        if (dataLayer.updatePassenger(passengerID, street)) {

            System.out.print("\n");
            System.out.println("Successfully updated passenger with PassengerID " + passengerID + "'s street name to " + street);

        }
        else {

            System.out.print("\n");
            System.out.println("Error updating passenger with PassengerID " + passengerID + "'s street name to " + street);

        }

        dataLayer.staffRoutine();

    }

    /**
     * The main method for this class
     */
    public static void main(String[] args) {

        System.out.println("Keegan, Conor " + new Date());
        new Presentation();

    }

}