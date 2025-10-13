package Lab5.task2and3.model;
import java.io.Serial;
import java.io.Serializable;

public abstract class Shape implements Drawable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
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
