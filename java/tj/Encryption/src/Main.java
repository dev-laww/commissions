import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Scanner;

/**
 * Use encryption algorithm with DESede Cipher and Base64/MD5 Base64 encryption
 * for Java.
 */
public class Main {

    private static final String ENCRYPTION_KEY = "YNYNWKLIJLKJFJALJLAJFJFJY";
    private static final String DIGEST_KEY = "UHUWKJLSJDKLFJSKJDFJKSLJY";

    public static void main(final String[] args) {
//        final Main e = new Main();
//        String o[] = e.encrypt("mypasswords123");
//        System.out.println("Encrypted Data : " + o[0]);
//        System.out.println(o[1]);
//        // Now decrypt the string
//        final Encrypter decrypter = new Encrypter("DESede", ENCRYPTION_KEY);
//        final String decryptedData = decrypter.decrypt(o[0]);
//        System.out.println("Decrypted Data: " + decryptedData);
//        System.out.println("Done");
        final Scanner scanner = new Scanner(System.in);
        final String username = "username";
        final String password = "password";
        final Main e = new Main();
        String[] encryptedUsername = e.encrypt(username);
        String[] encryptedPassword = e.encrypt(password);

        final Encrypter decrypter = new Encrypter("DESede", ENCRYPTION_KEY);
        final String[] decryptedUsername = decrypter.decrypt(encryptedUsername[0]);
        System.out.println();
        final String[] decryptedPassword = decrypter.decrypt(encryptedPassword[0]);

        System.out.println("Please Login...");
        System.out.print("Enter username: ");
        String usernameInp = scanner.nextLine();
        System.out.print("Enter password: ");
        String passwordInp = scanner.nextLine();

        String decryptedUsernameText = decryptedUsername[0].split("&endt=")[0];
        String decryptedPasswordText = decryptedPassword[0].split("&endt=")[0];

        if (decryptedUsernameText.equals(usernameInp) && decryptedPasswordText.equals(passwordInp)) {
            System.out.println("\n-----Login Successful!-----");

            System.out.println("Encrypted Username: " + encryptedUsername[0]);
            System.out.println("Encrypted Password: " + encryptedPassword[0]);
            System.out.println();
            System.out.println("Decrypted Username: " + decryptedUsername[0]);
            System.out.println("Decrypted Password: " + decryptedPassword[0]);
            System.out.println();
            System.out.println("Username Cipher: " + decryptedUsername[1]);
            System.out.println("Password Cipher: " + decryptedPassword[1]);

            return;
        }
        System.out.println("-----Login Failed!-----");
    }

    public String[] encrypt(final String plainText) {
        try {
            final int waitTimeValidRequest = 100;
            long endTimeSeconds = (System.currentTimeMillis() / 1000) + waitTimeValidRequest;
            final String param1 = plainText + "&endt=" + endTimeSeconds;
            final Encrypter encrypter = new Encrypter("DESede", ENCRYPTION_KEY);
            final String encryptedParam1 = encrypter.encrypt(param1);
            // Use a one-way hash, we cannot determine the original message, but
            // we can verify against the original message.
            final String param2hashed = this.makeHash(param1);
            return new String[] {
                    encryptedParam1, param2hashed
            };
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String makeHash(final String param) {
        String hash = "";
        try {
            final String toBeHashed = param + DIGEST_KEY;
            final MessageDigest digest;
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(toBeHashed.getBytes());
            final byte[] encrypted = digest.digest();
            final BASE64Encoder base64encoder = new BASE64Encoder();
            hash = base64encoder.encode(encrypted);
        } catch (final NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return hash;
    }

    public static class Encrypter {
        public static final String ENC_DEFAULT_KEY = "YUNWEUYSKHWKHFABCUEKWYRNUI";
        public static final String DES_ENCRYPTION_SCHEME = "DES";
        public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
        private KeySpec keySpec;
        private SecretKeyFactory keyFactory;
        private Cipher cipher;
        private static final String ENCODING = "UTF8";

        public Encrypter(String encryptionScheme) {
            this(encryptionScheme, ENC_DEFAULT_KEY);
        }

        public Encrypter(String encryptionScheme, String encryptionKey) {
            if (encryptionKey == null)
                throw new IllegalArgumentException("encryption key was invalid");
            try {
                final byte[] keyAsBytes = encryptionKey.getBytes(ENCODING);
                if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) {
                    keySpec = new DESedeKeySpec(keyAsBytes);
                } else if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
                    keySpec = new DESKeySpec(keyAsBytes);
                } else {
                    throw new IllegalArgumentException("Encryption scheme not supported: " + encryptionScheme);
                }
                keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
                cipher = Cipher.getInstance(encryptionScheme);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static String bytes2String(byte[] bytes) {
            final StringBuffer buf = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                buf.append((char) bytes[i]);
            }
            return buf.toString();
        }

        public String[] decrypt(String encstr) {
            if (encstr == null || encstr.trim().length() <= 0) {
                throw new IllegalArgumentException("encrypted string was null or empty");
            }
            try {
                final SecretKey key = keyFactory.generateSecret(keySpec);
                cipher.init(Cipher.DECRYPT_MODE, key);
                BASE64Decoder base64decoder = new BASE64Decoder();
                byte[] plaintxt = base64decoder.decodeBuffer(encstr);
                byte[] ciphertext = cipher.doFinal(plaintxt);

                return new String[]{bytes2String(ciphertext), String.format("%s", ciphertext)};
            } catch (final Exception e) {
                e.printStackTrace();
            }
            return new String[]{};
        }

        public String encrypt(String decrstr) {
            if (decrstr == null || decrstr.trim().length() == 0) {
                throw new IllegalArgumentException("unencrypted string was null or empty");
            }
            try {
                SecretKey key = keyFactory.generateSecret(keySpec);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] cleartext = decrstr.getBytes(ENCODING);
                byte[] ciphertext = cipher.doFinal(cleartext);
                BASE64Encoder base64encoder = new BASE64Encoder();
                return base64encoder.encode(ciphertext);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}