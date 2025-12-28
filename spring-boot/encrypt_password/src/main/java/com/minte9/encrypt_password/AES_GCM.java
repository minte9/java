/**
 * AES GCM cipher
 * 
 * GCM is proven secure in the concrete security model.
 * The ECB can leak information about the plaintext.
 */

package com.minte9.encrypt_password;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_GCM {

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