import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
public class Presentation extends JFrame {

	private static final long serialVersionUID = 4007551965360932344L;
	DataLayer dataLayer = new DataLayer();

    /**
     * Constructor for Presentation Layer.
     */
    public Presentation() {
//        scanner = new Scanner(System.in);
//        System.out.print("Please enter your username: ");
//        String username = scanner.next();
//        System.out.print("\n");
//
//        System.out.print("Please enter your password: ");
//        String password = scanner.next();
//        System.out.print("\n");
//
//        if (dataLayer.loadDriver()) {
//
//            System.out.println("Successfully loaded drivers");
//
//        }
//        else {
//
//            System.out.println("Error loading drivers");
//
//        }
//
//        if (dataLayer.connect(username, password)) {
//
//            System.out.println("Succussfully connected to database");
//
//        }
//        else {
//
//            System.out.println("Error connecting to database");
//            System.exit(1);
//
//        }
//
//        System.out.println(dataLayer.getFacultyInfo("Conor", "", "RIT", ""));;

    	/*---------------- Begin Database Login Prompt ---------------------*/
    	JPanel jpLoginBox = new JPanel(new GridLayout(2,2));
    	JLabel jlUser = new JLabel("Username: ");
    	JLabel jlPass = new JLabel("Password: ");
    	JTextField jtfUser = new JTextField("root");
    	JTextField jtfPass = new JPasswordField("");
    	
    	jpLoginBox.add(jlUser);
    	jpLoginBox.add(jtfUser);
    	jpLoginBox.add(jlPass);
    	jpLoginBox.add(jtfPass);
    	
    	boolean failed = false;
    	
    	do {
    		if (failed) {
    			JOptionPane.showMessageDialog(null, "Incorrect Database Login!", "Database Login Prompt", JOptionPane.ERROR_MESSAGE);
    		}
    		
    		JOptionPane.showMessageDialog(null, jpLoginBox, "Database Login Prompt", JOptionPane.INFORMATION_MESSAGE);
    		failed = true;
    	} while (dataLayer.connect(jtfUser.getText(), jtfPass.getText()));
    	/*---------------- End Database Login Prompt ---------------------*/
    	
    }

    /**
     * Initializes the Presentation Layer.
     * 
     * @param args String[] command-line args
     */
    public static void main(String[] args) {
        new Presentation();
    }
}

/**
 * Accepts Credentials to Login as a Faculty Member.
 * 
 * @author Conor Keegan
 * @author Eli Hopkins
 * @author Evan Hiltzik
 * @author Nicholas Johnson
 */
class loginUser extends JPanel {
	
	private static final long serialVersionUID = 3814139183564072897L;
	
}

/**
 * Accepts Data to Search for.
 * 
 * @author Conor Keegan
 * @author Eli Hopkins
 * @author Evan Hiltzik
 * @author Nicholas Johnson
 */
class queryPanel extends JPanel {

	private static final long serialVersionUID = 5635321639571267414L;
	
}

/**
 * Accepts Data to Insert for a Faculty Member.
 * 
 * @author Conor Keegan
 * @author Eli Hopkins
 * @author Evan Hiltzik
 * @author Nicholas Johnson
 */
class insertPanel extends JPanel {

	private static final long serialVersionUID = -1597640241890801649L;
	
}

/**
 * Shows Results of a Search.
 * 
 * @author Conor Keegan
 * @author Eli Hopkins
 * @author Evan Hiltzik
 * @author Nicholas Johnson
 */
class queryResults extends JPanel {

	private static final long serialVersionUID = -5998403958243395034L;
	
}
