package model;

public abstract class Shape implements Drawable {
    public String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return "Color: " + shapeColor + ", Area: " + calcArea();
    }
}
