package lab10.task2.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

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
        ResourceBundle messages = ResourceBundle.getBundle("location.messages", Locale.getDefault());
        System.out.println(messages.getString("shape.drawing") + " " + messages.getString("shape.circle"));
    }

    @Override
    public String toString() {
        ResourceBundle messages = ResourceBundle.getBundle("location.messages");
        String colorKey = "color." + shapeColor.toLowerCase();
        String localizedColor;

        if (messages.containsKey(colorKey)) {
            localizedColor = messages.getString(colorKey);
        } else {
            localizedColor = shapeColor;
        }
        return messages.getString("shape.circle") + ": " +
                messages.getString("shape.color") + " " + localizedColor + ", " +
                messages.getString("shape.area") + " " + calcArea() + ", " +
                messages.getString("shape.radius") + " " + radius;
    }
}
