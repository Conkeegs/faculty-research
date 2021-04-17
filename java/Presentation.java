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

        System.out.println(dataLayer.getFacultyInfo("Conor", "", "RIT", ""));;

    }

    /**
     * The main method for this class
     */
    public static void main(String[] args) {

        new Presentation();

    }

}