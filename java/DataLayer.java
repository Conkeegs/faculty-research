import java.sql.*;
import java.util.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * This class handles the backend code for fetching student and faculty
 * information through the java MySQL class.
 * 
 * Faculty Research Database
 * ISTE-330
 * 
 * @author Conor Keegan
 * @author Eli Hopkins
 * @author Evan Hiltzik
 * @author Nicholas Johnson
 */

public class DataLayer {

    private final String url = "jdbc:mysql://localhost/facultyResearch";
    private final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection conn;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;

    public DataLayer() {

        System.out.println(getFacultyInfo("", "c", "c", "conor"));;

    }

    /**
     * This method uses a DriverManager static method to return a Connection object and connect
     * to the database.
     * @param user The user's database username
     * @param password The user's database password
     * @return A boolean indicating if connection to the database was successful
     */
    public boolean connect(String user, String password) {

        try {

            conn = DriverManager.getConnection(url, user, password);
            return true;

        }
        catch(SQLException sqlException) {

            sqlException.printStackTrace();
            return false;

        }
        catch(Exception exception) {

            exception.printStackTrace();
            return false;

        }

    }

    /**
     * This method tests to see if there is a connection to the database and if so,
     * closes it.
     * @return A boolean indicating that closing the connection was successful
     */
    public boolean close() {

        try {

            if (!conn.isClosed()) {

                conn.close();
                return true;

            }

        }
        catch(SQLException sqlException) {

            sqlException.printStackTrace();
            return false;

        }
        catch(Exception exception) {

            exception.printStackTrace();
            return false;

        }

        return false;

    }

    /**
     * This method loads the class of the driver
     * @return A boolean indicating that connection to the driver was successful
     */
    public boolean loadDriver() {

        try {

            Class.forName(DEFAULT_DRIVER);
            return true;

        }
        catch(ClassNotFoundException classNotFoundException) {

            classNotFoundException.printStackTrace();
            return false;

        }
        catch(Exception exception) {

            exception.printStackTrace();
            return false;

        }

    }

    public static void main(String[] args) {

        new DataLayer();

    }

    /**
     * This method fetches the information for faculty based on first name, last name, school, or abstract.
     * @param firstName the first name of the faculty member (optional).
     * @param lastName the first name of the faculty member (optional).
     * @param school the first name of the faculty member (optional).
     * @return a string containing the information of all faculty members fetched.
     */
    public String getFacultyInfo(String firstName, String lastName, String school, String facultyAbstract) {

        ArrayList<String> specifiedFacultyInfo = new ArrayList<String>();
        ArrayList<String> parameterNames = new ArrayList<String>();
        String sql = "SELECT firstname, lastname, school, facultyabstract FROM faculty ";
        String facultyInfo = "";

        specifiedFacultyInfo.add(firstName);
        specifiedFacultyInfo.add(lastName);
        specifiedFacultyInfo.add(school);
        specifiedFacultyInfo.add(facultyAbstract);

        parameterNames.add("firstName");
        parameterNames.add("lastName");
        parameterNames.add("school");
        parameterNames.add("facultyAbstract");

        // filter the 'specifiedFacultyInfo' and 'parameterNames' ArrayLists so that only the parameters that aren't null/empty go in them.
        for (int i = 0; i < specifiedFacultyInfo.size(); i++) {

            if (specifiedFacultyInfo.get(i) == null || specifiedFacultyInfo.get(i).equals("")) {

                specifiedFacultyInfo.remove(i);
                parameterNames.remove(i);
                i -= 1;

            }
            else if (sql.contains("WHERE")) {

                sql += "AND WHERE " + parameterNames.get(i) + "='" + specifiedFacultyInfo.get(i) + "' ";

            }
            else {

                sql += "WHERE " + parameterNames.get(i) + "='" + specifiedFacultyInfo.get(i) + "' ";

            }

        }

        try {

            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {

                String firstNameTemp = resultSet.getString("firstname");
                String lastNameTemp = resultSet.getString("lastname");
                String schoolTemp = resultSet.getString("school");
                String facultyAbstractTemp = resultSet.getString("facultyabstract");

                facultyInfo += "First Name: " + firstNameTemp + "\n" +
                               "Last Name: " + lastNameTemp + "\n" +
                               "School: " + schoolTemp + "\n" +
                               "Abstract: " + facultyAbstractTemp + "\n\n";
                
            }

            return facultyInfo;

        }
        catch(SQLException sqlException) {

            sqlException.printStackTrace();
            return "SQL ERROR";

        }
        catch(Exception exception) {

            exception.printStackTrace();
            return "GENERAL ERROR";

        }

    }

