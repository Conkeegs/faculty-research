import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * This class handles the backend code for fetching student and faculty
 * information through the java MySQL class.
 * 
 * Faculty Research Database ISTE-330
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
     * This method uses a DriverManager static method to return a Connection object
     * and connect to the database.
     * 
     * @param user     The user's database username
     * @param password The user's database password
     * @return A boolean indicating if connection to the database was successful
     */
    public boolean connect(String user, String password) {

        try {

            conn = DriverManager.getConnection(url, user, password);
            return true;

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return false;

        } catch (Exception exception) {

            exception.printStackTrace();
            return false;

        }

    }

    /**
     * This method tests to see if there is a connection to the database and if so,
     * closes it.
     * 
     * @return A boolean indicating that closing the connection was successful
     */
    public boolean close() {

        try {

            if (!conn.isClosed()) {

                conn.close();
                return true;

            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return false;

        } catch (Exception exception) {

            exception.printStackTrace();
            return false;

        }

        return false;

    }

    /**
     * This method loads the class of the driver
     * 
     * @return A boolean indicating that connection to the driver was successful
     */
    public boolean loadDriver() {

        try {

            Class.forName(DEFAULT_DRIVER);
            return true;

        } catch (ClassNotFoundException classNotFoundException) {

            classNotFoundException.printStackTrace();
            return false;

        } catch (Exception exception) {

            exception.printStackTrace();
            return false;

        }

    }

    /**
     * This method fetches the information for faculty based on first name, last
     * name, school, abstract, or keywords.
     * 
     * @param firstName       the first name of the faculty member (optional).
     * @param lastName        the first name of the faculty member (optional).
     * @param school          the first name of the faculty member (optional).
     * @param facultyAbstract the abstract of the faculty member (optional).
     * @param keywords        the keywords that the user has specified for the searched faculty abstracts.
     * @return a string containing the information of all faculty members fetched.
     */
    public String getFacultyInfo(String firstName, String lastName, String school, String facultyAbstract, ArrayList<String> keyWords) {

        // instantiation of variables for use later in the method
        ArrayList<String> specifiedFacultyInfo = new ArrayList<String>();
        ArrayList<String> parameterNames = new ArrayList<String>();
        ArrayList<ArrayList<String>> allFacultyInfo = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> allFacultyKeywords = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> greatestMatchingFaculty = new ArrayList<ArrayList<String>>();
        String sql = "SELECT facultyID, firstname, lastname, school, facultyabstract FROM faculty ";
        String facultyInfo = "";

        // add passed-in parameters to an ArrayList
        specifiedFacultyInfo.add(firstName);
        specifiedFacultyInfo.add(lastName);
        specifiedFacultyInfo.add(school);
        specifiedFacultyInfo.add(facultyAbstract);

        // add possible variable names for queries to take in
        parameterNames.add("firstName");
        parameterNames.add("lastName");
        parameterNames.add("school");
        parameterNames.add("facultyAbstract");

        // filter the 'specifiedFacultyInfo' and 'parameterNames' ArrayLists so that
        // only the parameters that aren't null/empty go in them.
        for (int i = 0; i < specifiedFacultyInfo.size(); i++) {

            if (specifiedFacultyInfo.get(i) == null || specifiedFacultyInfo.get(i).equals("")) {

                specifiedFacultyInfo.remove(i);
                parameterNames.remove(i);
                i -= 1;

            } else if (sql.contains("WHERE")) {

                sql += "AND " + parameterNames.get(i) + "='" + specifiedFacultyInfo.get(i) + "' ";

            } else {

                sql += "WHERE " + parameterNames.get(i) + "='" + specifiedFacultyInfo.get(i) + "' ";

            }

        }

        try {

            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            // populate the 'allFacultyInfo' ArrayList with other ArrayLists holding
            // informatiom about faculty that meet parameters specified.
            while (resultSet.next()) {

                ArrayList<String> tempFacultyInfo = new ArrayList<String>();

                String facultyID = resultSet.getString("facultyID");
                String firstNameTemp = resultSet.getString("firstname");
                String lastNameTemp = resultSet.getString("lastname");
                String schoolTemp = resultSet.getString("school");
                String facultyAbstractTemp = resultSet.getString("facultyabstract");

                tempFacultyInfo.add(facultyID);
                tempFacultyInfo.add(firstNameTemp);
                tempFacultyInfo.add(lastNameTemp);
                tempFacultyInfo.add(schoolTemp);
                tempFacultyInfo.add(facultyAbstractTemp);

                allFacultyInfo.add(tempFacultyInfo);

            }

            // if the 'keyWords' ArrayList parameter has values in it, add a 'matches' value
            // to each facultyinfo ArrayList which tells how many 'keyword' matches there are.
            if (keyWords.size() > 0) {

                // populate the 'allFacultyKeywords' ArrayList with other ArrayLists holding all
                // of the faculty keywords
                for (int i = 0; i < allFacultyInfo.size(); i++) {

                    ArrayList<String> tempFacultyKeywords = new ArrayList<String>();
                    statement = conn.createStatement();
                    resultSet = statement.executeQuery("SELECT keywords FROM facultyKeywords WHERE facultyID = " + allFacultyInfo.get(i).get(0));

                    while (resultSet.next()) {

                        String keyword = resultSet.getString(1);

                        tempFacultyKeywords.add(keyword);

                    }

                    allFacultyKeywords.add(tempFacultyKeywords);

                }

                // adds 'matches' value to 'allFacultyInfo' lists
                for (int i = 0; i < keyWords.size(); i++) {

                    for (int x = 0; x < allFacultyKeywords.size(); x++) {

                        int matches = 0;

                        for (int y = 0; y < allFacultyKeywords.get(x).size(); y++) {

                            if (keyWords.get(i).equals(allFacultyKeywords.get(x).get(y))) {

                                matches++;

                                if (allFacultyInfo.get(x).size() > 5) {

                                    allFacultyInfo.get(x).set(5, (Integer.parseInt(allFacultyInfo.get(x).get(5)) + matches) + "");

                                } else {

                                    allFacultyInfo.get(x).add(matches + "");

                                }

                            } else {

                                if (allFacultyInfo.get(x).size() <= 5) {

                                    allFacultyInfo.get(x).add(matches + "");

                                }

                            }

                        }

                    }

                }

                int greatest = 0;

                // populate the 'greatestMatchingFaculty' ArrayList with more ArrayLists
                // containing faculty that have the highest 'matches' values.
                for (int i = 0; i < allFacultyInfo.size(); i++) {

                    if (Integer.parseInt(allFacultyInfo.get(i).get(5)) > greatest) {

                        greatestMatchingFaculty = new ArrayList<ArrayList<String>>();
                        greatestMatchingFaculty.add(allFacultyInfo.get(i));

                        greatest = Integer.parseInt(allFacultyInfo.get(i).get(5));

                    } else if (Integer.parseInt(allFacultyInfo.get(i).get(5)) == greatest) {

                        greatestMatchingFaculty.add(allFacultyInfo.get(i));

                    }

                }

            } else {

                // this happens if the 'keyWords' parameter ArrayList is empty. Just populate the
                // 'greatestMatchingFaculty' ArrayList with all ArrayLists from 'allFacultyInfo'.
                for (int i = 0; i < allFacultyInfo.size(); i++) {

                    greatestMatchingFaculty.add(allFacultyInfo.get(i));

                }

            }

            // populate the 'facultyInfo' String with all information from the
            // 'greatestMatchingFaculty' ArrayList
            facultyInfo += "<html>";
            for (int i = 0; i < greatestMatchingFaculty.size(); i++) {

                facultyInfo += "First Name: " + greatestMatchingFaculty.get(i).get(1) + "<br />" + "Last Name: "
                        + greatestMatchingFaculty.get(i).get(2) + "<br />" + "School: "
                        + greatestMatchingFaculty.get(i).get(3) + "<br />" + "Abstract: "
                        + greatestMatchingFaculty.get(i).get(4) + "<br /><br />";

            }

            facultyInfo += "</html>";

            return facultyInfo;

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return "SQL ERROR";

        } catch (Exception exception) {

            exception.printStackTrace();
            return "GENERAL ERROR";

        }

    }

    /**
     * This method fetches the information for students based on first name, last
     * name, school, or skills.
     * 
     * @param firstName the first name of the student (optional).
     * @param lastName  the first name of the student (optional).
     * @param school    the first name of the student (optional).
     * @param skills    the skills of the student (optional).
     * @return a string containing the information of all students fetched.
     */
    public String getStudentInfo(String firstName, String lastName, String school, ArrayList<String> skills) {

        // instantiation of variables for use later in the method
        ArrayList<String> specifiedStudentInfo = new ArrayList<String>();
        ArrayList<String> parameterNames = new ArrayList<String>();
        ArrayList<ArrayList<String>> allStudentInfo = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> allStudentSkills = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> greatestMatchingStudents = new ArrayList<ArrayList<String>>();
        String sql = "SELECT studentID, firstname, lastname, school FROM student ";
        String studentInfo = "";

        // add passed-in parameters to an ArrayList
        specifiedStudentInfo.add(firstName);
        specifiedStudentInfo.add(lastName);
        specifiedStudentInfo.add(school);

        // add possible variable names for queries to take in
        parameterNames.add("firstName");
        parameterNames.add("lastName");
        parameterNames.add("school");

        // filter the 'specifiedStudentInfo' and 'parameterNames' ArrayLists so that
        // only the parameters that aren't null/empty go in them.
        for (int i = 0; i < specifiedStudentInfo.size(); i++) {

            if (specifiedStudentInfo.get(i) == null || specifiedStudentInfo.get(i).equals("")) {

                specifiedStudentInfo.remove(i);
                parameterNames.remove(i);
                i -= 1;

            } else if (sql.contains("WHERE")) {

                sql += "AND " + parameterNames.get(i) + "='" + specifiedStudentInfo.get(i) + "' ";

            } else {

                sql += "WHERE " + parameterNames.get(i) + "='" + specifiedStudentInfo.get(i) + "' ";

            }

        }

        try {

            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);

            // populate the 'allStudentInfo' ArrayList with other ArrayLists holding
            // informatiom about students that meet parameters specified.
            while (resultSet.next()) {

                ArrayList<String> tempStudentInfo = new ArrayList<String>();

                String studentID = resultSet.getString("studentID");
                String firstNameTemp = resultSet.getString("firstname");
                String lastNameTemp = resultSet.getString("lastname");
                String schoolTemp = resultSet.getString("school");

                tempStudentInfo.add(studentID);
                tempStudentInfo.add(firstNameTemp);
                tempStudentInfo.add(lastNameTemp);
                tempStudentInfo.add(schoolTemp);

                allStudentInfo.add(tempStudentInfo);

            }

            // if the 'skills' ArrayList parameter has values in it, add a 'matches' value
            // to each studentInfo ArrayList which tells how many 'skill' matches there are.
            if (skills.size() > 0) {

                // populate the 'allStudentSkills' ArrayList with other ArrayLists holding all
                // of the student skills
                for (int i = 0; i < allStudentInfo.size(); i++) {

                    ArrayList<String> tempStudentSkills = new ArrayList<String>();
                    statement = conn.createStatement();
                    resultSet = statement.executeQuery("SELECT skill FROM skills JOIN studentskill USING (skillid) WHERE studentid = " + allStudentInfo.get(i).get(0));

                    while (resultSet.next()) {

                        String skill = resultSet.getString(1);

                        tempStudentSkills.add(skill);

                    }

                    allStudentSkills.add(tempStudentSkills);

                }

                // adds 'matches' value to 'allStudentInfo' lists
                for (int i = 0; i < skills.size(); i++) {

                    for (int x = 0; x < allStudentSkills.size(); x++) {

                        int matches = 0;

                        for (int y = 0; y < allStudentSkills.get(x).size(); y++) {

                            if (skills.get(i).equals(allStudentSkills.get(x).get(y))) {

                                matches++;

                                if (allStudentInfo.get(x).size() > 4) {

                                    allStudentInfo.get(x).set(4, (Integer.parseInt(allStudentInfo.get(x).get(4)) + matches) + "");

                                } else {

                                    allStudentInfo.get(x).add(matches + "");

                                }

                            } else {

                                if (allStudentInfo.get(x).size() <= 4) {

                                    allStudentInfo.get(x).add(matches + "");

                                }

                            }

                        }

                    }

                }

                int greatest = 0;

                // populate the 'greatestMatchingStudents' ArrayList with more ArrayLists
                // containing students that have the highest 'matches' values.
                for (int i = 0; i < allStudentInfo.size(); i++) {

                    if (Integer.parseInt(allStudentInfo.get(i).get(4)) > greatest) {

                        greatestMatchingStudents = new ArrayList<ArrayList<String>>();
                        greatestMatchingStudents.add(allStudentInfo.get(i));

                        greatest = Integer.parseInt(allStudentInfo.get(i).get(4));

                    } else if (Integer.parseInt(allStudentInfo.get(i).get(4)) == greatest) {

                        greatestMatchingStudents.add(allStudentInfo.get(i));

                    }

                }

            } else {

                // this happens if the 'skills' parameter ArrayList is empty. Just populate the
                // 'greatestMatchingStudents' ArrayList with all ArrayLists from 'allStudentInfo'.
                for (int i = 0; i < allStudentInfo.size(); i++) {

                    greatestMatchingStudents.add(allStudentInfo.get(i));

                }

            }

            // This loop takes all of the students in the 'greatestMatchingStudents' ArrayList and gets each
            // of their specific skils. Then, these skills are appended to the end of each ArrayList inside
            // of 'greatestMatchingStudents' so they can be output in the swing application.
            for (int i = 0; i < greatestMatchingStudents.size(); i++) {

                String tempSkills = "";

                statement = conn.createStatement();
                resultSet = statement.executeQuery("SELECT skill FROM skills JOIN studentskill USING (skillid) WHERE studentid = " + greatestMatchingStudents.get(i).get(0));

                while (resultSet.next()) {

                    String skill = resultSet.getString(1);

                    if (tempSkills.equals("")) {

                        tempSkills += skill;

                    }
                    else {

                        tempSkills += " | " + skill;

                    }

                }

                greatestMatchingStudents.get(i).add(tempSkills);

            }

            // populate the 'studentInfo' String with all information from the
            // 'greatestMatchingStudents' ArrayList
            studentInfo += "<html>";
            for (int i = 0; i < greatestMatchingStudents.size(); i++) {

                studentInfo += "First Name: " + greatestMatchingStudents.get(i).get(1) + "<br />" 
                            + "Last Name: " + greatestMatchingStudents.get(i).get(2) + "<br />" 
                            + "School: " + greatestMatchingStudents.get(i).get(3) + "<br />"
                            + "Skill(s): " + greatestMatchingStudents.get(i).get(5) + "<br /><br />";

            }

            studentInfo += "</html>";

            return studentInfo;

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();
            return "SQL ERROR";

        } catch (Exception exception) {

            exception.printStackTrace();
            return "GENERAL ERROR";

        }
    }

    public boolean addFacLogin(int facultyID, String uname, String pass) {
        String[] pwHashInfo = hashPass(pass);

        try {
            PreparedStatement pStatement = conn.prepareStatement("UPDATE faculty SET username=?, pwhash=?, salt=? WHERE facultyID=?;");

            pStatement.setString(1, uname);
            pStatement.setString(2, pwHashInfo[1]);
            pStatement.setString(3, pwHashInfo[0]);
            pStatement.setInt(4, facultyID);

            pStatement.executeUpdate();

            pStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int checkFacLogin(String uname, String pass) {
        int facID = -1;
        String pwhash = "";
        String salt = "";

        try {
            PreparedStatement pStatement = conn.prepareStatement("SELECT facultyID, pwhash, salt FROM faculty WHERE uname=?");

            pStatement.setString(1, uname);

            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                facID = rs.getInt("facultyID");
                pwhash = rs.getString("pwhash");
                salt = rs.getString("salt");
    
                rs.close();
                pStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        if (verifyPass(salt, pwhash, pass)) {
            return facID;
        }

        return -1;
    }

    private static String[] hashPass(String plainPass) {
        SecureRandom saltGen = new SecureRandom();

        byte[] salt = new byte[32];
        saltGen.nextBytes(salt);

        PBEKeySpec kSpec = new PBEKeySpec(plainPass.toCharArray(), salt, 10000, 500);

        try {
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hashedPass = keyFac.generateSecret(kSpec).getEncoded();

            return new String[] {Base64.getEncoder().encodeToString(salt), Base64.getEncoder().encodeToString(hashedPass)};
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            kSpec.clearPassword();
        }
        return null;
    }

    private static boolean verifyPass(String salt, String hash, String plainPass) {
        PBEKeySpec kSpec = new PBEKeySpec(plainPass.toCharArray(), Base64.getDecoder().decode(salt), 10000, 500);

        try {
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hashedPass = keyFac.generateSecret(kSpec).getEncoded();

            if (hashedPass == Base64.getDecoder().decode(hash)) {
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            kSpec.clearPassword();
        }
        
        return false;
    }

    /**
     * This method just fetches the skills from a student
     * 
     * @param firstName the first name of the student (optional).
     * @param lastName  the first name of the student (optional).
     * @param school    the first name of the student (optional).
     * @param skills    the skills of the student (optional).
     * @return a string containing the information of all students fetched.
     */
    // public ArrayList<ArrayList<String>> getStudentSkills(ArrayList<String>
    // skills) {

    // ArrayList<String> studentIDs = new ArrayList<String>();
    // ArrayList<ArrayList<String>> skillArrayLists = new
    // ArrayList<ArrayList<String>>();
    // ArrayList<ArrayList<String>> returnedLists = new
    // ArrayList<ArrayList<String>>();

    // try {

    // statement = conn.createStatement();

    // resultSet = statement.executeQuery("SELECT studentID FROM student");

    // while (resultSet.next()) {

    // studentIDs.add(resultSet.getString("studentID"));

    // }

    // for (int i = 0; i < studentIDs.size(); i++) {

    // ArrayList<String> tempList = new ArrayList<String>();

    // resultSet = statement.executeQuery("SELECT DISTINCT skill FROM skills JOIN
    // studentskill WHERE studentID = " + studentIDs.get(i));

    // while (resultSet.next()) {

    // if (tempList.size() == 0) {

    // tempList.add(studentIDs.get(i));

    // }

    // tempList.add(resultSet.getString("skill"));

    // }

    // skillArrayLists.add(tempList);

    // }

    // for (int i = 0; i < skillArrayLists.size(); i++) {

    // int matches = 0;
    // boolean addedMatches = false;

    // for (int x = 0; x < skillArrayLists.get(i).size(); x++) {

    // for (int y = 0; y < skills.size(); y++) {

    // if (skills.get(y).equals(skillArrayLists.get(i).get(x))) {

    // if (addedMatches) {

    // skillArrayLists.get(i).set(skillArrayLists.get(i).size() - 1, ++matches +
    // "");

    // }
    // else {

    // skillArrayLists.get(i).add(++matches + "");
    // addedMatches = true;

    // }

    // }

    // }

    // }

    // }

    // System.out.println(skillArrayLists);

    // returnedLists.add(new ArrayList<String>());
    // returnedLists.get(0).add("0");

    // for (int i = 0; i < skillArrayLists.size(); i++) {

    // if (Integer.parseInt(skillArrayLists.get(i).get(skillArrayLists.get(i).size()
    // - 1)) > Integer.parseInt(returnedLists.get(0).get(returnedLists.get(0).size()
    // - 1))) {

    // System.out.println(Integer.parseInt(skillArrayLists.get(i).get(skillArrayLists.get(i).size()
    // - 1)));

    // if (returnedLists.size() > 1) {

    // returnedLists = new ArrayList<ArrayList<String>>();

    // }

    // returnedLists.set(0, skillArrayLists.get(i));

    // }
    // else if
    // (Integer.parseInt(skillArrayLists.get(i).get(skillArrayLists.get(i).size() -
    // 1)) == Integer.parseInt(returnedLists.get(0).get(returnedLists.get(0).size()
    // - 1))) {

    // returnedLists.add(skillArrayLists.get(i));

    // }

    // }

    // return returnedLists;

    // }
    // catch(SQLException sqlException) {

    // sqlException.printStackTrace();
    // return new ArrayList<ArrayList<String>>();

    // }
    // catch(Exception exception) {

    // exception.printStackTrace();
    // return new ArrayList<ArrayList<String>>();

    // }

    // }

}
