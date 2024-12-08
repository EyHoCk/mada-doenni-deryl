package ch.fhnw.doenni.Elgamal;

import java.math.BigInteger;
import java.util.stream.Collectors;

public class Encryptor {

    public static String encryptText(String text, Key publicKey) {
        return text.chars().mapToObj(character -> encryptChar(character, publicKey)).collect(Collectors.joining(";"));
    }

    private static String encryptChar(int character, Key publicKey) {
        BigInteger a = Key.randomNumberFromGroup(publicKey.getGroup());
        BigInteger y1 = publicKey.getGenerator().modPow(a, publicKey.getGroup());
        BigInteger y2 = publicKey.getNumber().modPow(a, publicKey.getGroup())
                .multiply(BigInteger.valueOf(character))
                .mod(publicKey.getGroup());
        return "(" + y1 + "," + y2 + ")";
    }

}
