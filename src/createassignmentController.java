package src;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class createassignmentController extends Main {

  @FXML
  JFXDatePicker datepicked;

  @FXML
  JFXTimePicker timepicked;

  @FXML
  JFXTextArea assignmentname;

  @FXML
  JFXTextArea maxpoints;

  @FXML
  JFXTextArea pointsreceived;

  @FXML
  JFXTextArea assignmenttype;

  @FXML
  JFXTextArea comments;

  @FXML
  JFXComboBox selectedstudent;

  @FXML
  Label ErrorAssignmnets;

  /**
   *
   */
  @FXML
  private void createAssignment(ActionEvent event) throws IOException {
    //Pull from fields
    //Write to JSONDATA.txt
    try {

      int StudentNumber = 0;
      for (int i = 0; i <= newLogin.currentUserUser.getTotalNumberOfAccounts() - 1; i++) {
        String role = newLogin.currentUserUser.getRole(i);
        String StudentName = newLogin.currentUserUser.getFirstName(i);
        if (role.equals("Student")) {
          if (StudentName.equals(selectedstudent.getValue().toString())) {
            StudentNumber = i;
          }
        }
      }

      String assignmentName = assignmentname.getText();
      String maxPoints = maxpoints.getText();
      String pointsReceived = pointsreceived.getText();
      String Comments = comments.getText();
      String datePicked = datepicked.getValue().toString();
      String timePicked = timepicked.getValue().toString();
      String assignmentType = assignmenttype.getText();
      String selectedStudent = selectedstudent.getValue().toString();
      newLogin.currentUserUser
          .createAssignment(StudentNumber, assignmentName, assignmentType, Comments,
              maxPoints, pointsReceived, datePicked, timePicked, selectedStudent);
      ErrorAssignmnets.setText("Submitted Assignment");
      ErrorAssignmnets.setVisible(true);
      assignmentname.setText("");
      maxpoints.setText("");
      pointsreceived.setText("");
      comments.setText("");
      timepicked.setValue(null);
      datepicked.setValue(null);
      selectedstudent.setValue(null);
      assignmenttype.setText("");
    } catch (Exception e) {
      ErrorAssignmnets.setVisible(true);
    }
  }


  @FXML
  private void goToDashboard(ActionEvent event) throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  /**
   * Executes default code when createAssignment is called
   */
  @FXML
  public void initialize() {

    for (int i = 0; i <= newLogin.currentUserUser.getTotalNumberOfAccounts() - 1; i++) {
      String role = newLogin.currentUserUser.getRole(i);
      String tutorName = newLogin.currentUserUser.getFirstName(i);
      if (role.equals("Student")) {
        selectedstudent.getItems().addAll(tutorName);
      }
    }
  }

}
