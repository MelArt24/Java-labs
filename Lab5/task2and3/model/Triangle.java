package Lab5.task2and3.model;

import java.io.Serial;
import java.io.Serializable;

public class Triangle extends Shape implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private double base;
    private double height;

    public Triangle(String color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Triangle");
    }

    @Override
    public String toString() {
        return "Triangle: " + super.toString() + ", Base: " + base + ", Height: " + height;
    }
}
