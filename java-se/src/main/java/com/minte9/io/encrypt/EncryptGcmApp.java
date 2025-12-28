/**
 * Encrypt AES GCM
 * 
 * GCM is proven secure in the concrete security model.
 * The ECB can leak information about the plaintext.
 * 
 * Don’t reuse IV with the same key!
 * 
 * Generate a new one every single time you encrypt ...
 * and just store it alongside the ciphertext, not with the key.
 */
package com.minte9.io.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptGcmApp {
    public static void main(String[] args) throws Exception {

        String plainText = "mypassword";
        String key = "AO5uMsQyKeVfwkVF5L6n0SObW80g5JVYUcRv7WAYVow=";
        String iv = "DnGotRRpb6xlzeu5"; 
            
            // key = AES_GCM.createKey(256);
            // String iv = AES_GCM.createIv();
            // Move key and iv to env variables!
            

        String encrypted = AES_GCM.encrypt(plainText, key, iv);
        String decrypted = AES_GCM.decrypt(encrypted, key, iv);

        System.out.println(key);
        System.out.println(iv);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
}

class AES_GCM {

    public static String encrypt(String plainText, String key, String ivStr)
            throws Exception {
        
        byte[] iv = Base64.getDecoder().decode(ivStr);
        SecretKey secretKey = AES_GCM.getSecretKey(key);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(
            Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(128, iv)
        );

        return Base64.getEncoder().encodeToString(
            cipher.doFinal(plainText.getBytes("UTF-8"))
        );
    }

    public static String decrypt(String encryptedText, String key, String ivStr)
            throws Exception {
        
        byte[] iv = Base64.getDecoder().decode(ivStr);
        SecretKey secretKey = AES_GCM.getSecretKey(key);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(
            Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(128, iv)
        );

        return new String(cipher.doFinal(
            Base64.getDecoder().decode(encryptedText)
        ));
    }

    public static String createKey(int size)
            throws NoSuchAlgorithmException {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static String createIv() {
        byte[] iv = new byte[12];
        new SecureRandom().nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv);
    }

    public static SecretKey getSecretKey(String key) {
        byte[] decoded = Base64.getDecoder().decode(key);
        return new SecretKeySpec(decoded, 0, decoded.length, "AES");
    }

}