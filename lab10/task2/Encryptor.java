package lab10.task2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// now is not used
public class Encryptor {

    public static void encryptBytes(String inputFile, String outputFile, byte key) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b + key);
            }
        }
    }

    public static void decryptBytes(String inputFile, String outputFile, byte key) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b - key);
            }
        }
    }
}