package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BackEnd {

  public static void addUser(String Username, String Password) throws IOException {
      //Referencing Login Text File
      File file = new File("test.txt");
      //Creating scanner object
      Scanner scan1 = new Scanner(file);
      String inputUsername = "Username:" + Username;
      //username found boolean
      boolean found = false;
      //loop looking for username
      while (scan1.hasNextLine()) {

          // Stores initial input in variable for comparison
          String i = scan1.nextLine();

          // Checks the users input in comparison to the first line present in text doc
          if (inputUsername.equals(i)) {
              found = true;
          }
      }
      //if username is not found, create one
      if (!found) {
          //instances PrintWriter
          FileWriter writer = new FileWriter(file, true);
          // Writes to Text doc
          writer.write("Username:" + Username + "\r\n");
          writer.write("Password:" + Password + "\r\n");
          //Closes file an writer object
          writer.close();

          //to call the function you must use the following code
  /*
   try {
          BackEnd.addUser(userTextField.getText(),pwBox.getText());
        } catch (IOException e1) {
          e1.printStackTrace();
        }
   */
      }
  }

  public static boolean logInAuth(String Username, String Password) {
    File file = new File("test.txt");
    //Creates and initializes the scanner
    //Scanner keyboard = new Scanner(System.in);
    //start of try catch code for file not found exception
    try {
      //creates new scanner
      Scanner Scan = new Scanner(file);

      // Scans the users input for comparison
      // String must be in the text document format to prevent search issues
      String inputUsername = "Username:" + Username;

      // Scans the users input for comparison
      // String must be in the text document format to prevent search issues
      String inputPassword = "Password:" + Password;

      // Scans through text document
      while (Scan.hasNextLine()) {

        // Stores initial input in variable for comparison
        String i = Scan.nextLine();

        // Checks the users input in comparison to the first line present in text doc
        if (inputUsername.equals(i)) {

          // If the Username is correct then it will compare to the password stored below it
          if (Scan.nextLine().equals(inputPassword)) {

            //upon Completion it will prompt a success
            System.out.println("success");
            return true;

            //breaks from while loop upon success
            //break;
          }
        }
      }
      // Closes the Scanner
      Scan.close();
    }
    // Catches FileNotFoundException e
    catch (FileNotFoundException e) {

      // Prints to Stack Trace
      e.printStackTrace();
    }
    //Below is the function that is called inorder for the function to find the text box and then compare the strings
    //BackEnd.logInAuth(userTextField.getText(),pwBox.getText());
      return false;
  }
}