package src;

/**
 * Programmer: Hunter Danielson Description of file: This code allows the user to be sign in and
 * checks the JSON structure with methods from User. This checks login and also allows the user to
 * be redirect to the Sign in page.
 */

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class signinController extends Main {// extends JSONObjectFactory{

  @FXML
  TextField passwordtxt;

  @FXML
  TextField usernametxt;

  @FXML
  Label errortext;


  /**
   * Makes the ENTER key execute the sign in method "button1Pressed()"
   */
  @FXML
  private void enterClicked(KeyEvent keyEvent) throws IOException {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      button1Pressed();
    }
  }

  @FXML

  /**
   * Executes the sign in functionality
   */
  private void button1Pressed() throws IOException {
    if (newLogin.LoginValidation(usernametxt.getText(), passwordtxt.getText()) == Boolean.TRUE) {
      errortext.setText("Success");
      Stage stage = Main.getPrimaryStage();
      Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
      stage.setScene(new Scene(root, 600, 440));
      stage.show();
      //this is where the code goes to change the page to a home page
    } else {
      String error = "Failed login"; // Fills the errortext string with appropriate message
      errortext.setText(error);
    }
  }

  /**
   * Opens the sign up window
   */
  @FXML
  private void hyperlinkPressed(ActionEvent event) throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

}