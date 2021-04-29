import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;

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
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;

/**
 * This is the frontend code for the GUI part of the faculty research database.
 * This will use the java swing classes to allow users to interact and search
 * the system for faculty and students.
 * 
 * Faculty Research Database ISTE-330
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

	public static int loggedFacID;

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
		} else {
			System.out.println("Error loading drivers");
		}

		/*---------------- Begin Database Login Prompt ---------------------*/
		JPanel jpLoginBox = new JPanel(new GridLayout(2, 2));
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
				JOptionPane.showMessageDialog(null, "Incorrect Database Login!", "Database Login Prompt",
						JOptionPane.ERROR_MESSAGE);
			}

			JOptionPane.showMessageDialog(null, jpLoginBox, "Database Login Prompt", JOptionPane.INFORMATION_MESSAGE);
			failed = true;
		} while (!dLayer.connect(jtfUser.getText(), jtfPass.getText()));
		/*---------------- End Database Login Prompt ---------------------*/

		// System.out.println(dLayer.getStudentInfo("", "", "RIT", new
		// ArrayList<String>(Arrays.asList("C++"))));

		if (mode == 0) {
			// Set close operation, make JFrame visible, and fit window to component size
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			pack();

			// Center window on screen
			setLocationRelativeTo(null);
		} else if (mode == 1) {
			int id;
			Scanner sc = new Scanner(System.in);

			do {
				System.out.println(
						"------------------------------------------------------------\n" + "Add Faculty Login Info\n"
								+ "------------------------------------------------------------\n");

				System.out.print("Enter Faculty ID to add a login to (-1 to exit): ");
				id = sc.nextInt();
				sc.nextLine();

				if (id == -1) {
					break;
				}

				System.out.print("Enter Username for Faculty with ID " + id + ": ");
				String uname = sc.nextLine();

				String pw;
				// If the console object is null on the system running this, the password cannot
				// be obscured
				if (System.console() != null) {
					pw = new String(System.console().readPassword("Enter new password for " + uname + ": "));
				} else {
					System.out.print("Enter new password for " + uname + ": ");
					pw = sc.nextLine();
				}

				dLayer.addFacLogin(id, uname, pw);
			} while (id != -1);

			sc.close();

			System.exit(0);
		} else if (mode == 2) {

			int id;
			Scanner sc = new Scanner(System.in);

			do {
				System.out.println(
						"------------------------------------------------------------\n" + "Add Student Login Info\n"
								+ "------------------------------------------------------------\n");

				System.out.print("Enter Student ID to add a login to (-1 to exit): ");
				id = sc.nextInt();
				sc.nextLine();

				if (id == -1) {
					break;
				}

				System.out.print("Enter Username for Student with ID " + id + ": ");
				String uname = sc.nextLine();

				String pw;
				// If the console object is null on the system running this, the password cannot
				// be obscured
				if (System.console() != null) {
					pw = new String(System.console().readPassword("Enter new password for " + uname + ": "));
				} else {
					System.out.print("Enter new password for " + uname + ": ");
					pw = sc.nextLine();
				}

				dLayer.addStudLogin(id, uname, pw);
			} while (id != -1);

			sc.close();

			System.exit(0);

		}

		setResizable(false);

	}

	/**
	 * Convert Comma Separated List Input to an ArrayList.
	 * 
	 * @param keywords String keywords to convert to an ArrayList
	 * @return ArrayList<String> all input keywords as an ArrayList
	 */
	public static ArrayList<String> parseCommaList(String ls) {
		ArrayList<String> ret = new ArrayList<String>();
		// Separate comma-separated keywords
		String[] retArr = ls.split(",");

		// Trim whitespace from each keyword and add to return ArrayList
		for (String i : retArr) {
			ret.add(i.trim());
		}

		return ret;
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

		if (args.length != 0 && args[0].equals("1")) {
			pLayer = new Presentation(1);
		} else if (args.length != 0 && args[0].equals("2")) {

			pLayer = new Presentation(2);

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
	private JPanel queryPanelFac = new queryPanel(true);
	private JPanel queryPanelStud = new queryPanel(false);

	/**
	 * Initializes a loginUser JPanel.
	 */
	public loginUser() {
		// Instantiate Swing components
		JPanel jpLogin = new JPanel();
		JPanel jpUsername = new JPanel();
		JPanel jpPass = new JPanel();
		JPanel jpButtons = new JPanel();
		JLabel jlHeader = new JLabel("Login as Faculty/Student");
		JTextField jtfUsername = new JTextField(20);
		JTextField jtfPass = new JPasswordField(20);
		JButton jbGuest = new JButton("Continue as Guest");
		JButton jbLogin = new JButton("Login");

		// Set layouts of respective JPanels
		setLayout(new BorderLayout());
		jpLogin.setLayout(new BoxLayout(jpLogin, BoxLayout.Y_AXIS));

		/*
		 * If the user decides to continue as a guest open the query panel because
		 * non-faculty users may only query
		 */
		jbGuest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Presentation.setOpenedPanel(queryPanelStud);
			}
		});

		// Otherwise login as a faculty user
		jbLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int facResult = Presentation.getDLayer().checkFacLogin(jtfUsername.getText(), jtfPass.getText());
				int studResult = Presentation.getDLayer().checkStudLogin(jtfUsername.getText(), jtfPass.getText());

				if (facResult != -1) {
					Presentation.loggedFacID = facResult;
					Presentation.setOpenedPanel(new facPanel());
				} else if (studResult != -1) {

					Presentation.setOpenedPanel(queryPanelStud);

				} else {
					jtfPass.setText("");
					JOptionPane.showMessageDialog(null, "Your username or password is incorrect.",
							"Invalid Credentials", JOptionPane.ERROR_MESSAGE);
				}
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

		jlHeader.setHorizontalAlignment(JLabel.CENTER);
		jlHeader.setAlignmentX(CENTER_ALIGNMENT);

		// Add heading and login form
		add(jlHeader, BorderLayout.NORTH);
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

	private final String FAC = "Search for Faculty";
	private final String STUD = "Search for Students";

	private JPanel jpQueryFields;
	private String openedFields;
	private boolean inFac;

	public queryPanel(boolean inFac) {
		// Instantiate components
		JComboBox<String> jcbFacStud = new JComboBox<String>();
		jpQueryFields = new JPanel();

		this.inFac = inFac;

		// Set layout and add a border around the form
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
			BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK),
			BorderFactory.createEmptyBorder(15, 15, 15, 15))));

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
				 * Might not need the portion of the if statement to check which field is stored
				 * in openedFields depending on whether or not this event triggers if the choice
				 * is unchanged
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

	/**
	 * This method sets up the page that allows users to search for faculty.
	 */
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
		JButton jbBack = new JButton("Back");

		// general setup of components
		jpQueryFields.setLayout(new BoxLayout(jpQueryFields, BoxLayout.Y_AXIS));
		jtaAbstract.setLineWrap(true);

		// Add components to respective panels
		jpFName.add(new JLabel("First Name: "));
		jpFName.add(jtfFName);
		jpLName.add(new JLabel("Last Name: "));
		jpLName.add(jtfLName);
		jpSchool.add(new JLabel("School: "));
		jpSchool.add(jtfSchool);
		jpAbstract.add(jtaAbstract);
		jpKeywords.add(new JLabel("Keywords: "));
		jpKeywords.add(jtfKeywords);
		JScrollPane jtaAbstractScroll = new JScrollPane(jpAbstract, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add components to form
		jpQueryFields.add(jpFName);
		jpQueryFields.add(jpLName);
		jpQueryFields.add(jpSchool);
		jpQueryFields.add(new JLabel("Abstract: "));
		jpQueryFields.add(jtaAbstractScroll);
		jpQueryFields.add(jpKeywords);
		jpQueryFields.add(jbSubmit);
		jpQueryFields.add(jbBack);

		jbBack.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0), jbBack.getBorder()));
		jbSubmit.setHorizontalAlignment(JButton.CENTER);
		jbSubmit.setAlignmentX(CENTER_ALIGNMENT);
		jbBack.setHorizontalAlignment(JButton.CENTER);
		jbBack.setAlignmentX(CENTER_ALIGNMENT);

		jbSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fName = jtfFName.getText();
				String lName = jtfLName.getText();
				String school = jtfSchool.getText();
				String fAbstract = jtaAbstract.getText();
				ArrayList<String> keywords = Presentation.parseCommaList(jtfKeywords.getText());

				// Get results from data layer
				String res = Presentation.getDLayer().getFacultyInfo(fName, lName, school, fAbstract, keywords);

				Presentation.setOpenedPanel(new queryResults(res, inFac));
			}
		});

		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Presentation.setOpenedPanel(new loginUser());

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
		JButton jbBack = new JButton("Back");

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

		jbBack.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0), jbBack.getBorder()));
		jbSubmit.setHorizontalAlignment(JButton.CENTER);
		jbSubmit.setAlignmentX(CENTER_ALIGNMENT);
		jbBack.setHorizontalAlignment(JButton.CENTER);
		jbBack.setAlignmentX(CENTER_ALIGNMENT);

		// Add components to form
		jpQueryFields.add(jpFName);
		jpQueryFields.add(jpLName);
		jpQueryFields.add(jpSchool);
		jpQueryFields.add(jpSkills);
		jpQueryFields.add(jbSubmit);
		jpQueryFields.add(jbBack);

		jbSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String fName = jtfFName.getText();
				String lName = jtfLName.getText();
				String school = jtfSchool.getText();
				ArrayList<String> skills = Presentation.parseCommaList(jtfSkills.getText());

				String res = Presentation.getDLayer().getStudentInfo(fName, lName, school, skills);

				Presentation.setOpenedPanel(new queryResults(res, inFac));
			}
		});

		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Presentation.setOpenedPanel(new loginUser());

			}
		});

		add(jpQueryFields, BorderLayout.CENTER);
		Presentation.getPLayer().revalidate();
		Presentation.getPLayer().repaint();
		Presentation.getPLayer().pack();
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

	public insertPanel() {
		// Instantiate components
		JPanel jpContents = new JPanel();
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
		JButton jbBack = new JButton("Back");

		// general setup of components
		jpContents.setLayout(new BoxLayout(jpContents, BoxLayout.Y_AXIS));
		jtaAbstract.setLineWrap(true);

		// Add components to respective panels
		jpFName.add(new JLabel("New First Name: "));
		jpFName.add(jtfFName);
		jpLName.add(new JLabel("New Last Name: "));
		jpLName.add(jtfLName);
		jpSchool.add(new JLabel("New School: "));
		jpSchool.add(jtfSchool);
		jpAbstract.add(jtaAbstract);
		jpKeywords.add(new JLabel("New Keywords: "));
		jpKeywords.add(jtfKeywords);
		JScrollPane jtaAbstractScroll = new JScrollPane(jpAbstract, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add components to form
		jpContents.add(jpFName);
		jpContents.add(jpLName);
		jpContents.add(jpSchool);
		jpContents.add(new JLabel("New Abstract: "));
		jpContents.add(jtaAbstractScroll);
		jpContents.add(jpKeywords);
		jpContents.add(jbSubmit);
		jpContents.add(jbBack);

		add(jpContents);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
			BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK),
			BorderFactory.createEmptyBorder(15, 15, 15, 15))));

		jbBack.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0), jbBack.getBorder()));
		jbSubmit.setHorizontalAlignment(JButton.CENTER);
		jbSubmit.setAlignmentX(CENTER_ALIGNMENT);
		jbBack.setHorizontalAlignment(JButton.CENTER);
		jbBack.setAlignmentX(CENTER_ALIGNMENT);

		jbSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if ((jtfFName.getText() == null || jtfFName.getText().equals(""))
						|| (jtfLName.getText() == null || jtfLName.getText().equals(""))
						|| (jtfSchool.getText() == null || jtfSchool.getText().equals(""))
						|| (jtaAbstract.getText() == null || jtaAbstract.getText().equals(""))
						|| (jtfKeywords.getText() == null || jtfKeywords.getText().equals(""))) {

					JOptionPane.showMessageDialog(null, "Please fill out all fields!", "Invalid Insert Information",
							JOptionPane.ERROR_MESSAGE);

				} else {

					jtfFName.setText(jtfFName.getText().replaceAll(" ", ""));
					jtfFName.setText(jtfFName.getText().trim());
					jtfLName.setText(jtfLName.getText().replaceAll(" ", ""));
					jtfLName.setText(jtfLName.getText().trim());
					jtfSchool.setText(jtfSchool.getText().trim());
					jtfKeywords.setText(jtfKeywords.getText().trim());

					String fName = jtfFName.getText();
					String lName = jtfLName.getText();
					String school = jtfSchool.getText();
					String fAbstract = jtaAbstract.getText();
					ArrayList<String> keywords = Presentation.parseCommaList(jtfKeywords.getText());

					if (Presentation.getDLayer().insertFac(fName, lName, school, fAbstract, keywords)) {

						JOptionPane.showMessageDialog(null, "Member successfully inserted!", "Entry Added",
								JOptionPane.INFORMATION_MESSAGE);

					} else {

						JOptionPane.showMessageDialog(null, "Member was unable to be inserted!", "Entry Error",
								JOptionPane.ERROR_MESSAGE);

					}

					jtfFName.setText("");
					jtfLName.setText("");
					jtfSchool.setText("");
					jtaAbstract.setText("");
					jtfKeywords.setText("");

				}

			}
		});

		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Presentation.setOpenedPanel(new loginUser());

			}
		});
	}
}

