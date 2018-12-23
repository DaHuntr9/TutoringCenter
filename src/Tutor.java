package src;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

/**
 * Programmers: Hunter Danielson, Brian Withrow. Date: 11/20/2018 Description:
 *
 * Version 1.0:
 *
 * Need to do: Still needs to returns the user id and role.
 *
 * Finished Functionality: Verifies login correctly.
 */

public class Tutor {

  //This contains all the variables and their associated values for the users instance.
  JSONArray AccountsIN;
  int UserNumber;

  //Base Constructor to create AccountsIN User array
  public Tutor() {
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
  public Tutor(int UserNumber) {
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

  /**
   * toString to test if Object is being made.
   */
  @Override
  public String toString() {
    return "User{" +
        "UserNumber=" + UserNumber +
        '}';
  }


  /**
   * Account information is array position 0
   */
  //Getter fucntions
  public String getUserEmail(int UserNumber) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String Email = (String) userCredentialsIn.get("Email");
    return Email;
  }

  public String getUsername(int UserNumber) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String UsernameIn = (String) userCredentialsIn.get("Username");
    return UsernameIn;
  }

  public String getPassword(int UserNumber) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String PasswordIn = (String) userCredentialsIn.get("Password");
    return PasswordIn;
  }

  public String getFirstName(int UserNumber) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String FirstName = (String) userCredentialsIn.get("FirstName");
    return FirstName;
  }

  public String getLastName(int UserNumber) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String LastName = (String) userCredentialsIn.get("LastName");
    return LastName;
  }

  public String getRole(int UserNumber) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String Role = (String) userCredentialsIn.get("AccountType");
    return Role;
  }

  public String getMajor(int UserNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String Major = (String) userCredentialsIn.get("Major");
    return Major;

  }

  public String getaboutMeText(int UserNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String aboutMeText = (String) userCredentialsIn.get("aboutMeText");
    return aboutMeText;

  }

  public String getProfileIcon(int UserNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);
    String profileIcon = (String) userCredentialsIn.get("ProfileIcon");
    return profileIcon;

  }

  //setters
  public void setUserEmail(int UserNumber, String Email) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("Email", Email);

    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setUsername(int UserNumber, String Username) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("Username", Username);

    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setPassword(int UserNumber, String Password) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("Password", Password);

    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setFirstName(int UserNumber, String FirstName) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("FirstName", FirstName);

    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setLastName(int UserNumber, String LastName) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("LastName", LastName);

    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setMajor(int UserNumber, String major) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("major", major);

    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setAboutMeText(int UserNumber, String aboutMeText) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("aboutMeText", aboutMeText);

    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setProfileIcon(int UserNumber, String location) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("location", location);

    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  /**
   * Appointment information position 2 on the array
   */
  //Needs TESTING
  public void createAppointment(int UserNumber, String Subject, String TutorName,
      String AppointmentDate, String Location) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AppointmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject AppointmentData = new JSONObject();
    AppointmentData.put("Subject", Subject);
    AppointmentData.put("TutorName", TutorName);
    AppointmentData.put("AppointmentDate", AppointmentDate);
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

  //getters
  public Date setAppointmentDate(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(1);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(appointmentNumber);
    Date assignmentDate = (Date) assignmentDataIn.get("AppointmentDate");
    return assignmentDate;
  }

  public String setAppointmentSubject(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(appointmentNumber);
    String Subject = (String) assignmentDataIn.get("Subject");
    return Subject;
  }

  public String setAppointmentLocation(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(appointmentNumber);
    String Location = (String) assignmentDataIn.get("Location");
    return Location;
  }

  public String setAppointmentTutor(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(appointmentNumber);
    String TutorName = (String) assignmentDataIn.get("TutorName");
    return TutorName;
  }

  //setters
  public void setAppointmentDate(int UserNumber, int appointmentNumber, Date AppointmentDate) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(appointmentNumber);
    individualAssignmentData.put("AppointmentDate", AppointmentDate);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setAppointmentSubject(int UserNumber, int appointmentNumber,
      String Subject) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(appointmentNumber);
    individualAssignmentData.put("Subject", Subject);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setAppointmentLocation(int UserNumber, int appointmentNumber, String Location) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(appointmentNumber);
    individualAssignmentData.put("Location", Location);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setAppointmentTutor(int UserNumber, int appointmentNumber, String TutorName) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(appointmentNumber);
    individualAssignmentData.put("TutorName", TutorName);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }


  /**
   * Assignment Functions Array position 3 included: Get Set Create Assignments
   */
  public void createAssignment(String AssigmentName, String AssignmentType, String Comments,
      int MaxPoints, int PointsReceived) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject AssignmentData = new JSONObject();
    AssignmentData.put("AssigmentName", AssigmentName);
    AssignmentData.put("MaxPoint", MaxPoints);
    AssignmentData.put("PointsReceived", PointsReceived);
    AssignmentData.put("Comments", Comments);
    AssignmentData.put("AssigmentType", AssignmentType);
    //adds assignment to the array
    AssignmentArrayInstance.add(AssignmentData);
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

  //getters
  public String getAssignmentName(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    String assignmentName = (String) assignmentDataIn.get("AssignmentName");
    return assignmentName;
  }

  public int getMaxPoints(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    int maxPoints = (int) assignmentDataIn.get("MaxPoints");
    return maxPoints;
  }

  public int getPointsRecived(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    int pointsRecived = (int) assignmentDataIn.get("PointsReceived");
    return pointsRecived;
  }

  public String getComments(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    String assignmentComments = (String) assignmentDataIn.get("Comments");
    return assignmentComments;
  }

  public String getAssigmentType(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    String assigmentType = (String) assignmentDataIn.get("AssigmentType");
    return assigmentType;
  }

  //setters
  public void setAssignmentName(int UserNumber, int assignmentNumber, String AssigmentName) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("AssigmentName", AssigmentName);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setMaxPoints(int UserNumber, int assignmentNumber, int MaxPoints) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("MaxPoints", MaxPoints);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setComments(int UserNumber, int assignmentNumber,
      String Comment) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("Comment", Comment);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  public void setAssignmentType(int UserNumber, int assignmentNumber,
      String AssignmetType) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("AssignmetType", AssignmetType);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }

  //Double check spelling for the assignment points recived when initaly wrtting to it
  public void setPointRecived(int UserNumber, int assignmentNumber,
      int PointRecived) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("PointRecived", PointRecived);
    //print to file and console
    System.out.println(AccountsIN.toJSONString());

    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file);) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File Edited Successfuly");
  }


  /**
   * Login Functions Are position 4 on the array TEST STILL need to go to last object to logout
   * properly
   */
  public void createLoginObject(int UserNumber, Date Login) {
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray LoginArrayInstance = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONObject LoginData = new JSONObject();
    LoginData.put("Login", Login);
    LoginData.put("Logout", Login);
    LoginArrayInstance.add(LoginData);
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

  public void setLogout(int UserNumber, Date Logout) {
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray LoginArrayInstance = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONObject LoginData = new JSONObject();
    LoginData.put("Logout", Logout);
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


  /**
   * What is this.
   */


  public void instantiateAssociations() {

  }

  public void goToProfile() {

  }

  public void goToTutorReviews() {

  }

  public void goToGradesAttendence() {

  }
}
