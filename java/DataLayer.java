import java.sql.*;
import java.util.*;

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
    private ResultSet resultSet;

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

    /**
     * This method fetches the information for faculty based on first name, last name, school, or abstract.
     * @param firstName the first name of the faculty member (optional).
     * @param lastName the first name of the faculty member (optional).
     * @param school the first name of the faculty member (optional).
     * @param facultyAbstract the abstract of the faculty member (optional).
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

                sql += "AND " + parameterNames.get(i) + "='" + specifiedFacultyInfo.get(i) + "' ";

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

}