    /**
     * This method deletes a passenger from the database.
     * @param passengerID the passengerID of the passenger to delete.
     * @return a boolean indicating whether the passenger was deleted or not.
     */
    public boolean deletePassenger(String lastName, String firstName) {

        int rowCount;

        try {

            // this gets the number of rows from the database before the delete
            rowCount = Integer.parseInt(getPassengerCount());

            // this gets all the records from the passenger table and moves the cursor forwards
            System.out.print("\n");
            System.out.println("RECORDS IN PASSENGER BEFORE DELETE:");
            preparedStatement = conn.prepareStatement("SELECT * FROM passenger");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            // loops through the first set of records from the passenger table before the delete
            System.out.print("\n");
            for (int i = 0; i < rowCount; i++) {

                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3)
                + " " + resultSet.getString(4) + " " + resultSet.getString(5));

                resultSet.next();

            }

            // deletes records from passenger table
            preparedStatement = conn.prepareStatement("DELETE FROM passenger WHERE lname = ? AND fname = ?");
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.executeUpdate();

            // this gets the number of rows from the database after the delete
            rowCount = Integer.parseInt(getPassengerCount());

            // this gets all the records from the passenger table and moves the cursor forwards
            System.out.print("\n");
            System.out.println("RECORDS IN PASSENGER AFTER DELETE:");
            preparedStatement = conn.prepareStatement("SELECT * FROM passenger");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            
            // loops through the second set of records from the passenger table after the delete
            System.out.print("\n");
            for (int i = 0; i < rowCount; i++) {

                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3)
                + " " + resultSet.getString(4) + " " + resultSet.getString(5));

                resultSet.next();

            }

            System.out.print("\n");
            System.out.println("Records left in passenger table: " + getPassengerCount());

            return true;

        }
        catch(SQLException sqlException) {

            sqlException.printStackTrace();
            return false;

        }
        catch(Exception exception) {

            exception.printStackTrace();
            return false;

        }

    }

    /**
     * This method updated the passenger table by changing the street name of a specific passenger
     * @param passengerID the passengerID of the person.
     * @param street the new street name for the person.
     * @return a bolean indicating whether the person's street was updated or not.
     */
    public boolean updatePassenger(String passengerID, String street) {

        try {

            // updated the person's street
            preparedStatement = conn.prepareStatement("UPDATE passenger SET street = ? WHERE passengerid = ?");
            preparedStatement.setString(1, street);
            preparedStatement.setString(2, passengerID);
            preparedStatement.executeUpdate();
            return true;

        }
        catch(SQLException sqlException) {

            sqlException.printStackTrace();
            return false;

        }
        catch(Exception exception) {

            exception.printStackTrace();
            return false;

        }

    }

    /**
     * This method indicated that it is in execution and then gets the row and column count from the
     * staff table.
     * @return a boolean indicating that this method got both the row and columns counts.
     */
    public void staffRoutine() {

        try {

            // get row count
            System.out.print("\n");
            System.out.println("In staff routine...");
            preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM staff");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println("Number of rows in staff table: " + resultSet.getString(1));

            // get column count
            preparedStatement = conn.prepareStatement("SELECT * FROM staff");
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            System.out.println("Number of columns in staff table: " + resultSetMetaData.getColumnCount());

        }
        catch(SQLException sqlException) {

            sqlException.printStackTrace();

        }

    }

    /**
     * This method gets the number of rows in the passenger table.
     * @return a String which is the number of rows in the passenger table.
     */
    public String getPassengerCount() {

        String recordCount = "";

        try {

            // get number of rows
            preparedStatement = conn.prepareStatement("SELECT COUNT(*) FROM passenger");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            recordCount = resultSet.getString(1);

            return recordCount;

        }
        catch(SQLException sqlException) {

            sqlException.printStackTrace();

        }

        return null;

    }

}