package Lab5.task2and3;

import Lab5.task2and3.model.Shape;
import java.io.*;

public class ShapeFileHandler {

    private static final byte KEY = 'K';

    public static void saveShapes(String filename, Shape[] shapes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new EncryptOutputStream(new FileOutputStream(filename), KEY))) {

            oos.writeObject(shapes);
            System.out.println("Shapes saved successfully (encrypted)!");

        } catch (IOException e) {
            System.out.println("Error saving shapes: " + e.getMessage());
        }
    }


    public static Shape[] loadShapes(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new DecryptInputStream(new FileInputStream(filename), KEY))) {

            Shape[] shapes = (Shape[]) ois.readObject();

            if (shapes == null || shapes.length == 0) {
                System.out.println("File is empty or contains no shapes.");
            } else {
                System.out.println("Loaded " + shapes.length + " shapes.");
            }

            return shapes;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading shapes: " + e.getMessage());
            return new Shape[0];
        }
    }

}