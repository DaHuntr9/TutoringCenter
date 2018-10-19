package src;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class signupController extends JSONObjectFactory{

  @FXML TextField passwordtxt;

  @FXML TextField usernametxt;

  @FXML Label errortext;

  @FXML
  private void button1Pressed(ActionEvent event) throws IOException {
    JSONObjectFactory JSONFile = new JSONObjectFactory();
    if ( usernametxt.getText().isEmpty() || passwordtxt.getText().isEmpty()  ) {
      errortext.setText("The one of the submission fields are empty");
    }
    else{
      JSONFile.createNewUser(usernametxt.getText(), passwordtxt.getText());
    }
  }
}