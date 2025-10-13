package Lab3.model;

public class Rectangle extends Shape {
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
