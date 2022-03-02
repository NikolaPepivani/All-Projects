package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

public class CoordinateDialog extends Alert {

    private TextField xfield = new TextField();
    private TextField yfield = new TextField();

    public CoordinateDialog() {
        super(AlertType.CONFIRMATION);
        GridPane grid = new GridPane();
        grid.addRow(0, new Label("X-Coordinate:"), xfield);
        grid.addRow(1, new Label("Y-Coordinate:"), yfield);
        getDialogPane().setContent(grid);
        setHeaderText(null);
    }

    public int getXCoordinate() {
        return Integer.parseInt(xfield.getText());
    }

    public int getYCoordinate() {
        return Integer.parseInt(yfield.getText());
    }

    public Position getCoordinates() {
        return new Position(Integer.parseInt(xfield.getText()), Integer.parseInt(yfield.getText()));
    }

}
