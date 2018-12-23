package src;

/**
 * Programmer: Martian Marks,Hunter Danielson
 * Description of file: This code reads a person's grades and
 * attendance and add them to a stack of associated variables. Because of this the data needs to be
 * read every-time you press update it, it doesn't store the graphs data.
 */

import java.io.IOException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class gradesController extends Main {

  @FXML
  PieChart pieChart;

  @FXML
  JFXComboBox<String> pieChartTypeSelector;

  @FXML
  JFXComboBox StudentSelector;

  @FXML
  Label ErrorGrades;

  @FXML
  public void initialize() {
    if (newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Tutor")) {
      StudentSelector.setVisible(true);
    }
    for (int i = 0; i <= newLogin.currentUserUser.getTotalNumberOfAccounts() - 1; i++) {
      String role = newLogin.currentUserUser.getRole(i);
      String StudentName = newLogin.currentUserUser.getFirstName(i);
      if (role.equals("Student")) {
        StudentSelector.getItems().addAll(StudentName);
      }
    }
  }


  /**
   * Takes the user to the dashboard
   */
  @FXML
  private void goToDashboard(ActionEvent event) throws IOException {
    Stage stage = Main.getPrimaryStage();

    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

    stage.setScene(new Scene(root, 600, 440));

    stage.show();
  }

  /**
   * Updates the graph according to the selection from the combo box
   */
  @FXML
  private void update() {
    try {
      if (newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Tutor")) {
        System.out.println("attempting update");

        int StudentNumber = 0;
        for (int i = 0; i <= newLogin.currentUserUser.getTotalNumberOfAccounts() - 1; i++) {
          String role = newLogin.currentUserUser.getRole(i);
          String StudentName = newLogin.currentUserUser.getFirstName(i);
          if (role.equals("Student")) {
            if (StudentName.equals(StudentSelector.getValue().toString())) {
              StudentNumber = i;
            }
          }
        }
        System.out.println(StudentNumber);
        // If the combo box has "Grades" selected
        if (pieChartTypeSelector.getValue().equals("Grades")) {
          int A = 0;
          int B = 0;
          int C = 0;
          int D = 0;
          int F = 0;
          int counter = 0;
          while (counter < newLogin.currentUserUser
              .getNumberOfAssignments(StudentNumber)) {
            double a = Double.parseDouble(newLogin.currentUserUser
                .getAssignmentPointsReceived(StudentNumber, counter));
            double b = Double.parseDouble(
                newLogin.currentUserUser.getAssignmentMaxPoints(StudentNumber, counter));
            System.out.println(a);
            System.out.println(b);
            double Calculation = (a / b);
            if (Calculation >= 0.90) {
              A++;
            }
            if (Calculation < 0.90 && Calculation >= 0.80) {
              B++;
            }
            if (Calculation < 0.80 && Calculation >= 0.70) {
              C++;
            }
            if (Calculation < 0.70 && Calculation >= 0.60) {
              D++;
            }
            if (Calculation < 0.60) {
              F++;
            }
            counter++;
          }

          // Loads grade elements
          ObservableList<PieChart.Data> pieChartData =
              FXCollections.observableArrayList(
                  new PieChart.Data("A", A),
                  new PieChart.Data("B", B),
                  new PieChart.Data("C", C),
                  new PieChart.Data("D", D),
                  new PieChart.Data("F", F)
              );
          pieChart.setData(pieChartData);
          pieChart.setTitle("Grades");

          // Displays grade elements
          Main.getPrimaryStage().show();
          ErrorGrades.setVisible(false);
        }

        if (pieChartTypeSelector.getValue().equals("Attendance")) {
          // Loads attendance elements
          //Late, ontime, Absent
          int Late = 0;
          int ontime = 0;
          int Absent = 0;
          int counter = 0;
          while (counter < newLogin.currentUserUser
              .getNumberOfAppointments(StudentNumber)) {
            if (newLogin.currentUserUser.getAppointmentAttendance(StudentNumber, counter)
                .equals("Late")) {
              Late++;
            }
            if (newLogin.currentUserUser.getAppointmentAttendance(StudentNumber, counter)
                .equals("ontime")) {
              ontime++;
            }
            if (newLogin.currentUserUser.getAppointmentAttendance(StudentNumber, counter)
                .equals("Absent")) {
              Absent++;
            }
            counter++;
          }

          ObservableList<PieChart.Data> pieChartData =
              FXCollections.observableArrayList(
                  new PieChart.Data("Present", ontime),
                  new PieChart.Data("Late", Late),
                  new PieChart.Data("Absent", Absent)
              );
          pieChart.setData(pieChartData);
          pieChart.setTitle("Attendance");

          // Displays attendance elements
          Main.getPrimaryStage().show();
          ErrorGrades.setVisible(false);
        }
      }
    } catch (Exception e) {
      ErrorGrades.setVisible(true);
    }
    try {
      if (newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Student")) {
        System.out.println("attempting update");

        // If the combo box has "Grades" selected
        if (pieChartTypeSelector.getValue().equals("Grades")) {
          int A = 0;
          int B = 0;
          int C = 0;
          int D = 0;
          int F = 0;
          int counter = 0;
          while (counter < newLogin.currentUserUser
              .getNumberOfAssignments(this.newLogin.getUserNumber())) {
            double a = Double.parseDouble(newLogin.currentUserUser
                .getAssignmentPointsReceived(this.newLogin.getUserNumber(), counter));
            double b = Double.parseDouble(
                newLogin.currentUserUser.getAssignmentMaxPoints(this.newLogin.getUserNumber(), counter));
            System.out.println(a);
            System.out.println(b);
            double Calculation = (a / b);
            if (Calculation >= 0.90) {
              A++;
            }
            if (Calculation < 0.90 && Calculation >= 0.80) {
              B++;
            }
            if (Calculation < 0.80 && Calculation >= 0.70) {
              C++;
            }
            if (Calculation < 0.70 && Calculation >= 0.60) {
              D++;
            }
            if (Calculation < 0.60 && Calculation >= 0.50) {
              F++;
            }
            counter++;
          }

          // Loads grade elements
          ObservableList<PieChart.Data> pieChartData =
              FXCollections.observableArrayList(
                  new PieChart.Data("A", A),
                  new PieChart.Data("B", B),
                  new PieChart.Data("C", C),
                  new PieChart.Data("D", D),
                  new PieChart.Data("F", F)
              );
          pieChart.setData(pieChartData);
          pieChart.setTitle("Grades");

          // Displays grade elements
          Main.getPrimaryStage().show();
          ErrorGrades.setVisible(false);
        }

        if (pieChartTypeSelector.getValue().equals("Attendance")) {
          // Loads attendance elements
          //Late, ontime, Absent
          int Late = 0;
          int ontime = 0;
          int Absent = 0;
          int counter = 0;
          System.out.println(this.newLogin.getUserNumber());
          while (counter < newLogin.currentUserUser
              .getNumberOfAppointments(this.newLogin.getUserNumber())) {
            if (newLogin.currentUserUser.getAppointmentAttendance(this.newLogin.getUserNumber(), counter)
                .equals("Late")) {
              Late++;
            }
            if (newLogin.currentUserUser.getAppointmentAttendance(this.newLogin.getUserNumber(), counter)
                .equals("Present")) {
              ontime++;
            }
            if (newLogin.currentUserUser.getAppointmentAttendance(this.newLogin.getUserNumber(), counter)
                .equals("Absent")) {
              Absent++;
            }
            counter++;
          }

          ObservableList<PieChart.Data> pieChartData =
              FXCollections.observableArrayList(
                  new PieChart.Data("Present", ontime),
                  new PieChart.Data("Late", Late),
                  new PieChart.Data("Absent", Absent)
              );
          pieChart.setData(pieChartData);
          pieChart.setTitle("Attendance");

          // Displays attendance elements
          Main.getPrimaryStage().show();
          ErrorGrades.setVisible(false);
        }


      }
    } catch (Exception e) {
      ErrorGrades.setVisible(true);
    }
  }

}
