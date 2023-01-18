import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Encryptor encryptor = new Encryptor();
        String encryptedPassword = encryptor.encrypt(password);
        String decryptedPassword = encryptor.decrypt(encryptedPassword);

        System.out.println("Welcome to Login System");
        System.out.print("Please enter your password to login: ");
        String userPass = scanner.nextLine();

        if (userPass.equals(decryptedPassword)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Wrong password");
        }
    }
}

class Encryptor {
    private SecretKeySpec secretKey;

    public Encryptor() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            secretKey = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String password) {
        try {
            byte[] decoded = Base64.getDecoder().decode(password);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(decoded));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}