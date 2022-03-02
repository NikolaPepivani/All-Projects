package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PlaceInformation extends Alert {

    public PlaceInformation(String name, int x, int y) {
        super(AlertType.INFORMATION);
        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Name: " + name));
        grid.addRow(1, new Label("Coordinates: " + x + ", " + y));
        getDialogPane().setContent(grid);
        setHeaderText(null);
    }

    public PlaceInformation(String name, int x, int y, String describtion) {
        super(AlertType.INFORMATION);
        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Name: " + name));
        grid.addRow(1, new Label("Coordinates: " + x + ", " + y));
        grid.addRow(2, new Label("Describtion: " + describtion));
        getDialogPane().setContent(grid);
        setHeaderText(null);
    }

}

