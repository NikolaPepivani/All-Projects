package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

public class DescribedPlace extends Place {

    private String name;
    private String describtion;

    public DescribedPlace(String name, Position p, Category c, String describtion, boolean isMarked) {
        super(p, c, isMarked);
        this.name = name;
        this.describtion = describtion;
    }

    public String getName() {
        return name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void displayInformation() {
        PlaceInformation info = new PlaceInformation(getName(), (int) getX(), (int) getY(), getDescribtion());
        info.showAndWait();
    }

    public String toString() {
        return "Described" + "," + getCategory() + "," + (int) getPositions().getX() + "," + (int) getPositions().getY() + "," + getName() + "," + getDescribtion();
    }

}