/**
 * Accepts Data to update a Faculty Member.
 * 
 * @author Conor Keegan
 * @author Eli Hopkins
 * @author Evan Hiltzik
 * @author Nicholas Johnson
 */
class updatePanel extends JPanel {
	private static final long serialVersionUID = -1597640241890801649L;

	public updatePanel() {
		// Instantiate components
		JPanel jpContents = new JPanel();
		JPanel jpID = new JPanel();
		JPanel jpFName = new JPanel();
		JPanel jpLName = new JPanel();
		JPanel jpSchool = new JPanel();
		JPanel jpAbstract = new JPanel();
		JPanel jpKeywords = new JPanel();
		JTextField jtfID = new JTextField(20);
		JTextField jtfFName = new JTextField(20);
		JTextField jtfLName = new JTextField(20);
		JTextField jtfSchool = new JTextField(20);
		JTextField jtfKeywords = new JTextField(20);
		JTextArea jtaAbstract = new JTextArea(20, 25);
		JButton jbSubmit = new JButton("Submit");
		JButton jbBack = new JButton("Back");

		// general setup of components
		jpContents.setLayout(new BoxLayout(jpContents, BoxLayout.Y_AXIS));
		jtaAbstract.setLineWrap(true);

		// Add components to respective panels
		jpID.add(new JLabel("Faculty ID to Update: "));
		jpID.add(jtfID);
		jpFName.add(new JLabel("New First Name: "));
		jpFName.add(jtfFName);
		jpLName.add(new JLabel("New Last Name: "));
		jpLName.add(jtfLName);
		jpSchool.add(new JLabel("New School: "));
		jpSchool.add(jtfSchool);
		jpAbstract.add(jtaAbstract);
		jpKeywords.add(new JLabel("New Keywords: "));
		jpKeywords.add(jtfKeywords);
		JScrollPane jtaAbstractScroll = new JScrollPane(jpAbstract, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add components to form
		jpContents.add(jpID);
		jpContents.add(jpFName);
		jpContents.add(jpLName);
		jpContents.add(jpSchool);
		jpContents.add(new JLabel("New Abstract: "));
		jpContents.add(jtaAbstractScroll);
		jpContents.add(jpKeywords);
		jpContents.add(jbSubmit);
		jpContents.add(jbBack);

		add(jpContents);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
			BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK),
			BorderFactory.createEmptyBorder(15, 15, 15, 15))));

		jbBack.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0), jbBack.getBorder()));
		jbSubmit.setHorizontalAlignment(JButton.CENTER);
		jbSubmit.setAlignmentX(CENTER_ALIGNMENT);
		jbBack.setHorizontalAlignment(JButton.CENTER);
		jbBack.setAlignmentX(CENTER_ALIGNMENT);

		jbSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (jtfID.getText() == null || jtfID.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Please specifiy a faculty ID!", "Invalid Update Information",
							JOptionPane.ERROR_MESSAGE);

				} else {

					try {

						Integer.parseInt(jtfID.getText());

					} catch (final NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "Please enter in a number value for the faculty ID!", "ID Error",
								JOptionPane.ERROR_MESSAGE);
						jtfID.setText("");
					}

					jtfID.setText(jtfID.getText().replaceAll(" ", ""));
					jtfID.setText(jtfID.getText().trim());
					jtfFName.setText(jtfFName.getText().replaceAll(" ", ""));
					jtfFName.setText(jtfFName.getText().trim());
					jtfLName.setText(jtfLName.getText().replaceAll(" ", ""));
					jtfLName.setText(jtfLName.getText().trim());
					jtfSchool.setText(jtfSchool.getText().trim());
					jtfKeywords.setText(jtfKeywords.getText().trim());

					String facID = jtfID.getText();
					String fName = jtfFName.getText();
					String lName = jtfLName.getText();
					String school = jtfSchool.getText();
					String fAbstract = jtaAbstract.getText();
					ArrayList<String> keywords = Presentation.parseCommaList(jtfKeywords.getText());

					if (Presentation.getDLayer().updateFac(facID, fName, lName, school, fAbstract, keywords)) {

						JOptionPane.showMessageDialog(null, "Member successfully updated!", "Entry Updated",
								JOptionPane.INFORMATION_MESSAGE);

					} else {

						JOptionPane.showMessageDialog(null, "Member was unable to be updated!", "Entry Error",
								JOptionPane.ERROR_MESSAGE);

					}

					jtfID.setText("");
					jtfFName.setText("");
					jtfLName.setText("");
					jtfSchool.setText("");
					jtaAbstract.setText("");
					jtfKeywords.setText("");

				}

			}
		});

		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Presentation.setOpenedPanel(new loginUser());

			}
		});
	}
}

