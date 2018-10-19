package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;


public class PageTemplate {

  // To-be inherited fields
  GridPane pageTempGrid = new GridPane(); // sets grid for all subclasses

  // Constructor
  PageTemplate() {
    pageTempGrid.setAlignment(Pos.CENTER);
    pageTempGrid.setHgap(10); // Spacing between rows and columns
    pageTempGrid.setVgap(10);
    pageTempGrid.setPadding(new Insets(25, 25, 25, 25));
  }
}
