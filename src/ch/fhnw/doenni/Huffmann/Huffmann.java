package ch.fhnw.doenni.Huffmann;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Huffmann {
    public static void main(String[] args) {
        String filename = "src/ch/fhnw/doenni/Huffmann/test.txt";
        int[] asciiCount = new int[128]; 
        ArrayList<Leaf> leaves = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int c;
            while ((c = reader.read()) != -1) {
                if (c < 128) { 
                    asciiCount[c]++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < asciiCount.length; i++) {
            if (asciiCount[i] > 0) {
                leaves.add(new Leaf(i, asciiCount[i]));
            }
        }

        // Huffman-Baum erstellen
        while (leaves.size() > 1) {
            leaves.sort((l1, l2) -> l1.getFrequency() - l2.getFrequency());
            Leaf left = leaves.remove(0);
            Leaf right = leaves.remove(0);
            leaves.add(new Leaf(left, right));
        }

        Leaf root = leaves.get(0);

        // Huffman-Codes generieren
        Map<Integer, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        // Debug-Ausgabe der Huffman-Codes
        for (Map.Entry<Integer, String> entry : huffmanCodes.entrySet()) {
            System.out.println((char) entry.getKey().intValue() + ": " + entry.getValue());
        }

        // Huffman-Codierungstabelle speichern
        try (FileWriter writer = new FileWriter("tabelle.txt")) {
            for (Map.Entry<Integer, String> entry : huffmanCodes.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "-");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Textdatei einlesen und in einen Bitstring kodieren
        StringBuilder bitString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int c;
            while ((c = reader.read()) != -1) {
                if (c < 128) {
                    bitString.append(huffmanCodes.get(c));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Bitstring erweitern
        bitString.append('1');
        while (bitString.length() % 8 != 0) {
            bitString.append('0');
        }

        // Byte-Array erstellen
        int byteArrayLength = bitString.length() / 8;
        byte[] byteArray = new byte[byteArrayLength];
        for (int i = 0; i < byteArrayLength; i++) {
            String byteString = bitString.substring(8 * i, 8 * i + 8);
            byteArray[i] = (byte) Integer.parseInt(byteString, 2);
        }

        // Byte-Array speichern
        try (FileOutputStream fos = new FileOutputStream("output.dat")) {
            fos.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Bitstring wurde erstellt, erweitert und als Byte-Array gespeichert.");

        // Dekodierung
        decode();
    }

    private static void generateCodes(Leaf node, String code, Map<Integer, String> huffmanCodes) {
        if (node.hasChildren()) {
            huffmanCodes.put(node.getAscii(), code);
        } else {
            generateCodes(node.getLeft(), code + "0", huffmanCodes);
            generateCodes(node.getRight(), code + "1", huffmanCodes);
        }
    }

    private static void decode() {
        // Kodierungstabelle einlesen
        Map<String, Integer> huffmanCodes = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tabelle.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] entries = line.split("-");
                for (String entry : entries) {
                    String[] parts = entry.split(":");
                    if (parts.length == 2) {
                        huffmanCodes.put(parts[1], Integer.parseInt(parts[0]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Byte-Array einlesen
        File file = new File("output.dat");
        byte[] bFile = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Byte-Array in Bitstring umwandeln
        StringBuilder bitString = new StringBuilder();
        for (byte b : bFile) {
            bitString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }

        // Letzte 1 und alle folgenden 0 abschneiden
        int lastIndex = bitString.lastIndexOf("1");
        bitString.setLength(lastIndex);

        // Bitstring dekodieren
        StringBuilder decodedText = new StringBuilder();
        String temp = "";
        for (int i = 0; i < bitString.length(); i++) {
            temp += bitString.charAt(i);
            if (huffmanCodes.containsKey(temp)) {
                decodedText.append((char) huffmanCodes.get(temp).intValue());
                temp = "";
            }
        }

        // Dekodierten Text speichern
        try (FileWriter writer = new FileWriter("decompress.txt")) {
            writer.write(decodedText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Dekodierter Text wurde erstellt und gespeichert.");
    }
}