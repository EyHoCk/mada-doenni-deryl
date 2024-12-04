package ch.fhnw.doenni.FileHandler;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class FileReader {

    public static String readFile(String filePath) {
        StringBuilder fileString = new StringBuilder();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileString.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileString.toString();
    }


    public static byte[] readByteArray(String filePath) {
        File file = new File(filePath);
        byte[] bFile = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bFile;
    }

}

