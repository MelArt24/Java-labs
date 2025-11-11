package lab10.task2;

import lab10.task2.model.Shape;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.*;

public class ShapeFileHandler {
    private static final Logger logger = Logger.getLogger(ShapeFileHandler.class.getName());

    private static ResourceBundle getMessages() {
        return ResourceBundle.getBundle("location.messages", Locale.getDefault(), new Main.UTF8Control());
    }

    private static final byte KEY = 'K';

    public static void saveShapes(String filename, Shape[] shapes) {
        ResourceBundle messages = getMessages();
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new EncryptOutputStream(new FileOutputStream(filename), KEY))) {

            oos.writeObject(shapes);
            logger.info(messages.getString("log.saveToFile") + " " + filename);

        } catch (IOException e) {
            logger.log(Level.SEVERE, messages.getString("log.saveError") + " " + filename, e);
        }
    }


    public static Shape[] loadShapes(String filename) {
        ResourceBundle messages = getMessages();
        try (ObjectInputStream ois = new ObjectInputStream(
                new DecryptInputStream(new FileInputStream(filename), KEY))) {

            Shape[] shapes = (Shape[]) ois.readObject();

            if (shapes == null || shapes.length == 0) {
                logger.warning(messages.getString("log.fileEmpty") + " " + filename);
            } else {
                logger.info(messages.getString("log.loadedSuccessfully") + " " + filename);
            }

            return shapes;

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, messages.getString("log.loadError") + " " + filename, e);
            return new Shape[0];
        }
    }
}