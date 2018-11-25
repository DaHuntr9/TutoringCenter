package src;

import java.awt.event.MouseEvent;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class signinController extends Main{// extends JSONObjectFactory{

  @FXML
  TextField passwordtxt;

  @FXML
  TextField usernametxt;

  @FXML
  Label errortext;

  @FXML

  private void button1Pressed(ActionEvent event) throws IOException {
    if (newLogin.LoginValidation(usernametxt.getText(),passwordtxt.getText())==Boolean.TRUE){
      errortext.setText("Success");
      Stage stage = Main.getPrimaryStage();
      Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
      stage.setScene(new Scene(root, 600, 440));
      stage.show();
      //this is where the code goes to change the page to a home page
    } else {
      String error = "Failed login";
      errortext.setText(error);
    }
  }
  @FXML
  private void hyperlinkPressed(ActionEvent event) throws IOException{
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

}