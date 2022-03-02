package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DescribedPlaceDialog extends Alert {

    private TextField nameField = new TextField();
    private TextField describtionField = new TextField();

    public DescribedPlaceDialog() {
        super(AlertType.CONFIRMATION);
        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Name:"), nameField);
        grid.addRow(1, new Label("Describtion:"), describtionField);
        getDialogPane().setContent(grid);
        setHeaderText(null);

    }

    public String getName() {
        return nameField.getText();
    }

    public String getDescribtion() {
        return describtionField.getText();
    }

}



