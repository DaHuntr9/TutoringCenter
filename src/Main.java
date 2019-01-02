package src;

/**
 * Programmer: Hunter Danielson, Carl Reyes Date:9/15/2018 Description of file: This code sets up
 * the stage on running the application. This startup is default start up is signin.fxml which
 * activates the scene and stage.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

public class Main extends Application {

  private static Stage primaryStage;

  // Creates a new login instance that manages the current user
  static Login newLogin = new Login();
  //This will be the Current User Account to prevent extened function calls.
  static User currentUser;

  int QuizNumberReference;

  // Loads the sign in fxml and sets the primary stage
  @Override
  public void start(Stage primaryStage) throws Exception {
    setPrimaryStage(primaryStage);
    Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
    //primaryStage.setResizable(false);
    primaryStage.setTitle("Tutoring App");
    primaryStage.setScene(new Scene(root, 600, 440));
    primaryStage.show();
  }

  private void setPrimaryStage(Stage stage) {
    Main.primaryStage = stage;
  }

  static public Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void main(String[] args) {
    launch(args);
  }


  /**
   * This function will return all Users whos roles are Tutors.
   * In doing so this will reduce the amount of code that will
   * be written in intializations statments (for controller classes).
   * @return String Array to be added to dropdowns on initalization

  // First problem is newLogin not being reconized.
  //Second problem is potential legacy functions can not be called in the same manor.
  public String getAllTutorsNames(){
    //need to figure out where new login exists.
    for (int i = 0; i <=  currentUser.getTotalNumberOfAccounts() - 1; i++){
      if(newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Tutor")){
        JSONObject uswerInformationObject = getUserInformationObject(UserNumber);
      }
    }
  }
  */

}