package encryptdecrypt;

import encryptdecrypt.cipher.Cipher;
import encryptdecrypt.cipher.CipherFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        String mode = "enc";
        String data = "";
        boolean dataSupplied = false;
        int key = 0;
        String algorithm = "shift";
        String infile = "";
        String outfile = "";
        boolean outfileSupplied = false;
        String message;
        String result;

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i+1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i+1]);
                    break;
                case "-data":
                    data = args[i+1];
                    dataSupplied = true;
                    break;
                case "-out":
                    outfile = args[i+1];
                    outfileSupplied = true;
                    break;
                case "-in":
                    infile = args[i+1];
                    break;
                case "-alg":
                    algorithm = args[i+1];
                    break;
                default:
                    System.out.println("Error: Unknown option '" + args[i] + "'");
                    return;
            }
        }

        if (dataSupplied) {
            message = data;
        } else {
            try {
                message = getDataFromFile(infile);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }

        Cipher cipher = CipherFactory.make(algorithm, key);

        switch (mode) {
            case "enc":
                result = cipher.encode(message);
                break;
            case "dec":
                result = cipher.decode(message);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }

        if (outfileSupplied) {
            if (!writeResultToFile(outfile, result)) {
                System.out.println("Error: writing result to file");
                return;
            }
        } else {
            System.out.println(result);
        }
    }

    private static boolean writeResultToFile(String outfile, String result) {
        File file = new File(outfile);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(result);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static String getDataFromFile(String infile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(infile)));
    }
}
