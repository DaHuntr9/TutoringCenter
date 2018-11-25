package src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class profileController extends Main{

    @FXML
    TextArea aboutMeText;
    @FXML
    ComboBox majorDropDown;
    @FXML
    ComboBox roleDropDown;

    public void initialize() {
        //NEEDS DATA IN JSON TO DETERMINE THE MAJOR,ALSO MIGHT NEED SYSTEM FOR DYNAMICLY SWITCHING MAJORS.
        majorDropDown.getSelectionModel().selectFirst();

        assert roleDropDown
                != null : "fx:id=\"roledropdown\" was not injected: check your FXML file 'signup.fxml'.";
        // populate the fruit combo box with item choices.
        roleDropDown.getItems().setAll("Student", "Tutor");
        if(newLogin.currentUserStudent.getRole(newLogin.getUserNumber()).equals("Student")){
            roleDropDown.getSelectionModel().selectFirst();
        }
        if(newLogin.currentUserStudent.getRole(newLogin.getUserNumber()).equals("Tutor")) {
            roleDropDown.getSelectionModel().selectLast();
        }
    }
    @FXML
    private void updatebutton(ActionEvent event) throws IOException {
        //this button will write the data to the JSON once a edit function for account info is made.
        newLogin.currentUserStudent.setFirstName(newLogin.getUserNumber(),"jeff");
    }
}


