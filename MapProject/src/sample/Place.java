package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public abstract class Place extends Polygon {

    private Category category;
    private Position position;
    private boolean isMarked;

    public Place(Position position, Category category, boolean isMarked) {
        super(position.getX(),position.getY(),position.getX()-15,position.getY()-15,position.getX()+15,position.getY()-15);
        this.position = position;
        this.category = category;
        this.isMarked = isMarked;
        setFill(category.getColor());
    }

    public abstract String getName();

    public Color getColor() {
        return category.getColor();
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public Position getPositions() {
        return position;
    }

    public Category getCategory() {
        return category;
    }

    public void setMark() {
        isMarked = true;
        setStroke(Color.YELLOW);
    }

    public void removeMark() {
        isMarked = false;
        setFill(getColor());
        setStroke(Color.TRANSPARENT);
    }

    public boolean getMark() {
        return isMarked;
    }

    public abstract void displayInformation();

    public abstract String toString();

}

