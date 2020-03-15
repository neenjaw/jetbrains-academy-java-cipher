package encryptdecrypt.cipher;

public class ShiftCipher extends Cipher {
    public ShiftCipher(int key) {
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
            char encoded = c;
            int cType = Character.getType(c);

            if (cType == Character.LOWERCASE_LETTER || cType == Character.UPPERCASE_LETTER) {
                encoded += shift;
                if (Character.getType(encoded) != cType) {
                    encoded = cycleBackToAlphabet(encoded, shift);
                }
            }

            sb.append(encoded);
        }

        return sb.toString();
    }

    private char cycleBackToAlphabet(char c, int shift) {
        if (shift < 0) {
            return (char) (c + 26);
        } else if (shift > 0) {
            return (char) (c - 26);
        } else {
            return c;
        }
    }
}
