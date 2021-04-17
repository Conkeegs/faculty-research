import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	private static JPanel openedPanel = null;
	private static Presentation pLayer;
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
    	
    	// Set window title
    	super("ISTE-330 Group Project - Faculty Research Database");
    	
    	// Try to load the database driver
        if (dataLayer.loadDriver()) {
            System.out.println("Successfully loaded drivers");
        }
        else {
            System.out.println("Error loading drivers");
        }

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
    	
    	// Ask the user for the database login until it's input correctly
    	do {
    		if (failed) {
    			JOptionPane.showMessageDialog(null, "Incorrect Database Login!", "Database Login Prompt", JOptionPane.ERROR_MESSAGE);
    		}
    		
    		JOptionPane.showMessageDialog(null, jpLoginBox, "Database Login Prompt", JOptionPane.INFORMATION_MESSAGE);
    		failed = true;
    	} while (dataLayer.connect(jtfUser.getText(), jtfPass.getText()));
    	/*---------------- End Database Login Prompt ---------------------*/
    	
    	// Open the faculty login panel after logging in to the DB
    	setOpenedPanel(new loginUser());
    	
    	// Set close operation, make JFrame visible, and fit window to component size
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setVisible(true);
    	pack();
    }

    /**
     * Getter for the Currently Opened JPanel.
     * 
     * @return JPanel Opened JPanel
     */
    public static JPanel getOpenedPanel() {
    	return openedPanel;
    }
    
    /**
     * Swaps the Currently Opened Panel for a New One.
     * 
     * @param newPanel New JPanel to add to CENTER in layout
     */
    public static void setOpenedPanel(JPanel newPanel) {
    	// If a JPanel has already been added, remove it
    	if (openedPanel != null) {
    		pLayer.remove(openedPanel);
    	}
    	
    	// Add the JPanel to the layout and update the variable
    	pLayer.add(newPanel, BorderLayout.CENTER);
    	openedPanel = newPanel;
    	pLayer.pack();
    }
    
    /**
     * Initializes the Presentation Layer.
     * 
     * @param args String[] command-line args
     */
    public static void main(String[] args) {
        pLayer = new Presentation();
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
	
	/**
	 * Initializes a loginUser JPanel.
	 */
	public loginUser() {
		// Instantiate Swing components
		JPanel jpLogin = new JPanel();
		JPanel jpUsername = new JPanel();
		JPanel jpPass = new JPanel();
		JPanel jpButtons = new JPanel();
		JTextField jtfUsername = new JTextField();
		JTextField jtfPass = new JPasswordField();
		JButton jbGuest = new JButton("Continue as Guest");
		JButton jbLogin = new JButton("Login");
		
		// Set layouts of respective JPanels
		setLayout(new BorderLayout());
		jpLogin.setLayout(new BoxLayout(jpLogin, BoxLayout.Y_AXIS));
		
		/*
		 * If the user decides to continue as a guest open the query panel
		 * because non-faculty users may only query
		 */
		jbGuest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Presentation.setOpenedPanel(new queryPanel());
			}
		});
		
		// Otherwise login as a faculty user
		jbLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// For future use
			}
		});
		
		// Add components to username panel
		jpUsername.add(new JLabel("Username: "));
		jpUsername.add(jtfUsername);
		
		// Add components to password panel
		jpPass.add(new JLabel("Password: "));
		jpPass.add(jtfPass);
		
		// Add components to button panel
		jpButtons.add(jbGuest);
		jpButtons.add(jbLogin);
		
		// Add panels to login form
		jpLogin.add(jpUsername);
		jpLogin.add(jpPass);
		jpLogin.add(jpButtons);
		
		// Add heading and login form
		add(new JLabel("Login as Faculty"), BorderLayout.NORTH);
		add(jpLogin);
	}
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
