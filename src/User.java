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
 * Programmers: Hunter Danielson, Brian Withrow. Date: 11/20/2018 Description:
 *
 * Version 1.0:
 *
 * Need to do: Still needs to returns the user id and role.
 *
 * Finished Functionality: Verifies login correctly.
 *
 * Version 1.1:
 * Assignments getter setter and creation adjusted.
 *
 * Notes:
 * This code is extremely Redundent but upon trying to condense it into substitute functions,
 * the Getters and Setters would produce errors. So I left the document with un-condensed functions
 * that operate correctly.
 */

public class User {

  //This contains all the variables and their associated values for the users instance.
  JSONArray AccountsIN;
  int UserNumberA;

  //Base Constructor to create AccountsIN User array
  public User() {
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
  public User(int UserNumber) {
    this.UserNumberA = UserNumber;
    File file = new File("JSONDATA.txt");
    try {
      Scanner FileInput = new Scanner(file);
      StringBuilder FileInputConcat = new StringBuilder();
      while (FileInput.hasNextLine()) {
        FileInputConcat.append(FileInput.nextLine());
      }
      JSONParser parser = new JSONParser();
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
        "UserNumber=" + UserNumberA +
        '}';
  }

  /**
   * Account information is array position 0
   */
  //Getter functions
  public int getTotalNumberOfAccounts() {
    int totalNumberOfAccounts = AccountsIN.size();
    //JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //int totalNumberOfAccounts = userAccountsIN.size();
    return totalNumberOfAccounts;
  }

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
    String Major = (String) userCredentialsIn.get("major");
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

  public void setRole(int UserNumber, String AccountType) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("AccountType", AccountType);

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

  public void setProfileIcon(int UserNumber, String ProfileIcon) {
    //this parses the users account from the constructor
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);

    JSONObject userCredentialsIn = (JSONObject) userAccountsIN.get(0);

    userCredentialsIn.put("ProfileIcon", ProfileIcon);

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
   * Appointment information position 1 on the array
   */
  public void createAppointment(int UserNumber, String Subject, String TutorName,
      String AppointmentDate, String Location, String Attendance, String Time, String Comments) {
    //this parses the users account from the constructor
    //System.out.println("start");
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    //System.out.println("AccountsIN present");
    JSONArray AppointmentArrayInstance = (JSONArray) UserAccountsIN.get(1);
    //System.out.println("accessed the accounts array");
    //this is the new object that will be added to the array
    JSONObject AppointmentData = new JSONObject();
    //System.out.println("made new object");
    AppointmentData.put("Subject", Subject);
    //System.out.println(Subject);
    AppointmentData.put("TutorName", TutorName);
    //System.out.println(TutorName);
    AppointmentData.put("AppointmentDate", AppointmentDate);
    //System.out.println(AppointmentDate);
    AppointmentData.put("Location", Location);
    AppointmentData.put("Time", Time);
    //System.out.println(Location);
    AppointmentData.put("Comments", Comments);
    AppointmentData.put("Attendance", Attendance);
    //System.out.println(Attendance);
    AppointmentArrayInstance.add(AppointmentData);
    //System.out.println("added");

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

  public void setAppointmentComment(int UserNumber, int appointmentNumber, String Comments) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AppointmentArrayInstance = (JSONArray) UserAccountsIN.get(1);
    //this is the new object that will be added to the array
    JSONObject individualAppointmentData = (JSONObject) AppointmentArrayInstance
        .get(appointmentNumber);
    individualAppointmentData.put("Comments", Comments);
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

  //getters
  public int getNumberOfAppointments(int UserNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray appointmentsArrayIn = (JSONArray) userAccountsIN.get(1);
    int size = appointmentsArrayIn.size();
    return size;
  }


  public String getAppointmentTime(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray appointmentsArrayIn = (JSONArray) userAccountsIN.get(1);
    JSONObject assignmentDataIn = (JSONObject) appointmentsArrayIn.get(appointmentNumber);
    String assignmentTime = (String) assignmentDataIn.get("Time");
    return assignmentTime;
  }

  public String getAppointmentComments(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray appointmentsArrayIn = (JSONArray) userAccountsIN.get(1);
    JSONObject assignmentDataIn = (JSONObject) appointmentsArrayIn.get(appointmentNumber);
    String assignmentComments = (String) assignmentDataIn.get("Comments");
    return assignmentComments;
  }

  public String getAppointmentDate(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray appointmentsArrayIn = (JSONArray) userAccountsIN.get(1);
    JSONObject assignmentDataIn = (JSONObject) appointmentsArrayIn.get(appointmentNumber);
    String assignmentDate = (String) assignmentDataIn.get("AppointmentDate");
    return assignmentDate;
  }

  public String getAppointmentSubject(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray appointmentsArrayIn = (JSONArray) userAccountsIN.get(1);
    JSONObject assignmentDataIn = (JSONObject) appointmentsArrayIn.get(appointmentNumber);
    String Subject = (String) assignmentDataIn.get("Subject");
    return Subject;
  }

  public String getAppointmentLocation(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray appointmentsArrayIn = (JSONArray) userAccountsIN.get(1);
    JSONObject assignmentDataIn = (JSONObject) appointmentsArrayIn.get(appointmentNumber);
    String Location = (String) assignmentDataIn.get("Location");
    return Location;
  }

  public String getAppointmentTutor(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray appointmentsArrayIn = (JSONArray) userAccountsIN.get(1);
    JSONObject assignmentDataIn = (JSONObject) appointmentsArrayIn.get(appointmentNumber);
    String TutorName = (String) assignmentDataIn.get("TutorName");
    return TutorName;
  }

  public String getAppointmentAttendance(int UserNumber, int appointmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray appointmentsArrayIn = (JSONArray) userAccountsIN.get(1);
    JSONObject assignmentDataIn = (JSONObject) appointmentsArrayIn.get(appointmentNumber);
    String Attendance = (String) assignmentDataIn.get("Attendance");
    return Attendance;
  }

  //setters
  public void setAppointmentDate(int UserNumber, int appointmentNumber, String AppointmentDate) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray appointmentArrayInstance = (JSONArray) UserAccountsIN.get(1);
    //this is the new object that will be added to the array
    JSONObject individualAppointmentdata = (JSONObject) appointmentArrayInstance
        .get(appointmentNumber);
    individualAppointmentdata.put("AppointmentDate", AppointmentDate);
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

  public void setAppointmentTime(int UserNumber, int appointmentNumber, String AppointmentTime) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray appointmentArrayInstance = (JSONArray) UserAccountsIN.get(1);
    //this is the new object that will be added to the array
    JSONObject individualAppointmentdata = (JSONObject) appointmentArrayInstance
        .get(appointmentNumber);
    individualAppointmentdata.put("Time", AppointmentTime);
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
    JSONArray appointmentArrayInstance = (JSONArray) UserAccountsIN.get(1);
    //this is the new object that will be added to the array
    JSONObject individualAppointmentData = (JSONObject) appointmentArrayInstance
        .get(appointmentNumber);
    //potential spelling error
    individualAppointmentData.put("Subject", Subject);
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
    JSONArray AppointmentArrayInstance = (JSONArray) UserAccountsIN.get(1);
    //this is the new object that will be added to the array
    JSONObject individualAppointmentData = (JSONObject) AppointmentArrayInstance
        .get(appointmentNumber);
    individualAppointmentData.put("Location", Location);
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
    JSONArray AppointmentArrayInstance = (JSONArray) UserAccountsIN.get(1);
    //this is the new object that will be added to the array
    JSONObject individualAppointmentData = (JSONObject) AppointmentArrayInstance
        .get(appointmentNumber);
    individualAppointmentData.put("TutorName", TutorName);
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

  public void setAppointmentAttendance(int UserNumber, int appointmentNumber, String Attendance) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AppointmentArrayInstance = (JSONArray) UserAccountsIN.get(1);
    //this is the new object that will be added to the array
    JSONObject individualAppointmentData = (JSONObject) AppointmentArrayInstance
        .get(appointmentNumber);
    individualAppointmentData.put("Attendance", Attendance);
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
   * Assignment Functions Array position 2 included: Get Set Create Assignments
   */
  public void createAssignment(int UserNumber, String AssigmentName, String AssignmentType,
      String Comments, String MaxPoints, String PointsReceived, String datePicked,
      String timePicked, String selectedStudent) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject AssignmentData = new JSONObject();

    AssignmentData.put("AssignmentName", AssigmentName);
    AssignmentData.put("MaxPoints", MaxPoints);
    AssignmentData.put("PointsReceived", PointsReceived);
    AssignmentData.put("Comments", Comments);
    AssignmentData.put("Date Due", datePicked);
    AssignmentData.put("Time Due", timePicked);
    AssignmentData.put("AssignmentType", AssignmentType);
    AssignmentData.put("SelectedStudent", selectedStudent);
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
  public int getNumberOfAssignments(int UserNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmentsArrayIn = (JSONArray) userAccountsIN.get(2);
    int size = assignmentsArrayIn.size();
    return size;
  }

  public String getAssignmentDatePicked(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    String Date = (String) assignmentDataIn.get("Date Due");
    return Date;
  }

  public String getAssignmentTimePicked(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    String Time = (String) assignmentDataIn.get("Time Due");
    return Time;
  }

  public String getAssignmentSelectedStudent(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    String SelectedStudent = (String) assignmentDataIn.get("SelectedStudent");
    return SelectedStudent;
  }

  public String getAssignmentName(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmentsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmentsArrayIn.get(assignmentNumber);
    String assignmentName = (String) assignmentDataIn.get("AssignmentName");
    return assignmentName;
  }

  public String getAssignmentMaxPoints(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    //long vs int cant write and read in the same method or it will need to be a integer
    String maxPoints = (String) assignmentDataIn.get("MaxPoints");
    return maxPoints;
  }

  public String getAssignmentPointsReceived(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    String pointsRecived = (String) assignmentDataIn.get("PointsReceived");
    return pointsRecived;
  }

  public String getAssignmentComments(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(assignmentNumber);
    String assignmentComments = (String) assignmentDataIn.get("Comments");
    return assignmentComments;
  }

  public String getAssignmentType(int UserNumber, int assignmentNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmentsArrayIn = (JSONArray) userAccountsIN.get(2);
    JSONObject assignmentDataIn = (JSONObject) assignmentsArrayIn.get(assignmentNumber);
    String AssignmentType = (String) assignmentDataIn.get("AssignmentType");
    return AssignmentType;
  }

  //setters
  public void setAssignmentName(int UserNumber, int assignmentNumber, String AssignmentName) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("AssignmentName", AssignmentName);
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
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
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

  public void setPointsReceived(int UserNumber, int assignmentNumber,
      int PointsReceived) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("PointsReceived", PointsReceived);
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
      String Comments) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("Comments", Comments);
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
      String AssignmentType) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(2);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("AssignmentType", AssignmentType);
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
   * need create reviews section position 3
   */
  public void createReview(int UserNumber, String TutorName, String Subject, String Comments,
      Boolean ReviewFlagged, String ReviewValue) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray ReviewArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject ReviewData = new JSONObject();
    ReviewData.put("TutorName", TutorName);
    ReviewData.put("Subject", Subject);
    ReviewData.put("Comments", Comments);
    ReviewData.put("ReviewFlagged", ReviewFlagged);
    ReviewData.put("ReviewValue", ReviewValue);
    //adds assignment to the array
    ReviewArrayInstance.add(ReviewData);
    //print the JSON Structure
    System.out.println(AccountsIN.toJSONString());
    //now we will create a file and write the json structure to it.
    //makes a file object and passes it as a parameter as a printer
    File file = new File("JSONDATA.txt");
    try (PrintWriter writer = new PrintWriter(file)) {
      writer.print(AccountsIN.toJSONString());
    } catch (FileNotFoundException ex) {
      System.out.println(ex.toString());
    }
    System.out.println("File updated successfully");
  }

