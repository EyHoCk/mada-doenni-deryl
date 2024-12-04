package ch.fhnw.doenni.Huffmann.FileHandler;


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




//    public Map<Character, Integer> SimbolCount() throws IOException {
//
//        Map<Character, Integer> tabel = new HashMap<Character, Integer>();
//
//        Path dateiPfad = Paths.get("src/main/java/org/Huffmann/Text.txt");
//
//        InputStream in = new FileInputStream(String.valueOf(dateiPfad));
//        InputStreamReader reader = new InputStreamReader(in);
//        BufferedReader buffered = new BufferedReader(reader);
//        String line = buffered.readLine();
//
//        for (char c: line.toCharArray() ) {
//
//            if (!tabel.containsKey(c)) {
//                tabel.put(c, 1);
//            } else {
//                tabel.put(c, tabel.get(c) + 1);
//            }
//        }
//
//        System.out.println(tabel);
//
//        return tabel;
//    }
//
//
//
//}
