package src;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Programmers: Hunter Danielson, Brian Withrow.
 * Date: 11/20/2018
 * Description:
 *
 * Version 1.0:
 *
 * Need to do:
 * Still needs to returns the user id and role.
 *
 * Finished Functionality:
 * Verifies login correctly.
 */

public class Student implements Users {
    //This contains all the variables and their associated values for the users instance.
    JSONArray AccountsIN;
    int UserNumber;
    //Base Constructor to create AccountsIN User array
    public Student(){
        //scanner and file name is constant across all read and write classes
        Scanner scanner = new Scanner(System.in);
        File file = new File("JSONDATA.txt");
        try {
            //this is the Scanner Object being used to create a new instance that reads the file
            //if this is implemented into another file uncomment the other code
            Scanner FileInput = new Scanner(file);

            //StringBuilder is used to parse the JSON file.
            StringBuilder FileInputConcat = new StringBuilder();

            //this while statement checks for  if anything is in the file
            while (FileInput.hasNextLine()) {
                //Concatenated all lines of the code together into one giant string
                FileInputConcat.append(FileInput.nextLine());
            }
            //Creates a Object of the Parser to read the Concatenated lines of string
            JSONParser parser = new JSONParser();

            //You have to declare the right type of JSON<type> and then this code will
            //parce the string that was concatenated and will then create JSONObjects of this
            AccountsIN = (JSONArray) parser.parse(FileInputConcat.toString());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
    }

    //Overloaded Constructor that will set the Students User Number for all other functions.
    public Student(int UserNumber){
        //Sets the User Number for the object.
        this.UserNumber = UserNumber;
        //scanner and file name is constant across all read and write classes
        Scanner scanner = new Scanner(System.in);
        File file = new File("JSONDATA.txt");
        try {
            //this is the Scanner Object being used to create a new instance that reads the file
            //if this is implemented into another file uncomment the other code
            Scanner FileInput = new Scanner(file);

            //StringBuilder is used to parse the JSON file.
            StringBuilder FileInputConcat = new StringBuilder();

            //this while statement checks for  if anything is in the file
            while (FileInput.hasNextLine()) {
                //Concatenated all lines of the code together into one giant string
                FileInputConcat.append(FileInput.nextLine());
            }
            //Creates a Object of the Parser to read the Concatenated lines of string
            JSONParser parser = new JSONParser();

            //You have to declare the right type of JSON<type> and then this code will
            //parce the string that was concatenated and will then create JSONObjects of this
            AccountsIN = (JSONArray) parser.parse(FileInputConcat.toString());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
    }

    public String getUserEmail(int UserNumber){
        //this parses the users account from the constructor
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

        JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
        String Email = (String) userCredentialsIn.get("Email");
        return Email;
    }

    public String getUsername(int UserNumber){
        //this parses the users account from the constructor
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
        //zero is the code for the user credential storage
        JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
        String UsernameIn = (String) userCredentialsIn.get("Username");
        return UsernameIn;
    }

    public String getPassword(int UserNumber){
        //this parses the users account from the constructor
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
        //zero is the code for the user credential storage
        JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
        String PasswordIn = (String) userCredentialsIn.get("Password");
        return PasswordIn;
    }

    @Override
    public String toString() {
        return "Student{" +
                "UserNumber=" + UserNumber +
                '}';
    }

    public String getFirstName(int UserNumber){
        //this parses the users account from the constructor
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

        JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
        String FirstName = (String) userCredentialsIn.get("FirstName");
        return FirstName;
    }

    public String getLastName(int UserNumber){
        //this parses the users account from the constructor
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

        JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
        String LastName = (String) userCredentialsIn.get("LastName");
        return LastName;
    }

    public String getRole(int UserNumber){
        //this parses the users account from the constructor
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

        JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
        String Role = (String) userCredentialsIn.get("Role");
        return Role;
    }

    //This function gets the Assignment Name for a specified Assignment
    //Needs TESTING
    public String getAssignmentName(int UserNumber, int assignmentNumber){
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
        JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(1);
        JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
        String assignmentName=(String) assignmentDataIn.get("AssignmentName");
        return assignmentName;
    }

    //This function gets the MaxPoints for a specified Assignment
    //Needs TESTING
    public int getMaxPoints (int UserNumber, int assignmentNumber){
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
        JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(1);
        JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
        int maxPoints=(int) assignmentDataIn.get("MaxPoints");
        return maxPoints;
    }

    //This function gets the PointsRecived for a specified Assignment
    //Needs TESTING
    public int getPointsRecived (int UserNumber, int assignmentNumber){
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
        JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(1);
        JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
        int pointsRecived=(int) assignmentDataIn.get("PointsRecived");
        return pointsRecived;
    }

    //This function gets the Assignment Comments for a specified Assignment
    //Needs TESTING
    public String getComments(int UserNumber, int assignmentNumber){
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
        JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(1);
        JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
        String assignmentComments=(String) assignmentDataIn.get("Comments");
        return assignmentComments;
    }

    //This function gets the AssigmentType for a specified Assignment
    //Needs TESTING
    public String getAssigmentType(int UserNumber, int assignmentNumber){
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
        JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(1);
        JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
        String assigmentType=(String) assignmentDataIn.get("AssigmentType");
        return assigmentType;
    }

    //Needs TESTING
    public void createAppointment(int UserNumber, String Subject, String TutorName, String AppointmentTime,String Location){
        //this parses the users account from the constructor
        JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
        //zero is the code for the user credential storage
        JSONArray AppointmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
        //this is the new object that will be added to the array
        JSONObject AppointmentData = new JSONObject();
        AppointmentData.put("Subject", Subject);
        AppointmentData.put("TutorName", TutorName);
        AppointmentData.put("AppointmentTime", AppointmentTime);
        AppointmentData.put("Location", Location);
        AppointmentArrayInstance.add(AppointmentData);


        //print the JSON Structure
        System.out.println(AccountsIN.toJSONString());

        //now we will create a file and write the json structure to it.
        //makes a file object and passes it as a parameter as a printer
        File file = new File("JSONDATA.txt");
        try (PrintWriter writer = new PrintWriter(file);) {
            writer.print(AccountsIN.toJSONString());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        System.out.println("File updated successfully");
    }

    public void setUserEmail() {

    }

    public void setUsername() {

    }

    public void setPassword() {

    }

    public void setFirstName(int UserNumber, String FirstName){
        //this parses the users account from the constructor
        JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

        JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

        userCredentialsIn.put("Username",FirstName);

        System.out.println(AccountsIN.toJSONString());

        //now we will create a file and write the json structure to it.
        //makes a file object and passes it as a parameter as a printer
        File file = new File("JSONDATA.txt");
        try (PrintWriter writer = new PrintWriter(file);) {
            writer.print(AccountsIN.toJSONString());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
        System.out.println("File created successfully \n\n Hit Return to display");
    }

    public void setLastName() {

    }

    public void createAppointment() {

    }

    public void instantiateAssociations() {

    }

    public void goToProfile() {

    }

    public void goToTutorReviews() {

    }

    public void goToGradesAttendence() {

    }
}
