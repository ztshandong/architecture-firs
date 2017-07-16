package com.zhangtao.encrypts;

import com.zhangtao.common.Hex;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

/**
 * Created by zhangtao on 2017/7/6.
 */
public class EncryHelper {

    public static String Bcrypts(String pwd, int i) {
        String s = BCrypt.hashpw(pwd, BCrypt.gensalt(i));
        return s;
    }

    public static boolean BcryptCheck(String pwd, String hashed) {
        return BCrypt.checkpw(pwd, hashed);
    }

    public static String HMAC(String data, String key, String hmac) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        if (hmac == null || hmac.equals("")) hmac = "HMACSHA512";
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), hmac);
        Mac mac = Mac.getInstance(hmac);
        mac.init(signingKey);
        return Base64.encodeBase64String(mac.doFinal(data.getBytes()));
    }

    public static String RSApubE(String text) throws Exception {
        byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(RSAKeyPath.PUBLICKEY_KEYSTORE)), text.getBytes());
        String cipher = Base64Ex.encode(cipherData);
        return cipher;
    }

    public static String RSApriD(String text) throws Exception {
        byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(RSAKeyPath.PRIVATEKEY_KEYSTORE)), Base64Ex.decode(text));
        String restr = new String(res);
        return restr;
    }

    public static String RSApriE(String text) throws Exception {
        byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(RSAKeyPath.PRIVATEKEY_KEYSTORE)), text.getBytes());
        String cipher = Base64Ex.encode(cipherData);
        return cipher;
    }

    public static String RSApubD(String text) throws Exception {
        byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(RSAKeyPath.PUBLICKEY_KEYSTORE)), Base64Ex.decode(text));
        String restr = new String(res);
        return restr;
    }

    public static String RSApriS(String text) throws Exception {
        String signstr = RSASignature.sign(text, RSAEncrypt.loadPrivateKeyByFile(RSAKeyPath.PRIVATEKEY_KEYSTORE));
        return signstr;
    }

    public static boolean RSApubS(String text, String signstr) throws Exception {
        return RSASignature.doCheck(text, signstr, RSAEncrypt.loadPublicKeyByFile(RSAKeyPath.PUBLICKEY_KEYSTORE));
    }

    public static String AESE(String text, String KEY) throws Exception {
        String s = AES2.aesEncrypt(text, KEY);
        return s;
    }

    public static String AESD(String text, String KEY) throws Exception {
        String s = AES2.aesDecrypt(text, KEY);
        return s;
    }

    public static String AESD(String text, String KEY, String IV) throws Exception {
        String s = AES3.aesDecrypt(text, KEY, IV);
        return s;
    }
    public static String AESE(String text, String KEY, String IV) throws Exception {
        String s = AES3.aesEncrypt(text, KEY, IV);
        return s;
    }
    public static String SHA512(String text) {
        return SHAEncrypt.SHA512(text);
    }

    public static String enHEX(String text) {
        String encodeStr = Hex.encodeHexStr(text);
        return encodeStr;
    }

    public static String deHEX(String text) {
        String decodeStr = new String(Hex.decodeHex(text));
        return decodeStr;
    }
}
