package encryptdecrypt.cipher;

public abstract class Cipher {

    protected int key;

    public Cipher(int key) {
        this.key = key;
    }

    abstract public String encode(String message);
    abstract public String decode(String message);
}
