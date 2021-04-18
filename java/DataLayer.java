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

        // instantiation of variables for use later in the method
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

    /**
     * This method fetches the information for students based on first name, last name, school, or skills.
     * @param firstName the first name of the student (optional).
     * @param lastName the first name of the student (optional).
     * @param school the first name of the student (optional).
     * @param skills the skills of the student (optional).
     * @return a string containing the information of all students fetched.
     */
    public String getStudentInfo(String firstName, String lastName, String school) {

        ArrayList<String> specifiedStudentInfo = new ArrayList<String>();
        ArrayList<String> parameterNames = new ArrayList<String>();
        String sql = "SELECT studentID, firstname, lastname, school FROM student ";
        String studentInfo = "";

        specifiedStudentInfo.add(firstName);
        specifiedStudentInfo.add(lastName);
        specifiedStudentInfo.add(school);
        // specifiedStudentInfo.add(skills);

        parameterNames.add("firstName");
        parameterNames.add("lastName");
        parameterNames.add("school");
        // parameterNames.add("skills");

        // filter the 'specifiedStudentInfo' and 'parameterNames' ArrayLists so that only the parameters that aren't null/empty go in them.
        for (int i = 0; i < specifiedStudentInfo.size(); i++) {

            if (specifiedStudentInfo.get(i) == null || specifiedStudentInfo.get(i).equals("")) {

                specifiedStudentInfo.remove(i);
                parameterNames.remove(i);
                i -= 1;

            }
            else if (sql.contains("WHERE")) {

                sql += "AND " + parameterNames.get(i) + "='" + specifiedStudentInfo.get(i) + "' ";

            }
            else {

                sql += "WHERE " + parameterNames.get(i) + "='" + specifiedStudentInfo.get(i) + "' ";

            }

        }

        try {

            statement = conn.createStatement();

            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {

                String firstNameTemp = resultSet.getString("firstname");
                String lastNameTemp = resultSet.getString("lastname");
                String schoolTemp = resultSet.getString("school");

                studentInfo += "<html>First Name: " + firstNameTemp + "<br />" +
                               "Last Name: " + lastNameTemp + "<br />" +
                               "School: " + schoolTemp + "<br />";
                
            }

            return studentInfo;

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
    * This method just fetches the skills from a student
    * @param firstName the first name of the student (optional).
    * @param lastName the first name of the student (optional).
    * @param school the first name of the student (optional).
    * @param skills the skills of the student (optional).
    * @return a string containing the information of all students fetched.
    */
    // public ArrayList<ArrayList<String>> getStudentSkills(ArrayList<String> skills) {

    //     ArrayList<String> studentIDs = new ArrayList<String>();
    //     ArrayList<ArrayList<String>> skillArrayLists = new ArrayList<ArrayList<String>>();
    //     ArrayList<ArrayList<String>> returnedLists = new ArrayList<ArrayList<String>>();

    //     try {

    //         statement = conn.createStatement();

    //         resultSet = statement.executeQuery("SELECT studentID FROM student");

    //         while (resultSet.next()) {

    //             studentIDs.add(resultSet.getString("studentID"));

    //         }

    //         for (int i = 0; i < studentIDs.size(); i++) {

    //             ArrayList<String> tempList = new ArrayList<String>();

    //             resultSet = statement.executeQuery("SELECT DISTINCT skill FROM skills JOIN studentskill WHERE studentID = " + studentIDs.get(i));

    //             while (resultSet.next()) {

    //                 if (tempList.size() == 0) {

    //                     tempList.add(studentIDs.get(i));

    //                 }

    //                 tempList.add(resultSet.getString("skill"));

    //             }

    //             skillArrayLists.add(tempList);

    //         }
            
    //         for (int i = 0; i < skillArrayLists.size(); i++) {

    //             int matches = 0;
    //             boolean addedMatches = false;

    //             for (int x = 0; x < skillArrayLists.get(i).size(); x++) {

    //                 for (int y = 0; y < skills.size(); y++) {

    //                     if (skills.get(y).equals(skillArrayLists.get(i).get(x))) {
                        
    //                         if (addedMatches) {
                            
    //                              skillArrayLists.get(i).set(skillArrayLists.get(i).size() - 1, ++matches + "");
                            
    //                         }
    //                         else {
                            
    //                           skillArrayLists.get(i).add(++matches + "");
    //                           addedMatches = true;
                            
    //                         }

    //                     }
                        
    //                 }

    //             }

    //         }

    //         System.out.println(skillArrayLists);

    //         returnedLists.add(new ArrayList<String>());
    //         returnedLists.get(0).add("0");

    //         for (int i = 0; i < skillArrayLists.size(); i++) {

    //             if (Integer.parseInt(skillArrayLists.get(i).get(skillArrayLists.get(i).size() - 1)) > Integer.parseInt(returnedLists.get(0).get(returnedLists.get(0).size() - 1))) {

    //                 System.out.println(Integer.parseInt(skillArrayLists.get(i).get(skillArrayLists.get(i).size() - 1)));
                    
    //                 if (returnedLists.size() > 1) {

    //                     returnedLists = new ArrayList<ArrayList<String>>();

    //                 }

    //                 returnedLists.set(0, skillArrayLists.get(i));

    //             }
    //             else if (Integer.parseInt(skillArrayLists.get(i).get(skillArrayLists.get(i).size() - 1)) == Integer.parseInt(returnedLists.get(0).get(returnedLists.get(0).size() - 1))) {

    //                 returnedLists.add(skillArrayLists.get(i));

    //             }

    //         }

    //         return returnedLists;

    //     }
    //     catch(SQLException sqlException) {

    //         sqlException.printStackTrace();
    //         return new ArrayList<ArrayList<String>>();

    //     }
    //     catch(Exception exception) {

    //         exception.printStackTrace();
    //         return new ArrayList<ArrayList<String>>();

    //     }

    // }

}