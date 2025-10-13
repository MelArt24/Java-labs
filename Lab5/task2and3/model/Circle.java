package Lab5.task2and3.model;
import java.io.Serial;
import java.io.Serializable;

public class Circle extends Shape implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }

    @Override
    public String toString() {
        return "Circle: " + super.toString() + ", Radius: " + radius;
    }
}
