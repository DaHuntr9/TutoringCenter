package src;

/**
 * Programmer: Hunter Danielson, Carl Reyes
 * Description of file: This is the Dashboard controller that controls what the user sees when
 * logged into the system. There are multiple tabs that correlate to each tab having different
 * functionality.
 * Initialization checks to what the user is logged in as and changes the tabs that are
 * exclusive to that role.
 * This code is separated into sections (aka each tab) signified by the java doc sections.
 * There were to many version updates to include in this file or it would have been an
 * exponentially large javadoc comment.
 */

import com.jfoenix.controls.JFXBadge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;
import java.io.IOException;


public class dashboardController extends Main {

  @FXML
  public TabPane tabpane;

  @FXML
  private Tab dashboard;

  @FXML
  private Tab settings;

  @FXML
  private Tab calendar;

  @FXML
  private Tab quizzes;

  @FXML
  public Tab schedule;
  @FXML
  public Tab quizzesTutor;

  @FXML
  public Tab scheduleTutor;

  @FXML
  private Tab reviewTutors;

  @FXML
  private Tab profile;

  @FXML
  ComboBox roleDropDownOne;

  @FXML
  ComboBox roleDropDownTwo;

  @FXML
  JFXBadge class1 = new JFXBadge();

  @FXML
  JFXBadge class2 = new JFXBadge();

  @FXML
  JFXBadge class3 = new JFXBadge();

  //Sets hover over element to display grade for class 1
  Label class1Label = new Label("Grade: 100%");
  VBox vbox = new VBox(class1Label);
  PopOver popOver = new PopOver(vbox);

  //Sets hover over element to display grade for class 2
  Label class2Label = new Label("Grade: 100%");
  VBox vbox2 = new VBox(class2Label);
  PopOver popOver2 = new PopOver(vbox2);

  //Sets hover over element to display grade for class 3
  Label class3Label = new Label("Grade: 100%");
  VBox vbox3 = new VBox(class3Label);
  PopOver popOver3 = new PopOver(vbox3);


  /**
   * Display class 1 grades element on hover
   */
  @FXML
  void class1Hover(MouseEvent event) throws Exception {
    vbox.setPrefHeight(45);
    vbox.setPrefWidth(75);
    class1Label.setWrapText(true);
    vbox.setStyle("-fx-background-color: #ffccbb;" + "-fx-alignment: CENTER");
    popOver.show(class1);
  }

  /**
   * Hides grades upon leaving hover element
   */
  @FXML
  void class1Exit(MouseEvent event) throws Exception {
    popOver.hide();
  }

  /**
   * Display class 2 grades element on hover
   */
  @FXML
  void class2Hover(MouseEvent event) throws Exception {
    vbox2.setPrefHeight(45);
    vbox2.setPrefWidth(75);
    class2Label.setWrapText(true);
    vbox2.setStyle("-fx-background-color: #6eb5c0;" + "-fx-alignment: CENTER");
    popOver2.show(class2);
  }

  /**
   * Hides grades upon leaving hover element
   */
  @FXML
  void class2Exit(MouseEvent event) throws Exception {
    popOver2.hide();
  }

  /**
   * Display class 3 grades element on hover
   */
  @FXML
  void class3Hover(MouseEvent event) throws Exception {
    vbox3.setPrefHeight(45);
    vbox3.setPrefWidth(75);
    class3Label.setWrapText(true);
    vbox3.setStyle("-fx-background-color: #006c84;" + "-fx-alignment: CENTER");
    popOver3.show(class3);
  }

  /**
   * Hides grades upon leaving hover element
   */
  @FXML
  void class3Exit(MouseEvent event) throws Exception {
    popOver3.hide();
  }

  /******************************************
   * DASHBOARD METHODS
   ******************************************/

  /**
   * Goes to appointment scheduler
   */
  @FXML
  private void goToScheduleTutoringCenter(ActionEvent event) throws IOException {
    SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
    selectionModel.select(schedule);
    if (newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Tutor")) {
      selectionModel.select(scheduleTutor);
    }

  }

  /**
   * Goes to grades and attendance graph representation
   */
  @FXML
  private void goToGradesAttendance(ActionEvent event) throws IOException {
    Stage stage = Main.getPrimaryStage();

    Parent root = FXMLLoader.load(getClass().getResource("grades.fxml"));

    stage.setScene(new Scene(root, 600, 440));

    stage.show();
  }

  /**
   * Goes to appointment scheduler
   */
  @FXML
  private void goToReviewTutorsDashboard(ActionEvent event) throws IOException {
    SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
    selectionModel.select(reviewTutors);
  }

  /**
   * Goes to user profile page
   */
  @FXML
  private void goToProfile(ActionEvent event) throws IOException {
    SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
    selectionModel.select(profile);
  }

  /******************************************
   * ASSIGNMENT LISTING METHODS
   ******************************************/

  @FXML
  JFXTreeTableView<Schedule> table;

  @FXML
  JFXButton checkInButton;

  @FXML
  JFXComboBox appointmentComboBox;

