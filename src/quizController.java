package src;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class quizController {

    @FXML
    private void settingsChosen(Event event) throws IOException {
        // System.out.println("in button1pressed");

        Stage stage = Main.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));

        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    @FXML
    private void dashboardChosen(Event event) throws IOException {
        // System.out.println("in button1pressed");

        Stage stage = Main.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }
/*
    @FXML
    private void quizChosen(Event event) throws IOException {
        // System.out.println("in button1pressed");

        Stage stage = Main.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("quiz.fxml"));

        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }
*/
    // TEMPORARY TEMPORARY TEMPRARY TEMPORARY
    @FXML
    private void gradesChosen(Event event) throws IOException {
        // System.out.println("in button1pressed");

        Stage stage = Main.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("writereview.fxml"));

        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }
    @FXML
    private void scheduleChosen(Event event) throws IOException {
        // System.out.println("in button1pressed");

        Stage stage = Main.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("schedule.fxml"));

        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    @FXML
    private void calendarChosen(Event event) throws IOException {
        // System.out.println("in button1pressed");

        Stage stage = Main.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("calendar.fxml"));

        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

}
