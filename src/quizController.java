package src;

/**
 * Programmer: Hunter Danielson Description of file: This code This code reads the Associated quiz
 * that was created by the Tutor. We decided not to include the editing functionality as student and
 * tutors shouldn't be able to edit quiz information.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class quizController extends Main {

  @FXML
  JFXRadioButton Answer1;
  @FXML
  JFXRadioButton Answer2;
  @FXML
  JFXRadioButton Answer3;
  @FXML
  JFXRadioButton Answer4;
  @FXML
  JFXTextArea QuizQuestion;
  @FXML
  ToggleGroup right;
  @FXML
  JFXButton nextQuestionButton;

  // Default values for the quizzes

  int currentQuestionIndex = 0;

  /**
   * Uses current quiz number index in order to get the quiz question and answers
   */
  @FXML
  public void initialize() {
    QuizQuestion.setText(newLogin.currentUserUser
        .getQuizQuestion(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    Answer1.setText(newLogin.currentUserUser
        .getAnswerOne(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    Answer2.setText(newLogin.currentUserUser
        .getAnswerTwo(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    Answer3.setText(newLogin.currentUserUser
        .getAnswerThree(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    Answer4.setText(newLogin.currentUserUser
        .getAnswerFour(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
  }

  /**
   * Loads and displays new questions
   */
  @FXML
  Label ErrorQuiz;

  @FXML
  private void goToNextQuestion(ActionEvent event) throws IOException {
    if (currentQuestionIndex == (
        newLogin.currentUserUser.getNumberOfQuestions(newLogin.getUserNumber(), QuizNumberReference)
            - 1)) {
      // Takes user to the dashboard when submission is pressed
      Stage stage = Main.getPrimaryStage();
      stage
          .setScene(new Scene(FXMLLoader.load(getClass().getResource("dashboard.fxml")), 600, 440));
      stage.show();
    } else {
      nextQuestionButton.setText("Next");
      currentQuestionIndex++;
    }
    if (Answer1.isSelected()) {
      newLogin.currentUserUser
          .setUserAnswer(newLogin.getUserNumber(), "A", QuizNumberReference, currentQuestionIndex);
      goToNextQuestionPartTwo();
    } else if (Answer2.isSelected()) {
      newLogin.currentUserUser
          .setUserAnswer(newLogin.getUserNumber(), "B", QuizNumberReference, currentQuestionIndex);
      goToNextQuestionPartTwo();
    } else if (Answer3.isSelected()) {
      newLogin.currentUserUser
          .setUserAnswer(newLogin.getUserNumber(), "C", QuizNumberReference, currentQuestionIndex);
      goToNextQuestionPartTwo();
    } else if (Answer4.isSelected()) {
      newLogin.currentUserUser
          .setUserAnswer(newLogin.getUserNumber(), "D", QuizNumberReference, currentQuestionIndex);
      goToNextQuestionPartTwo();
    } else {
      ErrorQuiz.setVisible(true);
    }
  }

  private void goToNextQuestionPartTwo() {
    // Sets unselects previous selection upon entering new question
    Answer1.setSelected(false);
    Answer2.setSelected(false);
    Answer3.setSelected(false);
    Answer4.setSelected(false);

    QuizQuestion.setText(newLogin.currentUserUser
        .getQuizQuestion(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    Answer1.setText(newLogin.currentUserUser
        .getAnswerOne(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    Answer2.setText(newLogin.currentUserUser
        .getAnswerTwo(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    Answer3.setText(newLogin.currentUserUser
        .getAnswerThree(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    Answer4.setText(newLogin.currentUserUser
        .getAnswerFour(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
    System.out.println(currentQuestionIndex);

    // If the last question is being viewed, the "Next" button becomes "Submit"
    if (currentQuestionIndex == (newLogin.currentUserUser
        .getNumberOfQuestions(newLogin.getUserNumber(), QuizNumberReference)) - 1) {
      nextQuestionButton.setText("Submit");
    }
  }

  /**
   * Goes to the last question in the question array
   */
  @FXML
  private void goToLastQuestion(ActionEvent event) throws IOException {
    // If the user is viewing any question that is not the first question
    if (currentQuestionIndex > 0) {
      currentQuestionIndex--;
      QuizQuestion.setText(newLogin.currentUserUser
          .getQuizQuestion(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
      Answer1.setText(newLogin.currentUserUser
          .getAnswerOne(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
      Answer2.setText(newLogin.currentUserUser
          .getAnswerTwo(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
      Answer3.setText(newLogin.currentUserUser
          .getAnswerThree(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
      Answer4.setText(newLogin.currentUserUser
          .getAnswerFour(newLogin.getUserNumber(), QuizNumberReference, currentQuestionIndex));
      // If the last question is not being viewed, the "Next" button becomes "Submit"
      nextQuestionButton.setText("Next");
    }
  }

  /**
   * Lets the user return to the dashboard at any point during the quiz
   */
  @FXML
  private void returnToDashboard() throws Exception {
    Stage stage = Main.getPrimaryStage();
    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("dashboard.fxml")), 600, 440));
    stage.show();
  }

}
