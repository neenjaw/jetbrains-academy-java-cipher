package encryptdecrypt.cipher;

import encryptdecrypt.cipher.Cipher;
import encryptdecrypt.cipher.ShiftCipher;
import encryptdecrypt.cipher.UnicodeCipher;

public class CipherFactory {
    public static Cipher make(String type, int key) {
        switch (type.toLowerCase()) {
            case "unicode":
                return new UnicodeCipher(key);
            case "shift":
                return new ShiftCipher(key);
            default:
                return null;
        }
    }
}