  @FXML
  private void checkIn(ActionEvent event) throws IOException {
    boolean passed = false;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
    LocalDateTime now = LocalDateTime.now();
    //System.out.println(dtf.format(now));
    Comment.setText(dtf.format(now));
    //System.out.println(newLogin.currentUserUser.getAppointmentDate(newLogin.currentUserUser.UserNumber,(int) appointmentComboBox.getValue()));
    newLogin.currentUserUser
        .setAppointmentComment(newLogin.getUserNumber(), (int) appointmentComboBox.getValue(),
            dtf.format(now));
    updateAssignments(new ActionEvent());
    if (Integer.parseInt(
        newLogin.currentUserUser
            .getAppointmentDate(newLogin.getUserNumber(), (int) appointmentComboBox.getValue())
            .substring(8, 10))
        - Integer.parseInt(dtf.format(now).substring(3, 5)) > 0) {
      //System.out.println("Late Level 1");
      try {
        if (Integer.parseInt(
            newLogin.currentUserUser
                .getAppointmentDate(newLogin.getUserNumber(), (int) appointmentComboBox.getValue())
                .substring(3, 5))
            - Integer.parseInt(dtf.format(now).substring(0, 2)) <= 0) {
          //System.out.println("Late Level 2");
          System.out.println("Absent!");
          passed = true;
          newLogin.currentUserUser.setAppointmentAttendance(newLogin.getUserNumber(),
              (int) appointmentComboBox.getValue(), "Late");
          //System.out.println(Integer.parseInt(dtf.format(now).substring(3, 5)));
          //System.out.println(Integer.parseInt(dtf.format(now).substring(0, 2)));
        }
      } catch (Exception e) {

      }
    }
    if (passed == false) {
      if (Integer.parseInt(
          newLogin.currentUserUser
              .getAppointmentDate(newLogin.getUserNumber(), (int) appointmentComboBox.getValue())
              .substring(8, 10))
          - Integer.parseInt(dtf.format(now).substring(3, 5)) > 0) {
        //System.out.println("Present Level 1");
        if (Integer.parseInt(
            newLogin.currentUserUser
                .getAppointmentDate(newLogin.getUserNumber(), (int) appointmentComboBox.getValue())
                .substring(5, 7))
            - Integer.parseInt(dtf.format(now).substring(0, 2)) >= 0) {
          //System.out.println("present Level 2");
          System.out.println("Present!");
          newLogin.currentUserUser.setAppointmentAttendance(newLogin.getUserNumber(),
              (int) appointmentComboBox.getValue(), "Present");
        }
      }
    }
  }


