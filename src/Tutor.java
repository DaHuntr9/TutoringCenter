package src;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tutor implements Users {
        //This contains all the variables and their associated values for the users instance.
        JSONArray AccountsIN;
        static int UserNumber;
        //Base Constructor to create AccountsIN User array
        public Tutor(){
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
        public Tutor(int UserNumber){
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

        public String getRole(int UserNumber) {
            return null;
        }

        public void setUserEmail() {

        }

        public void setUsername() {

        }

        public void setPassword() {

        }

        public void setFirstName(int n, String FirstName) {

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

