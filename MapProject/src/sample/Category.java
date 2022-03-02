package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

import javafx.scene.paint.Color;
public enum Category {

    BUS(Color.RED),TRAIN(Color.GREEN),UNDERGROUND(Color.BLUE),NONE(Color.BLACK);

    private final Color color;

    Category(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String toString() {
        return name().toUpperCase().substring(0,1) + name().toLowerCase().substring(1);
    }

}


