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
 * Programmers: Hunter Danielson, Brian Withrow. Date: 11/20/2018 Description: This is the Login
 * class that will be used to log users into their accounts. It will be the first class made due to
 * the fact that it needs to identify the users role and, instantiate the user in the role's
 * respective class. This Login will also return a role and a user id, this will be used in the
 * parent controller, to make a object of the respective role. This object will be used for all
 * other controllers to reference to from the parent controller.
 *
 * Version 1.0:
 *
 * Need to do: Still needs to returns the user id and role.
 *
 * Finished Functionality: Verifies login correctly.
 *
 * Version 1.1 Edited 11/25/2018 Created setMainCurrentUser extended login to main
 */


public class Login {

  //this stores the parsed data from the JSON file
  JSONArray AccountsIN;
  static int UserNumber;
  //these are the place holders for the construction of the current user.
  static User currentUserUser;
  static Boolean HardCode = false;

  //Constructor
  Login() {
    //scanner and file name is constant across all read and write classes
    Scanner scanner = new Scanner(System.in);
    File file = new File("JSONDATA.txt");

    //reading to the file

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
      //Prints the Concatenated file lines
      //System.out.println(FileInputConcat.toString());

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

  //checks the username and password of all accounts to check if the combination is correct.
  Boolean LoginValidation(String enteredUsername, String enteredPassword) {
    Boolean LoginCredentials = Boolean.FALSE;
    //this parses the users account from the constructor
    JSONArray userAccountsIN;
    for (int i = 0; i <= AccountsIN.toArray().length - 1; i++) {
      userAccountsIN = (JSONArray) AccountsIN.get(i);
      //zero is the code for the user credential storage
      JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
      String UsernameIn = (String) userCredentialsIn.get("Username");
      String PasswordIn = (String) userCredentialsIn.get("Password");
      System.out.println(UsernameIn);
      System.out.println(PasswordIn);
      if (enteredUsername.equals(UsernameIn) && enteredPassword.equals(PasswordIn)) {
        LoginCredentials = Boolean.TRUE;
        this.UserNumber = i;
        break;
      }
    }
    System.out.println(this.UserNumber);
    System.out.println(returnRole(this.UserNumber));
    if (returnRole(this.UserNumber).equals("Tutor")) {
      this.currentUserUser = new User(this.UserNumber);
    }
    if (returnRole(this.UserNumber).equals("Student")) {
      System.out.println("made object of student");
      this.currentUserUser = new User(this.UserNumber);
      System.out.println("done with making said object");
      System.out.println(this.currentUserUser.toString());
    }
    return LoginCredentials;
  }

  //this appends the User's Information to the UserAccount
  void createUserInformation(String NewUsername, String NewPassword, String FirstName,
      String LastName, String Email, String AccountType) {
    //this stores the individual accounts
    JSONArray NewUserAccount = new JSONArray();
    //this is the object that will store the user credentials
    JSONObject UserInformation = new JSONObject();
    //puts username into the user credentials object
    UserInformation.put("Username", NewUsername);
    UserInformation.put("Password", NewPassword);
    UserInformation.put("FirstName", FirstName);
    UserInformation.put("LastName", LastName);
    UserInformation.put("Email", Email);
    UserInformation.put("AccountType", AccountType);

    //adds the user Credentials to the user accounts
    NewUserAccount.add(UserInformation);
    //creates and adds assignmentsArray, appointmentArray, and reviewsLinkArray
    JSONArray appointmentArray = new JSONArray();
    NewUserAccount.add(appointmentArray);
    JSONArray assignmentsArray = new JSONArray();
    NewUserAccount.add(assignmentsArray);
    JSONArray reviewsLinkArray = new JSONArray();
    NewUserAccount.add(reviewsLinkArray);
    JSONArray loginArray = new JSONArray();
    NewUserAccount.add(loginArray);
    JSONArray quizArray = new JSONArray();
    NewUserAccount.add(quizArray);

    //adds the account to the JSON array
    AccountsIN.add(NewUserAccount);

    //sets the account number so that the createUserInformation function appends to the correct array value
    UserNumber = AccountsIN.toArray().length - 1;
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

  //This sets the Current instance of the user into main for the data to be called from.
  void setMainCurrentUser() {
    //sets all major variables for main
    this.AccountsIN = AccountsIN;
    this.UserNumber = UserNumber;
    //This is to set the user objects that wil have all the information about the user,
    //through the instance.
    if (returnRole(UserNumber) == "Student") {
      this.currentUserUser = new User (UserNumber);
    } else if (returnRole(UserNumber) == "Tutor") {
      this.currentUserUser = new User(UserNumber);
    }
  }

  //returns UserNumber
  public int getUserNumber() {
    return this.UserNumber;
  }

  //this returns the number to check if the user is a tutor or a student.
  String returnRole(int UserNumber) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String Role = (String) userCredentialsIn.get("AccountType");
    return Role;
  }
}
