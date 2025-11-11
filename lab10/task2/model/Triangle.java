package lab10.task2.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

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
        ResourceBundle messages = ResourceBundle.getBundle("location.messages", Locale.getDefault());
        System.out.println(messages.getString("shape.drawing") + " " + messages.getString("shape.triangle"));
    }

    @Override
    public String toString() {
        ResourceBundle messages = ResourceBundle.getBundle("location.messages", Locale.getDefault());
        String colorKey = "color." + shapeColor.toLowerCase();
        String localizedColor;

        if (messages.containsKey(colorKey)) {
            localizedColor = messages.getString(colorKey);
        } else {
            localizedColor = shapeColor;
        }
        return messages.getString("shape.triangle") + ": " +
                messages.getString("shape.color") + " " + localizedColor + ", " +
                messages.getString("shape.area") + " " + calcArea() + ", " +
                messages.getString("shape.base") + " " + base + ", " +
                messages.getString("shape.height") + " " + height;
    }
}