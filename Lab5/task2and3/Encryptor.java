package Lab5.task2and3;

import java.io.*;

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