/**
 * Accepts Data to update a Faculty Member.
 * 
 * @author Conor Keegan
 * @author Eli Hopkins
 * @author Evan Hiltzik
 * @author Nicholas Johnson
 */
class deletePanel extends JPanel {
	private static final long serialVersionUID = -1597640241890801649L;

	public deletePanel() {
		// Instantiate components
		JPanel jpContents = new JPanel();
		JPanel jpID = new JPanel();
		JTextField jtfID = new JTextField(20);
		JButton jbSubmit = new JButton("Submit");
		JButton jbBack = new JButton("Back");

		// general setup of components
		jpContents.setLayout(new BoxLayout(jpContents, BoxLayout.Y_AXIS));

		// Add components to respective panels
		jpID.add(new JLabel("Faculty ID: "));
		jpID.add(jtfID);

		// Add components to form
		jpContents.add(jpID);
		jpContents.add(jbSubmit);
		jpContents.add(jbBack);

		add(jpContents);

		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
			BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.GRAY, Color.BLACK),
			BorderFactory.createEmptyBorder(15, 15, 15, 15))));

		jbBack.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0), jbBack.getBorder()));
		jbSubmit.setHorizontalAlignment(JButton.CENTER);
		jbSubmit.setAlignmentX(CENTER_ALIGNMENT);
		jbBack.setHorizontalAlignment(JButton.CENTER);
		jbBack.setAlignmentX(CENTER_ALIGNMENT);

		jbSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (jtfID.getText() == null || jtfID.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Please specifiy a faculty ID to delete!", "Invalid Delete Information",
							JOptionPane.ERROR_MESSAGE);

				} else {

					try {

						Integer.parseInt(jtfID.getText());

					} catch (final NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "Please enter in a number value for the faculty ID!", "ID Error",
								JOptionPane.ERROR_MESSAGE);
						jtfID.setText("");
					}

					jtfID.setText(jtfID.getText().replaceAll(" ", ""));
					jtfID.setText(jtfID.getText().trim());

					String facID = jtfID.getText();

					if (Presentation.getDLayer().deleteFac(facID)) {

						JOptionPane.showMessageDialog(null, "Member successfully deleted!", "Entry Deleted",
								JOptionPane.INFORMATION_MESSAGE);

					} else {

						JOptionPane.showMessageDialog(null, "Member was unable to be deleted!", "Delete Error",
								JOptionPane.ERROR_MESSAGE);

					}

					jtfID.setText("");

				}

			}
		});

		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Presentation.setOpenedPanel(new loginUser());

			}
		});
	}
}

