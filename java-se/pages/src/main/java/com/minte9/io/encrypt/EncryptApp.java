/**
 * Encrypt AES simple
 * 
 * Advanced Encryption Standard which is a symmetric encryption algorithm.
 * 
 * ECB (Electronic Codebook) is essentially the first generation of the AES.
 * It is the most basic form of block cipher encryption.
 */
package com.minte9.io.encrypt;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptApp {
    public static void main(String[] args) 
            throws Exception {

        String key = "YXZyYHNaWA=";
        String plainText = "mypassword";

        String encrypted = AES.encrypt(plainText, key);
        String decrypted = AES.decrypt(encrypted, key);

        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}

class AES {

    public static String encrypt(String plainText, String key) 
            throws Exception  {

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey(key));

            return Base64.getEncoder().encodeToString(
                    cipher.doFinal(plainText.getBytes("UTF-8")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encryptedText, String key) 
            throws Exception  {
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, getKey(key));

        return new String(cipher.doFinal(
            Base64.getDecoder().decode(encryptedText)
        ));
    }

    public static SecretKeySpec getKey(String myKey) 
            throws Exception {

        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        byte[] key;

        key = myKey.getBytes("UTF-8");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);

        return new SecretKeySpec(key, "AES");
    }
}