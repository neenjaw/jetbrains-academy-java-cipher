package encryptdecrypt.cipher;

public class UnicodeCipher extends Cipher {
    public UnicodeCipher(int key) {
        super(key);
    }

    @Override
    public String encode(String message) {
        return process(message, key);
    }

    @Override
    public String decode(String message) {
        return process(message, -1 * key);
    }

    private String process(String message, int shift) {
        StringBuilder sb = new StringBuilder();

        for (char c:message.toCharArray()) {
            char encoded = (char) (c + shift);
            sb.append(encoded);
        }

        return sb.toString();
    }
}
