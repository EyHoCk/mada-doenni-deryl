package ch.fhnw.doenni.Elgamal;

import java.math.BigInteger;
import java.util.Random;

public class Key {

    private BigInteger group;
    private BigInteger generator;
    private BigInteger number;

    public BigInteger getGroup() {
        return group;
    }

    public void setGroup(BigInteger group) {
        this.group = group;
    }

    public BigInteger getGenerator() {
        return generator;
    }

    public void setGenerator(BigInteger generator) {
        this.generator = generator;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public Key(BigInteger group, BigInteger generator, BigInteger number) {
        this.group = group;
        this.generator = generator;
        this.number = number;
    }

    public static BigInteger randomNumberFromGroup(BigInteger group) {
        Random rnd = new Random();
        return new BigInteger(group.subtract(new BigInteger("1")).bitLength(), rnd);
    }

}
