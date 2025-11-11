package lab10.task2.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

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
        ResourceBundle messages = ResourceBundle.getBundle("location.messages", Locale.getDefault());
        System.out.println(messages.getString("shape.drawing") + " " + messages.getString("shape.rectangle"));
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

        return messages.getString("shape.rectangle") + ": " +
                messages.getString("shape.color") + " " + localizedColor + ", " +
                messages.getString("shape.area") + " " + calcArea() + ", " +
                messages.getString("shape.width") + " " + width + ", " +
                messages.getString("shape.height") + " " + height;
    }
}