  //getters
  public int getNumberOfReview(int UserNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    int numberOfReviews = assignmnetsArrayIn.size();
    return numberOfReviews;
  }

  public String getReviewTutor(int UserNumber, int reviewNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(reviewNumber);
    String TutorName = (String) assignmentDataIn.get("TutorName");
    return TutorName;
  }

  public String getReviewSubject(int UserNumber, int reviewNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(reviewNumber);
    String StudentName = (String) assignmentDataIn.get("Subject");
    return StudentName;
  }

  public String getReviewComment(int UserNumber, int reviewNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(reviewNumber);
    String Comments = (String) assignmentDataIn.get("Comments");
    return Comments;
  }

  public boolean getReviewFlagged(int UserNumber, int reviewNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(reviewNumber);
    Boolean ReviewFlagged = (Boolean) assignmentDataIn.get("ReviewFlagged");
    return ReviewFlagged;
  }

  public String getReviewValue(int UserNumber, int reviewNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(reviewNumber);
    String ReviewValue = (String) assignmentDataIn.get("ReviewValue");
    return ReviewValue;
  }

  /**
   * public Integer getReviewValueInteger(int UserNumber, int reviewNumber) { JSONArray
   * userAccountsIN = (JSONArray) AccountsIN.get(UserNumber); JSONArray assignmnetsArrayIn =
   * (JSONArray) userAccountsIN.get(3); JSONObject assignmentDataIn = (JSONObject)
   * assignmnetsArrayIn.get(reviewNumber); Integer ReviewValue = (Integer)
   * assignmentDataIn.get("ReviewValue"); return ReviewValue; } public long getReviewValuelong(int
   * UserNumber, int reviewNumber) { JSONArray userAccountsIN = (JSONArray)
   * AccountsIN.get(UserNumber); JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
   * JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(reviewNumber); long
   * ReviewValue = (long) assignmentDataIn.get("ReviewValue"); return ReviewValue; }
   */
  public Integer getReviewValueForAfterWriting(int UserNumber, int reviewNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray assignmnetsArrayIn = (JSONArray) userAccountsIN.get(3);
    JSONObject assignmentDataIn = (JSONObject) assignmnetsArrayIn.get(reviewNumber);
    Integer ReviewValue = (Integer) assignmentDataIn.get("ReviewValue");
    return ReviewValue;
  }

