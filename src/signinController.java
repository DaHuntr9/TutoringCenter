package src;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class signinController extends JSONObjectFactory{

  @FXML TextField passwordtxt;

  @FXML TextField usernametxt;

  @FXML Label errortext;

  @FXML
  private void button1Pressed(ActionEvent event) throws IOException {
    JSONObjectFactory JSONFile = new JSONObjectFactory();
    if (JSONFile.LoginValidation(usernametxt.getText(), passwordtxt.getText()) == Boolean.TRUE) {
      errortext.setText("Success");
      //this is where the code goes to change the page to a home page
    }
    else{
      String error = "Failed login";
      errortext.setText(error);
    }

  }
}
