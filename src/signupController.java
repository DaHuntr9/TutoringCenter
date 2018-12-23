package src;

/**
 * Programmer: Hunter Danielson Description of file: This code allows the user added to the JSON
 * file and would append their information to a new account. There is no data included in this new
 * field.
 */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signupController extends Main {

  @FXML
  TextField passwordtxtone;
  @FXML
  TextField passwordtxttwo;
  @FXML
  TextField usernametxt;
  @FXML
  TextField firstnametxt;
  @FXML
  TextField lastnametxt;
  @FXML
  TextField emailtxt;
  @FXML
  ComboBox roledropdown;
  @FXML
  Label errortxt;

  /**
   * Populates the role combo box with "Student" and "Tutor"
   */
  public void initialize() {
    roledropdown.getItems().setAll("Student", "Tutor");
  }

  /**
   * Performs sign up functionality and takes the user to the sign in page to sign in
   */
  @FXML
  private void button1Pressed(ActionEvent event) throws IOException {

    boolean passed = true;
    if (firstnametxt.getText().isEmpty() || lastnametxt.getText().isEmpty() || emailtxt.getText()
        .isEmpty() || roledropdown.getValue() == null) {
      errortxt.setText("Please fill all of the fields");
      passed = false;
    } else if (passwordtxtone.getText().length() + 1 <= 6
        || passwordtxtone.getText().length() - 1 >= 32) {
      errortxt.setText("Username or password needs to be 6-32 characters long");
      passed = false;
    } else if (usernametxt.getText().length() + 1 <= 6
        || usernametxt.getText().length() - 1 >= 32) {
      errortxt.setText("Username or password needs to be 6-32 characters long");
      passed = false;
    } else if (!passwordtxtone.getText().equals(passwordtxttwo.getText())) {
      errortxt.setText("Please make sure that the Passwords match");
      passed = false;
      System.out.println(passwordtxtone.getText());
      System.out.println(passwordtxttwo.getText());
      System.out.println(passwordtxtone.getText().equals(passwordtxttwo.getText()));
    }
    if (passed == true) {
      //check the last parameter to see if it gets the value from the drop down
      newLogin.createUserInformation(usernametxt.getText(), passwordtxtone.getText(),
          firstnametxt.getText(), lastnametxt.getText(),
          emailtxt.getText(), roledropdown.getValue().toString());


      Stage stage = Main.getPrimaryStage();
      Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
      stage.setScene(new Scene(root, 600, 440));
      stage.show();
    }
  }

  /**
   * Takes user to dashboard
   */
  @FXML
  private void goToDashboard(ActionEvent event) throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }
}

