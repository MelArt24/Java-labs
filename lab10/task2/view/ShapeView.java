package lab10.task2.view;

import lab10.task2.model.Shape;

public class ShapeView {
    public void displayShapes(Shape[] shapes) {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
