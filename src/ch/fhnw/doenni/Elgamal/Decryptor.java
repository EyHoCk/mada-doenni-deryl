package ch.fhnw.doenni.Elgamal;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Decryptor {

    public static String decryptText(String text, Key privateKey) {
        String[] encryptedChars = text.split(";");
        return Arrays.stream(encryptedChars).map(string -> String.valueOf(decryptChar(string, privateKey))).collect(
            Collectors.joining());

    }

    private static char decryptChar(String encrypted, Key privateKey) {
        String[] y1y2 = encrypted.substring(1, encrypted.length()-1).split(",");
        BigInteger y1 = new BigInteger(y1y2[0]);
        BigInteger y2 = new BigInteger(y1y2[1]);
        BigInteger result = y2.multiply(y1.modPow(privateKey.getNumber(), privateKey.getGroup()).modInverse(privateKey.getGroup())).mod(privateKey.getGroup());
        return (char) Integer.parseInt(result.toString());
    }

}
