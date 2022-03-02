package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

public class NamedPlace extends Place {

    private String name;


    public NamedPlace(String name, Position p, Category c, boolean isMarked) {
        super(p, c, isMarked);
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void displayInformation() {
        PlaceInformation info = new PlaceInformation(getName(), (int) getX(), (int) getY());
        info.showAndWait();
    }

    public String toString() {
        return "Named" + "," + getCategory() + "," + (int) getPositions().getX() + "," + (int) getPositions().getY() + "," + getName();
    }

}
