package ch.fhnw.doenni.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileWriter {

    public static void writeFile(String filePath, String content) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeByteArray(String filePath, byte[] byteArray) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
