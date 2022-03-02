package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NamedPlaceDialog extends Alert {

    private TextField nameField = new TextField();

    public NamedPlaceDialog() {
        super(AlertType.CONFIRMATION);
        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Name:"), nameField);
        getDialogPane().setContent(grid);
        setHeaderText(null);

    }

    public String getName() {
        return nameField.getText();
    }

}


