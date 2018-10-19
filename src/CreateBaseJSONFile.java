package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateBaseJSONFile {
  CreateBaseJSONFile(){
    // scanner input
    Scanner scanner = new Scanner(System.in);




    //this code will make the JSON file structure for the tutoring center
    //this makes the base array that is needed store individual users
    JSONArray Accounts = new JSONArray();

    //this stores the individual accounts
    JSONArray userAccounts = new JSONArray();




    //this is the object that will store the user credentials
    JSONObject userCredentials = new JSONObject();

    //this is how im getting a input for the username and password
    System.out.println("Enter the Username");
    String inputUsername = scanner.nextLine();
    System.out.println("Enter the Password");
    String inputPassword = scanner.nextLine();

    //this puts the fields username and password into the userAccounts Array
    userCredentials.put("Username",inputUsername);
    userCredentials.put("Password",inputPassword);
    //adds the user Credentials to the user accounts
    userAccounts.add(userCredentials);






















    Accounts.add(userAccounts);

    //print the JSON Structure
    System.out.println(Accounts.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(Accounts.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }

    System.out.println("File created successfully \n\n Hit Return to display");
  }
}