  //setters
  public void setReviewTutor(int UserNumber, int assignmentNumber, String TutorName) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
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

  public void setReviewSubject(int UserNumber, int assignmentNumber, String SubjectName) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("Subject", SubjectName);
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

  public void setReviewComment(int UserNumber, int assignmentNumber,
      String Comments) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("Comments", Comments);
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

  public void setReviewFlagged(int UserNumber, int assignmentNumber,
      boolean ReviewFlagged) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("ReviewFlagged", ReviewFlagged);
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

  public void setReviewValue(int UserNumber, int assignmentNumber,
      String ReviewValue) {//this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray AssignmentArrayInstance = (JSONArray) UserAccountsIN.get(3);
    //this is the new object that will be added to the array
    JSONObject individualAssignmentData = (JSONObject) AssignmentArrayInstance
        .get(assignmentNumber);
    individualAssignmentData.put("ReviewValue", ReviewValue);
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
   * need create quiz section position 4
   */
  //creates a new quiz array every time there needs to be a function to add the quiz questions to the individual arrays.
  public void addQuizQuestion(int UserNumber, int QuizNumber, String QuizQuestion, String AnswerOne,
      String AnswerTwo,
      String AnswerThree, String AnswerFour, String QuestionAnswer, String UserAnswer) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray QuizArrayInstanceArray = (JSONArray) UserAccountsIN.get(4);
    //the individual quiz
    JSONArray IndividualQuizArray = (JSONArray) QuizArrayInstanceArray.get(QuizNumber);
    JSONObject QuizData = new JSONObject();
    QuizData.put("QuizQuestion", QuizQuestion);
    QuizData.put("AnswerOne", AnswerOne);
    QuizData.put("AnswerTwo", AnswerTwo);
    QuizData.put("AnswerThree", AnswerThree);
    QuizData.put("AnswerFour", AnswerFour);
    QuizData.put("QuestionAnswer", QuestionAnswer);
    QuizData.put("UserAnswer", UserAnswer);
    //adds assignment to the array
    IndividualQuizArray.add(QuizData);

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

  public void createQuiz(int UserNumber) {
    //String QuizQuestion, String AnswerOne, String AnswerTwo, String AnswerThree, String AnswerFour, String QuestionAnswer, String UserAnswer
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray QuizArrayInstanceArray = (JSONArray) UserAccountsIN.get(4);
    //the individual quiz
    JSONArray IndividualQuizArray = new JSONArray();
    QuizArrayInstanceArray.add(IndividualQuizArray);
    //this is the new object that will be added to the array
    /*JSONObject QuizData = new JSONObject();
    QuizData.put("QuizQuestion", QuizQuestion);
    QuizData.put("AnswerOne", AnswerOne);
    QuizData.put("AnswerTwo", AnswerTwo);
    QuizData.put("AnswerThree", AnswerThree);
    QuizData.put("AnswerFour", AnswerFour);
    QuizData.put("QuestionAnswer", QuestionAnswer);
    QuizData.put("UserAnswer", UserAnswer);
    //adds assignment to the array
    IndividualQuizArray.add(QuizData);*/

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
  public int getNumberOfQuizzes(int UserNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    int numberOfQuizzes = quizArrayIn.size();
    return numberOfQuizzes;
  }

  public int getNumberOfQuestions(int UserNumber, int quizNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    int numberOfQuestions = quizNumberArrayIn.size();
    return numberOfQuestions;
  }

  public String getQuizQuestion(int UserNumber, int quizNumber, int quizQuestionNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject quizDataIn = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    String QuizQuestion = (String) quizDataIn.get("QuizQuestion");
    return QuizQuestion;
  }

  public String getAnswerOne(int UserNumber, int quizNumber, int quizQuestionNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject quizDataIn = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    String AnswerOne = (String) quizDataIn.get("AnswerOne");
    return AnswerOne;
  }

  public String getAnswerTwo(int UserNumber, int quizNumber, int quizQuestionNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject quizDataIn = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    String AnswerTwo = (String) quizDataIn.get("AnswerTwo");
    return AnswerTwo;
  }

  public String getAnswerThree(int UserNumber, int quizNumber, int quizQuestionNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject quizDataIn = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    String AnswerThree = (String) quizDataIn.get("AnswerThree");
    return AnswerThree;
  }

  public String getAnswerFour(int UserNumber, int quizNumber, int quizQuestionNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject quizDataIn = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    String AnswerFour = (String) quizDataIn.get("AnswerFour");
    return AnswerFour;
  }

  public String getQuestionAnswer(int UserNumber, int quizNumber, int quizQuestionNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject quizDataIn = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    String QuestionAnswer = (String) quizDataIn.get("QuestionAnswer");
    return QuestionAnswer;
  }

  public String getUserAnswer(int UserNumber, int quizNumber, int quizQuestionNumber) {
    JSONArray userAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    JSONArray quizArrayIn = (JSONArray) userAccountsIN.get(4);
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject quizDataIn = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    String UserAnswer = (String) quizDataIn.get("UserAnswer");
    return UserAnswer;
  }

  //setters
  public void setQuizQuestion(int UserNumber, String QuizQuestion, int quizNumber,
      int quizQuestionNumber) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray quizArrayIn = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject individualAssignmentData = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    individualAssignmentData.put("QuizQuestion", QuizQuestion);
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

  public void setAnswerOne(int UserNumber, String AnswerOne, int quizNumber,
      int quizQuestionNumber) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray quizArrayIn = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject individualAssignmentData = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    individualAssignmentData.put("AnswerOne", AnswerOne);
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

  public void setAnswerTwo(int UserNumber, String AnswerTwo, int quizNumber,
      int quizQuestionNumber) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray quizArrayIn = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject individualAssignmentData = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    individualAssignmentData.put("AnswerTwo", AnswerTwo);
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

  public void setAnswerThree(int UserNumber, String AnswerThree, int quizNumber,
      int quizQuestionNumber) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray quizArrayIn = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject individualAssignmentData = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    individualAssignmentData.put("AnswerThree", AnswerThree);
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

  public void setAnswerFour(int UserNumber, String AnswerFour, int quizNumber,
      int quizQuestionNumber) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray quizArrayIn = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject individualAssignmentData = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    individualAssignmentData.put("AnswerFour", AnswerFour);
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

  public void setQuestionAnswer(int UserNumber, String QuestionAnswer, int quizNumber,
      int quizQuestionNumber) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray quizArrayIn = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject individualAssignmentData = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    individualAssignmentData.put("QuestionAnswer", QuestionAnswer);
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

  public void setUserAnswer(int UserNumber, String UserAnswer, int quizNumber,
      int quizQuestionNumber) {
    //this parses the users account from the constructor
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray quizArrayIn = (JSONArray) UserAccountsIN.get(4);
    //this is the new object that will be added to the array
    JSONArray quizNumberArrayIn = (JSONArray) quizArrayIn.get(quizNumber);
    JSONObject individualAssignmentData = (JSONObject) quizNumberArrayIn.get(quizQuestionNumber);
    individualAssignmentData.put("UserAnswer", UserAnswer);
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
   * Login Functions Are position 5 on the array
   */
  public void createLoginObject(int UserNumber, String Login) {
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray LoginArrayInstance = (JSONArray) UserAccountsIN.get(5);
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

  public void setLogout(int UserNumber, String Logout) {
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray LoginArrayInstance = (JSONArray) UserAccountsIN.get(5);
    //this is the new object that will be added to the array
    JSONObject LoginData = (JSONObject) LoginArrayInstance.get(LoginArrayInstance.size() - 1);
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

  public String getLogin(int UserNumber, int loginNumber) {
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray LoginArrayInstance = (JSONArray) UserAccountsIN.get(5);
    JSONObject quizDataIn = (JSONObject) LoginArrayInstance.get(loginNumber);
    String Login = (String) quizDataIn.get("Login");
    return Login;
  }

  public String getLogout(int UserNumber, int logoutNumber) {
    JSONArray UserAccountsIN = (JSONArray) AccountsIN.get(UserNumber);
    //zero is the code for the user credential storage
    JSONArray LoginArrayInstance = (JSONArray) UserAccountsIN.get(5);
    JSONObject quizDataIn = (JSONObject) LoginArrayInstance.get(logoutNumber);
    String Logout = (String) quizDataIn.get("Logout");
    return Logout;
  }
}