package src;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class signupController extends JSONObjectFactory{

  @FXML TextField passwordtxt;

  @FXML TextField usernametxt;
  @FXML TextField firstnametxt;
  @FXML TextField lastnametxt;
  @FXML TextField emailtxt;
  @FXML ComboBox roledropdown;
  @FXML Label errortxt;

  public void initialize() {

    assert roledropdown
        != null : "fx:id=\"roledropdown\" was not injected: check your FXML file 'fruitcombo.fxml'.";
    // populate the fruit combo box with item choices.
    roledropdown.getItems().setAll("Student", "Tutor");
  }
  @FXML
  private void button1Pressed(ActionEvent event) throws IOException {
    JSONObjectFactory JSONFile = new JSONObjectFactory();
    if ( usernametxt.getText().isEmpty() || passwordtxt.getText().isEmpty()|| firstnametxt.getText().isEmpty() || lastnametxt.getText().isEmpty() || emailtxt.getText().isEmpty()||roledropdown.getSelectionModel().isEmpty()) {
      errortxt.setText("The one of the submission fields are empty");
    }
    else{
      //JSONFile.createNewUser(usernametxt.getText(), passwordtxt.getText());
      //check the last parameter to see if it gets the value from the drop down
      JSONFile.createUserInformation(usernametxt.getText(),passwordtxt.getText(),firstnametxt.getText(),lastnametxt.getText(),emailtxt.getText(),roledropdown.getPromptText());
    }
  }
}
