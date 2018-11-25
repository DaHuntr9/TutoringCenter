package src;


import com.jfoenix.controls.JFXBadge;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import javax.swing.*;
import java.io.IOException;


public class dashboardController extends Main{

  @FXML
  private Label user1;
  @FXML
  private Label user2;
  @FXML
  private Label user3;
  @FXML
  private Label user4;
  @FXML
  private Label user5;
  @FXML
  private Label user6;

  @FXML
  private TabPane tabpane;

  @FXML
  private Tab dashboard;

  @FXML
  private Tab settings;

  @FXML
  private Tab calendar;

  @FXML
  private Tab quizzes;

  @FXML
  private Tab schedule;

  @FXML
  private Tab grades;

  @FXML
  JFXBadge class1 = new JFXBadge();

  @FXML
  JFXBadge class2 = new JFXBadge();

  @FXML
  JFXBadge class3 = new JFXBadge();

  Label class1Label = new Label("Grade: 100%");
  VBox vbox = new VBox(class1Label);
  PopOver popOver = new PopOver(vbox);

  Label class2Label = new Label("Grade: 100%");
  VBox vbox2 = new VBox(class2Label);
  PopOver popOver2 = new PopOver(vbox2);

  Label class3Label = new Label("Grade: 100%");
  VBox vbox3 = new VBox(class3Label);
  PopOver popOver3 = new PopOver(vbox3);

  @FXML
  public void initialize() {
    //Sets the labels to Be The Current Users First Name
    user1.setText(newLogin.currentUserStudent.getFirstName(newLogin.UserNumber));
    user2.setText(newLogin.currentUserStudent.getFirstName(newLogin.UserNumber));
    user3.setText(newLogin.currentUserStudent.getFirstName(newLogin.UserNumber));
    user4.setText(newLogin.currentUserStudent.getFirstName(newLogin.UserNumber));
    user5.setText(newLogin.currentUserStudent.getFirstName(newLogin.UserNumber));
    user6.setText(newLogin.currentUserStudent.getFirstName(newLogin.UserNumber));
  }

  @FXML
  void class1Hover(MouseEvent event) throws Exception {
    vbox.setPrefHeight(45);
    vbox.setPrefWidth(75);
    class1Label.setWrapText(true);
    vbox.setStyle("-fx-background-color: #ffccbb;" + "-fx-alignment: CENTER");
    popOver.show(class1);
  }

  @FXML
  void class1Exit(MouseEvent event) throws Exception {
    popOver.hide();
  }

  @FXML
  void class2Hover(MouseEvent event) throws Exception {
    vbox2.setPrefHeight(45);
    vbox2.setPrefWidth(75);
    class2Label.setWrapText(true);
    vbox2.setStyle("-fx-background-color: #6eb5c0;" + "-fx-alignment: CENTER");
    popOver2.show(class2);
  }

  @FXML
  void class2Exit(MouseEvent event) throws Exception {
    popOver2.hide();
  }

  @FXML
  void class3Hover(MouseEvent event) throws Exception {
    vbox3.setPrefHeight(45);
    vbox3.setPrefWidth(75);
    class3Label.setWrapText(true);
    vbox3.setStyle("-fx-background-color: #006c84;" + "-fx-alignment: CENTER");
    popOver3.show(class3);
  }

  @FXML
  void class3Exit(MouseEvent event) throws Exception {
    popOver3.hide();
  }

  /******************************************
   * DASHBOARD METHODS
   ******************************************/

  @FXML
  private void goToScheduleTutoringCenter(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }

  @FXML
  private void goToGradesAttendance(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }

  @FXML
  private void goToTutorReviews(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }

  @FXML
  private void goToProfile(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }

  /******************************************
   * QUIZ METHODS
   ******************************************/

  @FXML
  private void goToQuizNext(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }

  @FXML
  private void goToQuizBack(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }

  /******************************************
   * TUTOR REVIEW METHODS
   ******************************************/

  @FXML
  private void submitTutorReview(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }
  /**
   * SCHEDULE TUTORING METHODS
   **/

  @FXML
  private void scheduleTutor(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }

  /**
   * SETTINGS METHODS
   **/

  @FXML
  private void updateSettings(ActionEvent event) throws IOException {
    /*
     * ENTER FUNCTIONALITY
     * */
  }
}