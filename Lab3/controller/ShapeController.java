package controller;

import model.Shape;
import java.util.Arrays;
import java.util.Comparator;

public class ShapeController {

    public double calculateTotalArea(Shape[] shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.calcArea();
        }
        return total;
    }

    public double calculateAreaByType(Shape[] shapes, Class<?> type) {
        double total = 0;
        for (Shape shape : shapes) {
            if (type.isInstance(shape)) {
                total += shape.calcArea();
            }
        }
        return total;
    }

    public void sortByArea(Shape[] shapes) {
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
    }

    public void sortByColor(Shape[] shapes) {
        Arrays.sort(shapes, Comparator.comparing(shape -> shape.shapeColor));
    }
}
