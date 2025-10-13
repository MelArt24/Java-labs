package Lab5.task2and3.model;
import java.io.Serial;
import java.io.Serializable;

public class Rectangle extends Shape implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }

    @Override
    public String toString() {
        return "Rectangle: " + super.toString() + ", Width: " + width + ", Height: " + height;
    }
}
