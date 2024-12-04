package ch.fhnw.doenni.Elgamal;

import java.math.BigInteger;

public class KeyPair {
    private Key prk;
    private Key puK;

    public Key getPrk() {
        return prk;
    }
    public void setPrk(Key prk) {
        this.prk = prk;
    }
    public Key getPuK() {
        return puK;
    }
    public void setPuK(Key puK) {
        this.puK = puK;
    }

        public KeyPair(BigInteger group, BigInteger generator) {
        BigInteger number = Key.randomNumberFromGroup(group);
        this.prk = new Key(group, generator, number);
        this.puK = new Key(group, generator, generator.modPow(number, group));
    }

    public KeyPair(Key prk, Key puK) {
        this.prk = prk;
        this.puK = puK;
    }

}
