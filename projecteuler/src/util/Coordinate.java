package util;

import java.util.Objects;

public class Coordinate {
    double x;
    double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(Coordinate c) {
        return Math.sqrt((c.x-x)*(c.x-x) + (c.y-y)*(c.y-y));
    }

    public Coordinate add(Coordinate c) {
        return new Coordinate(getX() + c.getX(), getY() + c.getY());
    }

    public Coordinate multiply(double f) {
        return new Coordinate(getX() * f, getY() * f);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}