import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
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
	private static DataLayer dLayer;

    /**
     * Constructor for Presentation Layer.
     * 
     * @param mode int 0 for normal mode, 1 for faculty login adding mode
     */
    public Presentation(int mode) {
    	// Set window title
    	super("ISTE-330 Group Project - Faculty Research Database");
    	
    	System.out.println("Faculty Research Database\nISTE-330\nBy: Conor Keegan, Eli Hopkins, Evan Hiltzik");
    	
    	// Try to load the database driver
        if (dLayer.loadDriver()) {
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
    	} while (!dLayer.connect(jtfUser.getText(), jtfPass.getText()));
    	/*---------------- End Database Login Prompt ---------------------*/

		// System.out.println(dLayer.getStudentInfo("", "", "RIT", new ArrayList<String>(Arrays.asList("C++"))));
    	
        if (mode == 0) {
            // Set close operation, make JFrame visible, and fit window to component size
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            pack();

            // Center window on screen
            setLocationRelativeTo(null);
        } else {
            int id;
            Scanner sc = new Scanner(System.in);

            do {
                System.out.println("------------------------------------------------------------\n"
                     + "Add Faculty Login Info\n"
                     + "------------------------------------------------------------\n");

                System.out.print("Enter Faculty ID to add a login to (-1 to exit): ");
                id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Username for Faculty with ID " + id + ": ");
                String uname = sc.nextLine();

                String pw;
                // If the console object is null on the system running this, the password cannot be obscured
                if (System.console() != null) {
                    pw = new String(System.console().readPassword("Enter new password for " + uname + ": "));
                } else {
                    System.out.print("Enter new password for " + uname + ": ");
                    pw = sc.nextLine();
                }

                // TODO: Add method from DataLayer to add login info to faculty user
            } while (id != -1);

            sc.close();

            System.exit(0);
        }
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
    
    public static Presentation getPLayer() {
    	return pLayer;
    }
    
    public static DataLayer getDLayer() {
    	return dLayer;
    }
    
    /**
     * Initializes the Presentation Layer.
     * 
     * @param args String[] command-line args
     */
    public static void main(String[] args) {
    	dLayer = new DataLayer();
        if (args[0].equals("1")) {
            pLayer = new Presentation(1);
        } else {
            pLayer = new Presentation(0);
            // Open the faculty login panel
            setOpenedPanel(new loginUser());
        }
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
		JTextField jtfUsername = new JTextField(20);
		JTextField jtfPass = new JPasswordField(20);
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
	
	private final String FAC = "Faculty";
	private final String STUD = "Student";
	
	private JPanel jpQueryFields;
	private String openedFields;
	
	public queryPanel() {
		// Instantiate components
		JComboBox<String> jcbFacStud = new JComboBox<String>();
		jpQueryFields = new JPanel();
		
		// Set layout and add a border around the form
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		// Add items to dropdown box
		jcbFacStud.addItem(FAC);
		jcbFacStud.addItem(STUD);
		
		// Default to faculty
		jcbFacStud.setSelectedItem(FAC);
		
		// Create faculty form
		queryFAC();
		
		jcbFacStud.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Might not need the portion of the if statement to check which field is stored in openedFields
				 * depending on whether or not this event triggers if the choice is unchanged
				 */
				if (jcbFacStud.getSelectedItem().equals(FAC) && !openedFields.equals(FAC)) {
					queryFAC();
				}
				if (jcbFacStud.getSelectedItem().equals(STUD) && !openedFields.equals(STUD)) {
					querySTUD();
				}
			}
		});
		
		add(jcbFacStud, BorderLayout.NORTH);
	}
	
	private void queryFAC() {
		// Set tracker variable and remove existing panel
		openedFields = FAC;
		remove(jpQueryFields);
		
		jpQueryFields = new JPanel();
		
		// Instantiate components
		JPanel jpFName = new JPanel();
		JPanel jpLName = new JPanel();
		JPanel jpSchool = new JPanel();
		JPanel jpAbstract = new JPanel();
		JPanel jpKeywords = new JPanel();
		JTextField jtfFName = new JTextField(20);
		JTextField jtfLName = new JTextField(20);
		JTextField jtfSchool = new JTextField(20);
		JTextField jtfKeywords = new JTextField(20);
		JTextArea jtaAbstract = new JTextArea(20, 25);
		JButton jbSubmit = new JButton("Submit");
		
		// Set layout to be vertical
		jpQueryFields.setLayout(new BoxLayout(jpQueryFields, BoxLayout.Y_AXIS));
		
		// Add components to respective panels
		jpFName.add(new JLabel("First Name: "));
		jpFName.add(jtfFName);
		jpLName.add(new JLabel("Last Name: "));
		jpLName.add(jtfLName);
		jpSchool.add(new JLabel("School: "));
		jpSchool.add(jtfSchool);
		jpAbstract.add(new JLabel("Abstract: "));
		jpAbstract.add(jtaAbstract);
		jpKeywords.add(new JLabel("Keywords: "));
		jpKeywords.add(jtfKeywords);
		
		// Add components to form
		jpQueryFields.add(jpFName);
		jpQueryFields.add(jpLName);
		jpQueryFields.add(jpSchool);
		jpQueryFields.add(jpAbstract);
		jpQueryFields.add(jpKeywords);
		jpQueryFields.add(jbSubmit);
		
		jbSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fName = jtfFName.getText();
				String lName = jtfLName.getText();
				String school = jtfSchool.getText();
				String fAbstract = jtaAbstract.getText();
				ArrayList<String> keywords = parseCommaList(jtfKeywords.getText());
				
				// Get results from data layer
				String res = Presentation.getDLayer().getFacultyInfo(fName, lName, school, fAbstract, keywords);
				
				Presentation.setOpenedPanel(new queryResults(res));
			}
		});
		
		add(jpQueryFields, BorderLayout.CENTER);
		Presentation.getPLayer().revalidate();
		Presentation.getPLayer().repaint();
		Presentation.getPLayer().pack();
	}
	
	/**
	 * Switch the Query Panel to be for Students.
	 */
	private void querySTUD() {
		// Set tracker variable and remove the existing panel
		openedFields = STUD;
		remove(jpQueryFields);
		
		jpQueryFields = new JPanel();
		
		// Instantiate components
		JPanel jpFName = new JPanel();
		JPanel jpLName = new JPanel();
		JPanel jpSchool = new JPanel();
		JPanel jpSkills = new JPanel();
		JTextField jtfFName = new JTextField(20);
		JTextField jtfLName = new JTextField(20);
		JTextField jtfSchool = new JTextField(20);
		JTextField jtfSkills = new JTextField(20);
		JButton jbSubmit = new JButton("Submit");
		
		// Set the layout of the form to be vertical
		jpQueryFields.setLayout(new BoxLayout(jpQueryFields, BoxLayout.Y_AXIS));
		
		// Add components to respective panel
		jpFName.add(new JLabel("First Name: "));
		jpFName.add(jtfFName);
		jpLName.add(new JLabel("Last Name: "));
		jpLName.add(jtfLName);
		jpSchool.add(new JLabel("School: "));
		jpSchool.add(jtfSchool);
		jpSkills.add(new JLabel("Skills: "));
		jpSkills.add(jtfSkills);
		
		// Add components to form
		jpQueryFields.add(jpFName);
		jpQueryFields.add(jpLName);
		jpQueryFields.add(jpSchool);
		jpQueryFields.add(jpSkills);
		jpQueryFields.add(jbSubmit);
		
		jbSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fName = jtfFName.getText();
				String lName = jtfLName.getText();
				String school = jtfSchool.getText();
				ArrayList<String> skills = parseCommaList(jtfSkills.getText());
				
				String res = Presentation.getDLayer().getStudentInfo(fName, lName, school, skills);
				
				Presentation.setOpenedPanel(new queryResults(res));
			}
		});
		
		add(jpQueryFields, BorderLayout.CENTER);
		Presentation.getPLayer().revalidate();
		Presentation.getPLayer().repaint();
		Presentation.getPLayer().pack();
	}
	
	/**
	 * Convert Comma Separated List Input to an ArrayList.
	 * 
	 * @param keywords String keywords to convert to an ArrayList
	 * @return ArrayList<String> all input keywords as an ArrayList
	 */
	private ArrayList<String> parseCommaList(String ls) {
		ArrayList<String> ret = new ArrayList<String>();
		// Separate comma-separated keywords
		String[] retArr = ls.split(",");
		
		// Trim whitespace from each keyword and add to return ArrayList
		for (String i: retArr) {
			ret.add(i.trim());
		}
		
		return ret;
	}
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
	
	/**
	 * Initialize Result Panel.
	 * 
	 * @param resContent String Result content to be displayed
	 */
	public queryResults(String resContent) {
		JButton jbBack = new JButton("Back");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Presentation.setOpenedPanel(new queryPanel());
			}
		});
		
		add(new JLabel(resContent));
		add(jbBack);
	}
}
