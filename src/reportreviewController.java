package src;

/**
 * Programmer: Hunter Danielson Description of file: This code flags a review as boolean false and
 * prevents other users from being displayed on display reviews.
 */

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class reportreviewController extends ratereviewController {

  @FXML
  Label errortext;


  /**
   * Takes user to the dashboard
   */
  @FXML
  private void goToDashboard(ActionEvent event) throws IOException {

    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  /**
   * Prompts the user with a success message for submission
   */
  @FXML
  private void submit(ActionEvent event) throws IOException {
    newLogin.currentUserUser.setReviewFlagged(userAccountPointer, reviewAccountPointer, true);
    String error = "Thank you for contributing to the community.";
    errortext.setText(error);
  }

}