/**
 * This class sets up the JPanel that appears when a user signs in as a faculty
 * member.
 */
class facPanel extends JPanel {
	private final String INS = "Insert Faculty";
	private final String UPDATE = "Update Faculty";
	private final String DELETE = "Delete Faculty";
	private final String QUERY = "Query";
	private JPanel openedPanel;
	private JPanel queryPanelFac = new queryPanel(true);

	public facPanel() {
		JComboBox<String> jcbInsQuery = new JComboBox<String>();
		JPanel contents = new JPanel();

		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));

		jcbInsQuery.addItem(INS);
		jcbInsQuery.addItem(UPDATE);
		jcbInsQuery.addItem(DELETE);
		jcbInsQuery.addItem(QUERY);

		jcbInsQuery.setSelectedItem(INS);

		openedPanel = new insertPanel();

		jcbInsQuery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Might not need the portion of the if statement to check which field is stored
				 * in openedFields depending on whether or not this event triggers if the choice
				 * is unchanged
				 */
				if (jcbInsQuery.getSelectedItem().equals(INS) && !(openedPanel instanceof insertPanel)) {
					contents.remove(openedPanel);
					openedPanel = new insertPanel();
					contents.add(openedPanel);
					Presentation.getPLayer().revalidate();
					Presentation.getPLayer().repaint();
					Presentation.getPLayer().pack();
				} else if (jcbInsQuery.getSelectedItem().equals(UPDATE) && !(openedPanel instanceof updatePanel)) {
					contents.remove(openedPanel);
					openedPanel = new updatePanel();
					contents.add(openedPanel);
					Presentation.getPLayer().revalidate();
					Presentation.getPLayer().repaint();
					Presentation.getPLayer().pack();
				} else if (jcbInsQuery.getSelectedItem().equals(DELETE) && !(openedPanel instanceof deletePanel)) {
					contents.remove(openedPanel);
					openedPanel = new deletePanel();
					contents.add(openedPanel);
					Presentation.getPLayer().revalidate();
					Presentation.getPLayer().repaint();
					Presentation.getPLayer().pack();
				} else if (jcbInsQuery.getSelectedItem().equals(QUERY) && !(openedPanel instanceof queryPanel)) {
					contents.remove(openedPanel);
					openedPanel = queryPanelFac;
					contents.add(openedPanel);
					Presentation.getPLayer().revalidate();
					Presentation.getPLayer().repaint();
					Presentation.getPLayer().pack();
				}
			}
		});

		contents.add(jcbInsQuery);
		contents.add(openedPanel);
		add(contents);
	}
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
	private JPanel queryPanelStud = new queryPanel(false);

	/**
	 * Initialize Result Panel.
	 * 
	 * @param resContent String Result content to be displayed
	 */
	public queryResults(String resContent, boolean fromFac) {

		JPanel resContentContainer = new JPanel(new BorderLayout());
		JTextArea resContentContainerText = new JTextArea(resContent);
		resContentContainerText.setLineWrap(true);
		resContentContainerText.setWrapStyleWord(true);
		resContentContainer.setPreferredSize(new Dimension(500, 700));

		JScrollPane resContentPane = new JScrollPane(resContentContainerText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		resContentContainer.add(resContentPane);

		JButton jbBack = new JButton("Back");

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fromFac) {
					Presentation.setOpenedPanel(new facPanel());
				} else {
					Presentation.setOpenedPanel(queryPanelStud);
				}
			}
		});

		add(resContentContainer);
		add(jbBack);

	}
}
