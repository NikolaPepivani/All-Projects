package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private Stage stage;
    private ImageView imageView = new ImageView();
    private BorderPane borderPane;
    private FlowPane buttons;
    private VBox radioButtons;
    private BorderPane root;
    private Pane center;
    private VBox right;
    private Image image;
    private RadioButton named;
    private RadioButton described;
    private TextField search;

    private ObjectHandler oh = new ObjectHandler();

    private ListView<Category> categories;
    private HashSet<Place> markedPlaces = new HashSet<Place>();
    private HashMap<String, HashSet<Place>> byName = new HashMap<String, HashSet<Place>>();
    private HashMap<Position, Place> positions = new HashMap<Position, Place>();
    private HashMap<Category, HashSet<Place>> byCategory = new HashMap<Category, HashSet<Place>>();

    private boolean saved = true;

    @Override public void start(Stage stage){
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new ExitHandler());
        this.stage = stage;
        borderPane = new BorderPane();
        buttons = new FlowPane();
        root = new BorderPane();
        radioButtons = new VBox();
        center = new Pane();
        right = new VBox();
        ObservableList<Category> chooseCategory = FXCollections.observableArrayList(Category.TRAIN, Category.BUS, Category.UNDERGROUND);
        categories = new ListView<Category>(chooseCategory);
        categories.setOnMousePressed(new ShowCategoryHandler());

        Button newButton = new Button("New");
        buttons.getChildren().add(newButton);
        newButton.setOnAction(new NewLocationHandler());
        named = new RadioButton("Named");
        described = new RadioButton("Described");
        radioButtons.getChildren().add(named);
        radioButtons.getChildren().add(described);
        buttons.getChildren().add(radioButtons);
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(named, described);
        named.setSelected(true);
        search = new TextField("Search");
        buttons.getChildren().add(search);
        Button searchButton = new Button("Search");
        buttons.getChildren().add(searchButton);
        searchButton.setOnAction(new SearchHandler());
        Button hideButton = new Button("Hide");
        buttons.getChildren().add(hideButton);
        hideButton.setOnAction(new HideHandler());
        Button remove = new Button("Remove");
        buttons.getChildren().add(remove);
        remove.setOnAction(new RemoveHandler());
        Button coordinateButton = new Button("Coordinates");
        buttons.getChildren().add(coordinateButton);
        coordinateButton.setOnAction(new CoordinateHandler());
        buttons.setPadding(new Insets(10));
        buttons.setHgap(10);
        buttons.setAlignment(Pos.CENTER);

        root.setCenter(buttons);

        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        Menu fileMenu = new Menu("File");
        menuBar.getMenus().add(fileMenu);
        MenuItem loadMap = new MenuItem("Load Picture");
        fileMenu.getItems().add(loadMap);
        loadMap.setOnAction(new LoadMapHandler());
        MenuItem loadPlaces = new MenuItem("Load Places");
        fileMenu.getItems().add(loadPlaces);
        loadPlaces.setOnAction(new LoadPlacesHandler());
        MenuItem save = new MenuItem("Save");
        fileMenu.getItems().add(save);
        save.setOnAction(new SaveHandler());
        MenuItem exit = new MenuItem("Exit");
        fileMenu.getItems().add(exit);
        exit.setOnAction(action->stage.fireEvent(new WindowEvent(stage,WindowEvent.WINDOW_CLOSE_REQUEST)));

        right.getChildren().add(new Label("Category"));
        right.getChildren().add(categories);
        Button hideCategoryButton = new Button("Hide Category");
        right.getChildren().add(hideCategoryButton);
        hideCategoryButton.setOnAction(new HideCategoryHandler());
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(10));
        categories.setMaxHeight(71);
        center.getChildren().add(imageView);

        borderPane.setTop(root);
        borderPane.setCenter(center);
        borderPane.setRight(right);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setTitle("ShowPicture");
        stage.show();

    }

    class NewLocationHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event) {
            if(image != null) {
                center.setOnMouseClicked(new NewClickHandler());
                center.setCursor(Cursor.CROSSHAIR);
            }
        }
    }

    class ObjectHandler implements EventHandler<MouseEvent> {
        public void handle(MouseEvent event) {
            if(center.getCursor() == Cursor.CROSSHAIR) {
                center.setDisable(true);
            }else {
                Place place = (Place) event.getSource();
                if(event.getButton() == MouseButton.SECONDARY) {
                    place.displayInformation();
                }
                else if(event.getButton() == MouseButton.PRIMARY) {
                    if(!place.getMark()) {
                        place.setMark();
                        markedPlaces.add(place);
                    }
                    else if(place.getMark()) {
                        place.removeMark();
                        markedPlaces.remove(place);
                    }
                }
            }
            center.setDisable(false);
        }
    }



    class NewClickHandler implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Position p = new Position(x, y);
            String name = null;
            Category c = categories.getSelectionModel().getSelectedItem();
            if(c == null) {
                c = Category.NONE;
            }
            Place place = null;
            if(named.isSelected()) {
                NamedPlaceDialog dialog = new NamedPlaceDialog();
                Optional<ButtonType> answer = dialog.showAndWait();
                if(answer.isPresent() && answer.get() == ButtonType.OK) {
                    name = dialog.getName();
                    if(name.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Namn får inte vara tomt");
                        alert.showAndWait();
                        return;
                    }
                    if(positions.containsKey(p)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Det finns redan en plats på denna position");
                        alert.showAndWait();
                        center.setCursor(Cursor.DEFAULT);
                    }else {
                        place = new NamedPlace(dialog.getName(), p, c, false);
                    }
                }
            }
            else if(described.isSelected()) {
                DescribedPlaceDialog dialog = new DescribedPlaceDialog();
                Optional<ButtonType> answer = dialog.showAndWait();
                if(answer.isPresent() && answer.get() == ButtonType.OK) {
                    name = dialog.getName();
                    if(name.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Namn får inte vara tomt");
                        alert.showAndWait();
                        return;
                    }
                    if(answer.isPresent() && answer.get() == ButtonType.OK) {
                        String describtion = dialog.getDescribtion();
                        if(describtion.isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Describtion får inte vara tomt");
                            alert.showAndWait();
                            return;
                        }
                        if(positions.containsKey(p)) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Det finns redan en plats på denna position");
                            alert.showAndWait();
                            center.setCursor(Cursor.DEFAULT);
                        }else {
                            place = new DescribedPlace(dialog.getName(), p, c, dialog.getDescribtion(), false);
                        }
                    }
                }
            }
            if(place != null) {
                addToAll(place);
            }
            center.setCursor(null);
            center.setOnMouseClicked(null);
            saved = false;
        }
    }

    class SearchHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event) {
            try {
                HashSet<Place> places = byName.get(search.getText());
                for(Place place : places) {
                    place.setVisible(true);
                    place.setMark();
                    markedPlaces.add(place);
                }
            }catch(NullPointerException e) {

            }
        }
    }


    class HideHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event) {
            for(Place place : markedPlaces) {
                if(place.isVisible()) {
                    place.setVisible(false);
                    place.removeMark();
                }
            }
            markedPlaces.clear();
        }
    }

    class RemoveHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event) {
            Iterator<Place> iterator = markedPlaces.iterator();
            while (iterator.hasNext()) {
                center.getChildren().remove(iterator.next());
            }
            for(Place place : markedPlaces) {
                byName.get(place.getName()).remove(place);
                positions.remove(place.getPositions());
                byCategory.get(place.getCategory()).remove(place);
            }
            markedPlaces.clear();
        }
    }

    class CoordinateHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event) {
            Position p = null;
            CoordinateDialog dialog = new CoordinateDialog();
            Optional<ButtonType> answer = dialog.showAndWait();
            if(answer.isPresent() && answer.get() == ButtonType.OK) {
                p = dialog.getCoordinates();
            }
            System.out.println(p);
            if(positions.containsKey(p)) {
                for(Place place : markedPlaces) {
                    place.removeMark();
                }
                markedPlaces.clear();
                positions.get(p).setMark();
                positions.get(p).displayInformation();
                markedPlaces.add(positions.get(p));
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Inga platser finns på dessa coordinater");
                alert.showAndWait();
            }
        }
    }

    class HideCategoryHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event) {
            Category c = categories.getSelectionModel().getSelectedItem();
            try {
                HashSet<Place> places = byCategory.get(c);
                for(Place place : places) {
                    if(place.getCategory() == c) {
                        place.setVisible(false);
                        place.removeMark();
                        markedPlaces.remove(place);
                    }
                }
            }catch(NullPointerException e){

            }
        }
    }

    class ShowCategoryHandler implements EventHandler<MouseEvent>{
        public void handle(MouseEvent event) {
            Category c = categories.getSelectionModel().getSelectedItem();
            try {
                HashSet<Place> places = byCategory.get(c);
                for(Place place : places) {
                    if(place.getCategory() == c) {
                        place.setVisible(true);
                    }
                }
            }catch(NullPointerException e){

            }
        }
    }

    class LoadMapHandler implements EventHandler<ActionEvent>{
        @Override public void handle(ActionEvent event){


            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Picture");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Images", "*.bmp", "*.jpg", "*.gif", "*.png"),
                    new FileChooser.ExtensionFilter("All files", "*.*")
            );
            File file = fileChooser.showOpenDialog(stage);
            if (file == null) {
                return;
            }
            if(!saved) {
                if(unsavedAlert()) {
                    markedPlaces.addAll(positions.values());
                    new RemoveHandler().handle(event);
                    saved = true;
                }else {
                    return;
                }
            }
            String fileName = file.getAbsolutePath();
            image = new Image("file:" + fileName);
            imageView.setImage(image);

            stage.sizeToScene();

        }
    }



    class LoadPlacesHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event) {
            try {
                if(center == null) {
                    System.out.println("not work");
                }

                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(null);
                if(file == null) {
                    return;
                }

                String fileName = file.getAbsolutePath();

                FileReader infile = new FileReader(fileName);
                BufferedReader in = new BufferedReader(infile);

                Place place = null;
                String line;

                markedPlaces.addAll(positions.values());
                new RemoveHandler().handle(event);

                while((line = in.readLine()) != null) {
                    String[] tokens = line.split(",");
                    Category cat = Category.valueOf(tokens[1].toUpperCase());
                    Position pos = new Position(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                    String name = tokens[4];
                    if(tokens[0].equals("Named")) {
                        place = new NamedPlace(name, pos, cat, false);
                    }else if(tokens[0].equals("Described")) {
                        String description = tokens[5];
                        place = new DescribedPlace(name, pos, cat, description, false);
                    }
                    addToAll(place);
                    saved = false;
                }
                in.close();
            }catch(FileNotFoundException e) {

            }catch(IOException e) {

            }
        }
    }
    class SaveHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event) {
            try {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showSaveDialog(null);
                String fileName = file.getAbsolutePath();

                FileWriter outfile = new FileWriter(fileName);
                PrintWriter out = new PrintWriter(outfile);

                for(Place l : positions.values()) {
                    out.println(l.toString());
                }
                out.close();
                outfile.close();

            }catch(FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Kan inte öppna filen" + e.getMessage());
                alert.showAndWait();
            }catch(IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }catch(NullPointerException e) {

            }
            saved = true;
        }
    }

    class ExitHandler implements EventHandler<WindowEvent>{
        public void handle(WindowEvent event) {
            if(!saved) {
                if(!unsavedAlert()) {
                    event.consume();
                }
            }
            else{
                return;
            }
        }
    }

    private void addToByNameHashMap(Place spot) {
        Place place = (Place) spot;
        HashSet<Place> places = new HashSet<Place>();
        try {
            HashSet<Place> existingName = byName.get(place.getName());
            existingName.add(place);
            byName.put(place.getName(), existingName);
        }catch(NullPointerException e) {
            places.add(place);
            byName.put(place.getName(), places);
        }
    }

    private void addToByCategoryHashMap(Place spot) {
        Place place = (Place) spot;
        HashSet<Place> places = new HashSet<Place>();
        try {
            HashSet<Place> existingCategory = byCategory.get(place.getCategory());
            existingCategory.add(place);
            byCategory.put(place.getCategory(), existingCategory);
        }catch(NullPointerException e) {
            places.add(place);
            byCategory.put(place.getCategory(), places);
        }
    }

    private boolean unsavedAlert() {
        Alert unsaved = new Alert(Alert.AlertType.CONFIRMATION, "Du har inte sparat ändringarna");
        Optional<ButtonType> response = unsaved.showAndWait();
        if(response.get() != ButtonType.OK) {
            return false;
        }else {
            return true;
        }
    }

    public void addToAll(Place place) {
        center.getChildren().add(place);
        addToByNameHashMap(place);
        addToByCategoryHashMap(place);
        positions.put(place.getPositions(), place);
        place.setOnMouseClicked(oh);
    }



    public static void main(String[] args) {
        launch(args);
    }
}