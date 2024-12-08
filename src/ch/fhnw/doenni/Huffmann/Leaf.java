package ch.fhnw.doenni.Huffmann;

public class Leaf {
    private int ascii;
    private int frequency;
    private Leaf left;
    private Leaf right;

    // Konstruktor für ein Blatt mit einem ASCII-Zeichen und seiner Häufigkeit
    public Leaf(int ascii, int frequency) {
        this.ascii = ascii;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public Leaf(Leaf left, Leaf right) {
        this.left = left;
        this.right = right;
        this.frequency = left.frequency + right.frequency;
        this.ascii = -1; 
    }

    public int getAscii() {
        return ascii;
    }

    public int getFrequency() {
        return frequency;
    }

    public Leaf getLeft() {
        return left;
    }

    public Leaf getRight() {
        return right;
    }

    public boolean hasChildren() {
        return left == null && right == null;
    }
}