  /**
   * Updates appointments scheduled by user
   */
  @FXML
  private void updateAssignments(ActionEvent event) throws IOException {

    // Adds "Subject" column
    JFXTreeTableColumn<Schedule, String> subject = new JFXTreeTableColumn("Subject");
    subject.setPrefWidth(100);

    subject.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().subject;
          }
        });

    // Adds "Tutor" column
    JFXTreeTableColumn<Schedule, String> tutor = new JFXTreeTableColumn("Tutor");
    tutor.setPrefWidth(100);

    tutor.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().tutor;
          }
        });

    // Adds "Comment" column
    JFXTreeTableColumn<Schedule, String> comment = new JFXTreeTableColumn("Comment");
    comment.setPrefWidth(100);

    comment.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().comment;
          }
        });

    // Adds "Date" column
    JFXTreeTableColumn<Schedule, String> date = new JFXTreeTableColumn("Date");
    date.setPrefWidth(100);

    date.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().date;
          }
        });

    // Adds "Time" column
    JFXTreeTableColumn<Schedule, String> time = new JFXTreeTableColumn("Time");
    time.setPrefWidth(100);

    time.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().time;
          }
        });

    // Adds "Location" column
    JFXTreeTableColumn<Schedule, String> location = new JFXTreeTableColumn("Location");
    location.setPrefWidth(100);

    location.setCellValueFactory(
        new Callback<CellDataFeatures<Schedule, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Schedule, String> param) {
            return param.getValue().getValue().location;
          }
        });

    // Adds default events to the schedule list
    ObservableList<Schedule> Schedule = FXCollections.observableArrayList();
    int counter = 0;
    while (counter < newLogin.currentUserUser.getNumberOfAppointments(newLogin.getUserNumber())) {
      Schedule.add(new Schedule(
          newLogin.currentUserUser.getAppointmentSubject(newLogin.getUserNumber(), counter),
          newLogin.currentUserUser.getAppointmentTutor(newLogin.getUserNumber(), counter),
          newLogin.currentUserUser.getAppointmentComments(newLogin.getUserNumber(), counter),
          newLogin.currentUserUser.getAppointmentDate(newLogin.getUserNumber(), counter),
          newLogin.currentUserUser.getAppointmentTime(newLogin.getUserNumber(), counter),
          newLogin.currentUserUser.getAppointmentLocation(newLogin.getUserNumber(), counter)));
      counter++;
    }

    // Temporary variables for dynamic appending to the ObservableList "Schedule" for archived appointments
    int temporarySize = temporary.size();

    for (int i = 0; i < temporarySize; i++) {
      Schedule.add(temporary.get(i));
    }

    final TreeItem<Schedule> root = new RecursiveTreeItem<Schedule>(Schedule,
        RecursiveTreeObject::getChildren);
    table.getColumns().setAll(subject, tutor, comment, date, time, location);
    table.setRoot(root);
    table.setShowRoot(false);
  }

  // Class containing appointment elements
  class Schedule extends RecursiveTreeObject<Schedule> {

    StringProperty subject;
    StringProperty tutor;
    StringProperty comment;
    StringProperty date;
    StringProperty time;
    StringProperty location;

    public Schedule(String subject, String tutor, String comment, String date, String time,
        String location) {
      this.subject = new SimpleStringProperty(subject);
      this.tutor = new SimpleStringProperty(tutor);
      this.comment = new SimpleStringProperty(comment);
      this.date = new SimpleStringProperty(date);
      this.time = new SimpleStringProperty(time);
      this.location = new SimpleStringProperty(location);
    }

    @FXML
    private void removeAssignment(ActionEvent event) throws IOException {
      /*
      Add Functionality
       */
    }


  }

  /******************************************
   * QUIZ METHODS
   ******************************************/

  /**
   * Goes to Class 1 Quiz
   */
  @FXML
  private void goToClass1Quiz(ActionEvent event) throws IOException {
    QuizNumberReference = 0;
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("quiz.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  /**
   * Goes to Class 2 Quiz
   */
  @FXML
  private void goToClass2Quiz(ActionEvent event) throws IOException {
    QuizNumberReference = 1;
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("quiz.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  /**
   * Goes to Class 3 Quiz
   */
  @FXML
  private void goToClass3Quiz(ActionEvent event) throws IOException {
    QuizNumberReference = 2;
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("quiz.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  /******************************************
   * TUTOR REVIEW METHODS
   ******************************************/

  @FXML
  JFXTextArea reviewContent;
  @FXML
  JFXButton reviewSubmitButton;
  @FXML
  JFXButton reviewRedirectButton;
  @FXML
  Label ErrorReview;

  @FXML
  private void submitTutorReview(ActionEvent event) throws IOException {
    try {
      if (!roleDropDownOne.getValue().toString().equals("") || !reviewContent.getText().equals("")
          || !roleDropDownTwo.getValue().toString().equals("")) {
        newLogin.currentUserUser.createReview(newLogin.getUserNumber(),
            roleDropDownOne.getValue().toString(), roleDropDownTwo.getValue().toString(),
            reviewContent.getText(),
            false,
            "0");
        ErrorReview.setText("Review Submitted!");
        ErrorReview.setVisible(true);
      } else {
        ErrorReview.setVisible(true);
      }
      //Need some text to tell the user that the review was submitted
    } catch (Exception e) {
      ErrorReview.setVisible(true);
    }

  }

  /**
   * Goes to tutor reviews
   */
  @FXML
  private void goToTutorReviews(ActionEvent event) throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("ratereview.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  /******************************************
   * SCHEDULE TUTORING METHODS
   ******************************************/


  @FXML
  JFXTextArea Comment;
  @FXML
  JFXComboBox TutorPicked;
  @FXML
  JFXComboBox SubjectPicked;
  @FXML
  JFXDatePicker DatePicked;
  @FXML
  JFXTimePicker TimePicked;
  @FXML
  JFXTextArea Location;
  @FXML
  Label Error;

  // Temporary ObservableList containing archived appointments to be appended in update in updateAssignments()
  ObservableList<Schedule> temporary = FXCollections.observableArrayList();

  /**
   * Takes information from appointment scheduler form and appends the appointment list
   */
  @FXML
  Label ErrorSchedule;

  @FXML
  private void scheduleTutor(ActionEvent event) throws IOException {
    try {
      String subject = SubjectPicked.getValue().toString();
      String tutor = TutorPicked.getValue().toString();
      String comment = Comment.getText();
      String date = DatePicked.getValue().toString();
      String time = TimePicked.getValue().toString();
      String location = Location.getText();
      //temporary.add(new Schedule(subject, tutor, comment, date, time, location));
      newLogin.currentUserUser
          .createAppointment(newLogin.getUserNumber(), subject, tutor, date, location, "", time,
              comment);
    } catch (Exception e) {
      ErrorSchedule.setVisible(true);
    }
    appointmentComboBox.getItems().clear();
    for (int i = 0;
        i <= newLogin.currentUserUser.getNumberOfAppointments(newLogin.getUserNumber()) - 1; i++) {
      try {
        appointmentComboBox.getItems().addAll(i);
      } catch (Exception e) {
        ErrorSchedule.setVisible(true);
      }
    }

  }

  /******************************************
   * SETTINGS
   ******************************************/

  //SETTINGS FXML ELEMENTS
  @FXML
  TextField emailTextField;
  @FXML
  TextField usernameTextField;
  @FXML
  PasswordField passwordTextFieldOne;
  @FXML
  PasswordField passwordTextFieldTwo;
  /**
   * Updates user email
   */
  @FXML
  Label ErrorEmail;

  @FXML
  private void updateEmail(ActionEvent event) throws IOException {
    System.out.println("attempting email update");
    try {
      String newEmail = emailTextField.getText();//grabs the new email value
      if (!newEmail.equals("")) {
        newLogin.currentUserUser.setUserEmail(newLogin.getUserNumber(), newEmail);
        System.out.println("email updated");
        ErrorEmail.setVisible(false);
      } else {
        ErrorEmail.setVisible(true);
      }
    } catch (Exception e) {
      System.out.println("email update failed");
      System.out.println(e);
      ErrorEmail.setVisible(true);
    }
  }

  /**
   * Updates user username
   */
  @FXML
  Label ErrorUserName;

  @FXML
  private void updateUsername(ActionEvent event) throws IOException {
    System.out.println("attempting username update");
    try {
      String newUsername = usernameTextField.getText();
      if (!newUsername.equals("")) {
        newLogin.currentUserUser.setUsername(newLogin.getUserNumber(), newUsername);
        System.out.println("username updated");
        ErrorUserName.setVisible(false);
      } else {
        ErrorUserName.setVisible(true);
      }
    } catch (Exception e) {
      System.out.println("username update failed");
      System.out.println(e);
      ErrorUserName.setVisible(true);
    }
  }

  @FXML
  Label ErrorPassword;

  /**
   * Updates user password
   */
  @FXML
  private void updatePassword(ActionEvent event) throws IOException {
    System.out.println("attempting password update");
    try {
      String newPassword1 = passwordTextFieldOne.getText();
      String newPassword2 = passwordTextFieldTwo.getText();
      if (newPassword1.equals(newPassword2) && !newPassword1.equals("") && newPassword1.length() > 5
          && newPassword1.length() < 33) {
        newLogin.currentUserUser.setPassword(newLogin.getUserNumber(), newPassword1);
        System.out.println("password updated");
        ErrorPassword.setVisible(false);
      } else {
        ErrorPassword.setVisible(true);
      }

    } catch (Exception e) {
      System.out.println("password update failed");
      System.out.println(e);
      ErrorPassword.setVisible(true);
    }
  }

  /******************************************
   * PROFILE METHODS
   ******************************************/
  @FXML
  JFXComboBox profileComboBox;
  @FXML
  JFXTextArea aboutMeTextArea;
  @FXML
  JFXTextArea majorTextArea;

  @FXML
  JFXBadge profilebadge;


  /**
   * Updates user's profile icon
   */
  @FXML
  private void updateProfileIcon(ActionEvent event) throws IOException {

    StringBuilder sb = new StringBuilder();
    sb.append("#");
    for (int i = 0; i < 6; i++) {
      double random = Math.random() * 15;
      int truncate = (int) random;
      switch ((int) random) {
        case 10:
          sb.append("A");
          break;
        case 11:
          sb.append("B");
          break;
        case 12:
          sb.append("C");
          break;
        case 13:
          sb.append("D");
          break;
        case 14:
          sb.append("E");
          break;
        case 15:
          sb.append("F");
          break;
        default:
          sb.append(Integer.toString(truncate));
          break;
      }
      System.out.println(sb);
    }
    System.out.println(sb);
    profilebadge.setStyle("-fx-background-color:" + sb);
    newLogin.currentUserUser.setProfileIcon(newLogin.getUserNumber(), sb.toString());
  }

  /**
   * Updates all of the user set profile elements
   */
  @FXML
  private void updateProfile(ActionEvent event) throws IOException {
    newLogin.currentUserUser.setAboutMeText(newLogin.getUserNumber(), aboutMeTextArea.getText());
    newLogin.currentUserUser.setMajor(newLogin.getUserNumber(), aboutMeTextArea.getText());
    newLogin.currentUserUser
        .setRole(newLogin.getUserNumber(), profileComboBox.getValue().toString());
  }

  /**
   * Logs user out of the Tutoring Center application
   */
  @FXML
  private void logout(ActionEvent event) throws IOException {
        /*login system needs to search for the last JSON OBJECT before implementation
        System.out.println("attempting email update");
        //creates new date object
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        newLogin.currentUserUser.setLogout(newLogin.getUserNumber(), date);
        */

        /*
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        newLogin.currentUserUser.setLogout(0, formatter.format(date));
        */

    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("signin.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  /**
   * Executes default code when dashboard is called
   */

  @FXML
  JFXTextArea QuizQuestion, QuizQuestion1, QuizQuestion2, QuizQuestion3, QuizQuestion4;

  @FXML
  JFXButton goToLastQuestionTutor, nextQuestionButton;

  @FXML
  JFXButton createQuizButton;

  @FXML
  Hyperlink submitHyperlink;

  @FXML
  JFXComboBox selectedstudent;

  @FXML
  JFXTextArea submitQuizCreationInstructions;

  @FXML
  JFXButton goToDashboard, completeQuizCreation;

  @FXML
  JFXButton class1quiz;

  @FXML
  JFXButton class2quiz;

  @FXML
  JFXButton class3quiz;

  @FXML
  JFXButton scheduleTutoring;

  @FXML
  JFXComboBox AssignmentNumberDropDown;
  @FXML
  Label QuizErrorA;

  @FXML
  public void initialize() {
    QuizErrorA.setVisible(true);
    class1quiz.setVisible(false);
    class2quiz.setVisible(false);
    class3quiz.setVisible(false);
    if (newLogin.currentUserUser.getNumberOfQuizzes(newLogin.getUserNumber()) == 1) {
      QuizErrorA.setVisible(false);
      class1quiz.setVisible(true);
      class2quiz.setVisible(false);
      class3quiz.setVisible(false);
    } else if (newLogin.currentUserUser.getNumberOfQuizzes(newLogin.getUserNumber()) == 2) {
      QuizErrorA.setVisible(false);
      class1quiz.setVisible(true);
      class2quiz.setVisible(true);
      class3quiz.setVisible(false);
    } else if (newLogin.currentUserUser.getNumberOfQuizzes(newLogin.getUserNumber()) > 3) {
      QuizErrorA.setVisible(false);
      class1quiz.setVisible(true);
      class2quiz.setVisible(true);
      class3quiz.setVisible(true);
    }

    if (newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Tutor")) {
      tabpane.getTabs().remove(schedule);
      tabpane.getTabs().remove(quizzes);
      scheduleTutor.setText("Assignments");
      quizzesTutor.setText("Create Quiz");
      scheduleTutoring.setText("Create Assignments");
    } else if (newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Student")) {
      tabpane.getTabs().remove(scheduleTutor);
      tabpane.getTabs().remove(quizzesTutor);
    }

    createQuizButton.setVisible(true);

    //goToDashboard.setVisible(true);
    //completeQuizCreation.setVisible(true);

    //while(newLogin.currentUserUser.getTotalNumberOfAccounts()){}
    for (int i = 0; i <= newLogin.currentUserUser.getTotalNumberOfAccounts() - 1; i++) {
      String role = newLogin.currentUserUser.getRole(i);
      String tutorName = newLogin.currentUserUser.getFirstName(i);
      if (role.equals("Tutor")) {
        //need to add tutors to an array of strings
        //System.out.println("adding");
        TutorPicked.getItems().addAll(tutorName);
        roleDropDownOne.getItems().addAll(tutorName);
      }
    }
    SubjectPicked.getItems().setAll("Biology", "Chemistry", "Math", "OOP");
    roleDropDownTwo.getItems().setAll("Biology", "Chemistry", "Math", "OOP");

    for (int i = 0;
        i <= newLogin.currentUserUser.getNumberOfAppointments(newLogin.getUserNumber()) - 1; i++) {
      try {
        appointmentComboBox.getItems().addAll(i);
      } catch (Exception e) {
        System.out.println(".");
      }
    }
    //int StudentNumber = 0;
    for (int i = 0; i <= newLogin.currentUserUser.getTotalNumberOfAccounts() - 1; i++) {
      String role = newLogin.currentUserUser.getRole(i);
      String StudentName = newLogin.currentUserUser.getFirstName(i);
      if (role.equals("Student")) {
        AssignmentNumberDropDown.getItems().addAll(StudentName);
      }
    }

    for (int i = 0; i <= newLogin.currentUserUser.getTotalNumberOfAccounts() - 1; i++) {
      String role = newLogin.currentUserUser.getRole(i);
      String tutorName = newLogin.currentUserUser.getFirstName(i);
      if (role.equals("Student")) {
        selectedstudent.getItems().addAll(tutorName);
      }
    }

    emailTextField.setText(newLogin.currentUserUser.getUserEmail(newLogin.getUserNumber()));
    usernameTextField.setText(newLogin.currentUserUser.getUsername(newLogin.getUserNumber()));
    //passwordTextFieldOne.setText(newLogin.currentUserUser.getPassword(newLogin.getUserNumber()));
    aboutMeTextArea.setText(newLogin.currentUserUser.getaboutMeText(newLogin.getUserNumber()));
    majorTextArea.setText(newLogin.currentUserUser.getMajor(newLogin.getUserNumber()));
    profilebadge.setStyle("-fx-background-color:" + newLogin.currentUserUser
        .getProfileIcon(newLogin.getUserNumber()));
    //this code will set the profile tab's information to be displayed automatically
    profileComboBox.getItems().setAll("Student", "Tutor");
    if (newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Student")) {
      profileComboBox.getSelectionModel().selectFirst();
    }
    if (newLogin.currentUserUser.getRole(newLogin.getUserNumber()).equals("Tutor")) {
      profileComboBox.getSelectionModel().selectLast();
    }
    try {
      ArrayList<Double> average = new ArrayList<Double>();
      int counter = 0;
      while (counter < newLogin.currentUserUser.getNumberOfAssignments(newLogin.getUserNumber())) {
        double a = Double.parseDouble(newLogin.currentUserUser
            .getAssignmentPointsReceived(newLogin.getUserNumber(), counter));
        double b = Double.parseDouble(
            newLogin.currentUserUser.getAssignmentMaxPoints(newLogin.getUserNumber(), counter));
        System.out.println(a);
        System.out.println(b);
        double Calculation = (a / b);
        average.add(Calculation);
        counter++;
      }
      int counterTwo = 0;
      double Summation = 0;
      System.out.println(average.size());
      while (counterTwo < average.size()) {
        Summation = average.get(counterTwo) + Summation;
        counterTwo++;
      }
      DecimalFormat df = new DecimalFormat("##000.00");
      Double SummationDivision = Double.valueOf(df.format((Summation / average.size()) * 100));
      class1Label.setText(SummationDivision.toString());
      if (SummationDivision.equals(0.0)) {
        class1Label.setText("No Grades Yet");
      }
    } catch (Exception e) {
      class1Label.setText("No Grades Yet");

    }
    //Attendance Calculation
    try {
      double ontime = 0;
      int counterattendence = 0;
      System.out.println("numberofAppointments"+newLogin.currentUserUser
          .getNumberOfAppointments(newLogin.getUserNumber()));
      while (counterattendence < newLogin.currentUserUser
          .getNumberOfAppointments(newLogin.getUserNumber())) {
        System.out.println(newLogin.currentUserUser.getAppointmentAttendance(newLogin.getUserNumber(), counterattendence));
        System.out.println(newLogin.currentUserUser.getAppointmentAttendance(newLogin.getUserNumber(), counterattendence).equals("Present"));
        if (newLogin.currentUserUser.getAppointmentAttendance(newLogin.getUserNumber(), counterattendence).equals("Present")) {
          System.out.println("Present");
          ontime++;
        }
        counterattendence++;
      }
      DecimalFormat df = new DecimalFormat("##000.00");
      Double SummationDivisionTwo = Double.valueOf(df.format((ontime / counterattendence) * 100));
      class2Label.setText(SummationDivisionTwo.toString());
      if (SummationDivisionTwo.equals(0.0)) {
        class2Label.setText("No Attendance yet!");
      }
    } catch (Exception e) {
      class2Label.setText("No Attendance yet!");
    }
    class3.setVisible(false);
  }

  @FXML
  Hyperlink updateProfileIcon;

  /******************************************
   * TUTOR ASSIGNMENT METHODS
   ******************************************/

  @FXML
  JFXTreeTableView<Assignment> tableTutor;


  /**
   * Updates assignments scheduled by user
   */
  @FXML
  private void updateAssignmentsTutor(ActionEvent event) throws IOException {

    JFXTreeTableColumn<Assignment, String> assignmentName = new JFXTreeTableColumn("Assign. Name");
    assignmentName.setPrefWidth(100);

    assignmentName.setCellValueFactory(
        new Callback<CellDataFeatures<Assignment, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Assignment, String> param) {
            return param.getValue().getValue().assignmentName;
          }
        });

    JFXTreeTableColumn<Assignment, String> datePicked = new JFXTreeTableColumn("Due Date");
    datePicked.setPrefWidth(100);

    datePicked.setCellValueFactory(
        new Callback<CellDataFeatures<Assignment, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Assignment, String> param) {
            return param.getValue().getValue().datePicked;
          }
        });

    JFXTreeTableColumn<Assignment, String> timePicked = new JFXTreeTableColumn("Time Due");
    timePicked.setPrefWidth(100);

    timePicked.setCellValueFactory(
        new Callback<CellDataFeatures<Assignment, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Assignment, String> param) {
            return param.getValue().getValue().timePicked;
          }
        });

    JFXTreeTableColumn<Assignment, String> Comments = new JFXTreeTableColumn("Comments");
    Comments.setPrefWidth(100);

    Comments.setCellValueFactory(
        new Callback<CellDataFeatures<Assignment, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Assignment, String> param) {
            return param.getValue().getValue().Comments;
          }
        });

    JFXTreeTableColumn<Assignment, String> pointsReceived = new JFXTreeTableColumn(
        "Points Received");
    pointsReceived.setPrefWidth(100);

    pointsReceived.setCellValueFactory(
        new Callback<CellDataFeatures<Assignment, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Assignment, String> param) {
            return param.getValue().getValue().pointsReceived;
          }
        });

    JFXTreeTableColumn<Assignment, String> maxPoints = new JFXTreeTableColumn("Max Received");
    maxPoints.setPrefWidth(100);

    maxPoints.setCellValueFactory(
        new Callback<CellDataFeatures<Assignment, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Assignment, String> param) {
            return param.getValue().getValue().maxPoints;
          }
        });

    JFXTreeTableColumn<Assignment, String> assignmentType = new JFXTreeTableColumn("Assign. Type");
    assignmentType.setPrefWidth(100);

    assignmentType.setCellValueFactory(
        new Callback<CellDataFeatures<Assignment, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Assignment, String> param) {
            return param.getValue().getValue().assignmentType;
          }
        });

    JFXTreeTableColumn<Assignment, String> selectedStudent = new JFXTreeTableColumn("Student");
    selectedStudent.setPrefWidth(100);

    selectedStudent.setCellValueFactory(
        new Callback<CellDataFeatures<Assignment, String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(
              CellDataFeatures<Assignment, String> param) {
            return param.getValue().getValue().selectedStudent;
          }
        });

    ObservableList<Assignment> assignment = FXCollections.observableArrayList();
    int firstcounter = 0;
    int secondcounter = 0;
    while (firstcounter < newLogin.currentUserUser.getTotalNumberOfAccounts()) {
      while (secondcounter < newLogin.currentUserUser.getNumberOfAssignments(firstcounter)) {
        assignment.add(new Assignment(
            newLogin.currentUserUser.getAssignmentName(firstcounter, secondcounter)
            , newLogin.currentUserUser.getAssignmentMaxPoints(firstcounter, secondcounter)
            , newLogin.currentUserUser.getAssignmentPointsReceived(firstcounter, secondcounter)
            , newLogin.currentUserUser.getAssignmentComments(firstcounter, secondcounter)
            , newLogin.currentUserUser.getAssignmentDatePicked(firstcounter, secondcounter)
            , newLogin.currentUserUser.getAssignmentTimePicked(firstcounter, secondcounter)
            , newLogin.currentUserUser.getAssignmentType(firstcounter, secondcounter)
            , newLogin.currentUserUser.getAssignmentSelectedStudent(firstcounter, secondcounter)));
        secondcounter++;
      }
      firstcounter++;
    }
    // Temporary variables for dynamic appending to the ObservableList "Schedule" for archived appointments
    int temporarySize = temporary2.size();

    for (int i = 0; i < temporarySize; i++) {
      assignment.add(temporary2.get(i));
    }

    final TreeItem<Assignment> root = new RecursiveTreeItem<Assignment>(assignment,
        RecursiveTreeObject::getChildren);
    tableTutor.getColumns()
        .setAll(assignmentName, maxPoints, pointsReceived, Comments, datePicked, timePicked,
            assignmentType, selectedStudent);
    tableTutor.setRoot(root);
    tableTutor.setShowRoot(false);
  }

  // Temporary ObservableList containing archived appointments to be appended in update in updateAssignments()
  ObservableList<Assignment> temporary2 = FXCollections.observableArrayList();

  /**
   *
   */
  @FXML
  private void removeAssignment(ActionEvent event) throws IOException {

  }

  /**
   *
   */
  @FXML
  private void createAssignment(ActionEvent event) throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("createAssignment.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  /******************************************
   * TUTOR CREATE QUIZ METHODS
   ******************************************/
  String[][] temporaryString = new String[10][5];
  int temporaryStringIndex = 0;

  public void populateTemporaryStringArray(int index) {
    ArrayList<JFXTextArea> jfxta = new ArrayList<JFXTextArea>() {
    };
    jfxta.add(QuizQuestion1);
    jfxta.add(QuizQuestion2);
    jfxta.add(QuizQuestion3);
    jfxta.add(QuizQuestion4);

    for (int i = 0; i < 5; i++) {
      if (i == 0) {
        temporaryString[index][0] = QuizQuestion.getText();
      } else {
        temporaryString[index][i] = jfxta.get(i - 1).getText();
        System.out.println(temporaryString[index][i]);
      }
    }
  }

  @FXML
  Label quizError;

  @FXML
  private void goToNextQuestion(ActionEvent event) throws IOException {
    //write all fields String array
    boolean donePopulating = false;

    try {
      if (!QuizQuestion.getText().equals("") && !QuizQuestion1.getText().equals("")
          && !QuizQuestion2.getText().equals("") && !QuizQuestion3.getText().equals("")
          && !QuizQuestion4.getText().equals("")) {
        populateTemporaryStringArray(temporaryStringIndex);
        donePopulating = true;
      } else {
        quizError.setText("Please fill out all fields.");
      }
    } catch (Exception e) {
      quizError.setText("Please fill out all fields.");
    }

    if (donePopulating == true) {
      quizError.setText("");
      if (temporaryStringIndex == 8) {
        temporaryStringIndex++;
        nextQuestionButton.setDisable(true);
      } else {
        temporaryStringIndex++;
      }
      //clears all fields
      if (!(temporaryString[temporaryStringIndex - 1][0].equals(""))) {
        QuizQuestion.setPromptText("Enter Question " + (temporaryStringIndex + 1));
        QuizQuestion.setText(temporaryString[temporaryStringIndex][0]);
        QuizQuestion1.setText(temporaryString[temporaryStringIndex][1]);
        QuizQuestion2.setText(temporaryString[temporaryStringIndex][2]);
        QuizQuestion3.setText(temporaryString[temporaryStringIndex][3]);
        QuizQuestion4.setText(temporaryString[temporaryStringIndex][4]);
      } else {
        QuizQuestion.setText("");
        QuizQuestion1.setText("");
        QuizQuestion2.setText("");
        QuizQuestion3.setText("");
        QuizQuestion4.setText("");
      }
    }
  }

  @FXML
  private void goToLastQuestion(ActionEvent event) throws IOException {
    nextQuestionButton.setDisable(false);
    if (temporaryStringIndex > 0) {
      boolean donePopulatingLastQuestion = false;
      try {
        if (!QuizQuestion.getText().equals("") && !QuizQuestion1.getText().equals("")
            && !QuizQuestion2.getText().equals("") && !QuizQuestion3.getText().equals("")
            && !QuizQuestion4.getText().equals("")) {
          populateTemporaryStringArray(temporaryStringIndex);
          donePopulatingLastQuestion = true;
        } else {
          quizError.setText("Please fill out all fields.");
        }
      } catch (Exception e) {
        quizError.setText("Please fill out all fields.");
      }
      if (donePopulatingLastQuestion == true) {
        quizError.setText("");
        temporaryStringIndex--;
        QuizQuestion.setPromptText("Enter Question " + (temporaryStringIndex + 1));
        QuizQuestion.setText(temporaryString[temporaryStringIndex][0]);
        QuizQuestion1.setText(temporaryString[temporaryStringIndex][1]);
        QuizQuestion2.setText(temporaryString[temporaryStringIndex][2]);
        QuizQuestion3.setText(temporaryString[temporaryStringIndex][3]);
        QuizQuestion4.setText(temporaryString[temporaryStringIndex][4]);
      }
    }
  }

  @FXML
  private void completeQuizCreation(ActionEvent event) throws IOException {

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

    //store destined user in JSON
    newLogin.currentUserUser.createQuiz(StudentNumber);
    for (int i = 0; i < temporaryString.length - 1; i++) {
      String correctAnswer = "null";
      int correctAnswerIndex = 0;
      //System.out.println(temporaryString[i][1]);
      //System.out.println(temporaryString[i][1].indexOf("*"));
      try {
        if (temporaryString[i][1].indexOf("*") != -1) {
          correctAnswer = "A";
          correctAnswerIndex = 1;
        } else if (temporaryString[i][2].indexOf("*") != -1) {
          correctAnswer = "B";
          correctAnswerIndex = 2;
        } else if (temporaryString[i][3].indexOf("*") != -1) {
          correctAnswer = "C";
          correctAnswerIndex = 3;
        } else if (temporaryString[i][4].indexOf("*") != -1) {
          correctAnswer = "D";
          correctAnswerIndex = 4;
        }
      } catch (Exception e) {
        break;
      }
      temporaryString[i][correctAnswerIndex] = temporaryString[i][correctAnswerIndex]
          .substring(0, temporaryString[i][correctAnswerIndex].indexOf("*"));
      int numofquiz = newLogin.currentUserUser.getNumberOfQuizzes(StudentNumber);
      newLogin.currentUserUser.addQuizQuestion(StudentNumber,
          numofquiz - 1,
          temporaryString[i][0],
          temporaryString[i][1],
          temporaryString[i][2],
          temporaryString[i][3],
          temporaryString[i][4],
          correctAnswer,
          "");
    }
    //go to dashboard
    goToDashboard(new ActionEvent());
  }

  @FXML
  private void goToQuizSubmission(ActionEvent event) throws IOException {

    boolean donePopulating = false;

    try {
      if (!QuizQuestion.getText().equals("") && !QuizQuestion1.getText().equals("")
          && !QuizQuestion2.getText().equals("") && !QuizQuestion3.getText().equals("")
          && !QuizQuestion4.getText().equals("")) {
        populateTemporaryStringArray(temporaryStringIndex);
        donePopulating = true;
      } else {
        quizError.setText("Please fill out all fields.");
      }
    } catch (Exception e) {
      quizError.setText("Please fill out all fields.");
    }

    if (donePopulating == true) {
      QuizQuestion.setVisible(false);
      QuizQuestion1.setVisible(false);
      QuizQuestion2.setVisible(false);
      QuizQuestion3.setVisible(false);
      QuizQuestion4.setVisible(false);
      goToLastQuestionTutor.setVisible(false);
      submitHyperlink.setVisible(false);
      nextQuestionButton.setVisible(false);
      goToDashboard.setVisible(true);
      completeQuizCreation.setVisible(true);
      selectedstudent.setVisible(true);
      submitQuizCreationInstructions.setVisible(true);
    }
  }

  @FXML
  private void goToQuizCreation(ActionEvent event) throws IOException {
    QuizQuestion.setVisible(true);
    QuizQuestion1.setVisible(true);
    QuizQuestion2.setVisible(true);
    QuizQuestion3.setVisible(true);
    QuizQuestion4.setVisible(true);
    goToLastQuestionTutor.setVisible(true);
    nextQuestionButton.setVisible(true);
    createQuizButton.setVisible(false);
    submitHyperlink.setVisible(true);
  }

  @FXML
  private void goToDashboard(ActionEvent event) throws IOException {
    SingleSelectionModel<Tab> selectionModel = tabpane.getSelectionModel();
    selectionModel.select(dashboard);
  }


}

// Class containing appointment elements
class Assignment extends RecursiveTreeObject<Assignment> {

  StringProperty assignmentName;
  StringProperty maxPoints;
  StringProperty pointsReceived;
  StringProperty Comments;
  StringProperty datePicked;
  StringProperty timePicked;
  StringProperty assignmentType;
  StringProperty selectedStudent;

  public Assignment(String assignmentName, String maxPoints, String pointsReceived, String Comments,
      String datePicked,
      String timePicked, String assignmentType, String selectedStudent) {
    this.assignmentName = new SimpleStringProperty(assignmentName);
    this.maxPoints = new SimpleStringProperty(maxPoints);
    this.pointsReceived = new SimpleStringProperty(pointsReceived);
    this.Comments = new SimpleStringProperty(Comments);
    this.datePicked = new SimpleStringProperty(datePicked);
    this.timePicked = new SimpleStringProperty(timePicked);
    this.assignmentType = new SimpleStringProperty(assignmentType);
    this.selectedStudent = new SimpleStringProperty(selectedStudent);
  }

}