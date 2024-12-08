package ch.fhnw.doenni.Elgamal;

import ch.fhnw.doenni.FileHandler.FileReader;
import ch.fhnw.doenni.FileHandler.FileWriter;

import java.io.FileNotFoundException;
import java.math.BigInteger;


public class Elgamal {

    public static void main(String[] args) throws FileNotFoundException {

        String input = "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1" +
            "29024E088A67CC74020BBEA63B139B22514A08798E3404DD" +
            "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245" +
            "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED" +
            "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3D" +
            "C2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F" +
            "83655D23DCA3AD961C62F356208552BB9ED529077096966D" +
            "670C354E4ABC9804F1746C08CA18217C32905E462E36CE3B" +
            "E39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9" +
            "DE2BCBF6955817183995497CEA956AE515D2261898FA0510" +
            "15728E5A8AACAA68FFFFFFFFFFFFFFFF";

        BigInteger group = new BigInteger(input, 16);
        BigInteger g = BigInteger.valueOf(2);

        System.out.println(group + ", "+ g);

        KeyPair pair = new KeyPair(group, g);
        //FileWriter.writeFile("./pk.txt", pair.getPuK().getNumber().toString());
        //FileWriter.writeFile("./sk.txt", pair.getPrk().getNumber().toString());

        //Verschlüsseln einer Text.txt datei.
        Key puK = new Key(group, g, new BigInteger(FileReader.readFile("src/ch/fhnw/doenni/Elgamal/pk.txt")));
        String vText = Encryptor.encryptText(FileReader.readFile("src/ch/fhnw/doenni/Elgamal/text.txt"), puK);
        FileWriter.writeFile("src/ch/fhnw/doenni/Elgamal/chiffre.txt", vText);

        //Entschlüsselung der vText Datei
        Key prK = new Key(group, g, new BigInteger(FileReader.readFile("src/ch/fhnw/doenni/Elgamal/sk.txt")));
        String result = Decryptor.decryptText(FileReader.readFile("src/ch/fhnw/doenni/Elgamal/chiffre.txt"), prK);
        FileWriter.writeFile("src/ch/fhnw/doenni/Elgamal/text-d.txt", result);

        //Entschlüsselung der Vorgegebenen Datei
        Key prK2 = new Key(group, g, new BigInteger(FileReader.readFile("src/ch/fhnw/doenni/Elgamal/Aufgabe5/sk.txt")));
        String result2 = Decryptor.decryptText(FileReader.readFile("src/ch/fhnw/doenni/Elgamal/Aufgabe5/chiffre.txt"), prK2);
        FileWriter.writeFile("src/ch/fhnw/doenni/Elgamal/Aufgabe5/text-d.txt", result2);


    }



}
