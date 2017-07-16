package com.zhangtao.encrypts;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by zhangtao on 2017/7/11.
 */
public class AES3 {
    public static String aesDecrypt(final String encrypted,String keystr,String ivstr) {
        try {
            SecretKey key = new SecretKeySpec(keystr.getBytes("UTF-8"), "AES");
//            SecretKey key = new SecretKeySpec(Base64.decodeBase64(keystr), "AES");
            AlgorithmParameterSpec iv = new IvParameterSpec(ivstr.getBytes("UTF-8"));
//            AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(ivstr));
            byte[] decodeBase64 = Base64.decodeBase64(encrypted);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            return new String(cipher.doFinal(decodeBase64), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("This should not happen in production.", e);
        }
    }
    public static String aesEncrypt(final String encrypted,String keystr,String ivstr) {
        try {
            SecretKey key = new SecretKeySpec(keystr.getBytes("UTF-8"), "AES");
//            SecretKey key = new SecretKeySpec(Base64.decodeBase64(keystr), "AES");
            AlgorithmParameterSpec iv = new IvParameterSpec(ivstr.getBytes("UTF-8"));
//            AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(ivstr));
            byte[] decodeBase64 = encrypted.getBytes("UTF-8");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            return new String(Base64.encodeBase64(cipher.doFinal(decodeBase64)), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("This should not happen in production.", e);
        }
    }
}
