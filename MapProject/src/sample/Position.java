package sample;

// PROG2 VT2020, Inl�mningsuppgift 1, del 1
// Nikola Pepivani nipe9977

public class Position {

    private double xcoordinate;
    private double ycoordinate;

    public Position(double x, double y) {
        this.xcoordinate = x;
        this.ycoordinate = y;
    }

    public double getX() {
        return xcoordinate;
    }

    public double getY() {
        return ycoordinate;
    }

    public int hashCode() {
        return (int) (getX() + 10 * getY() * 10);
    }

    public boolean equals(Object o) {
        if(!(o instanceof Position)) {
            return false;
        }else {
            Position p = (Position) o;
            return xcoordinate == p.getX() && ycoordinate == p.getY();
        }
    }

    public String toString() {
        return String.valueOf((int) getX()+", "+ (int) getY());
    }

}



