package com.zhangtao;

import com.alibaba.fastjson.JSON;
import com.zhangtao.encrypts.EncryHelper;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptApplicationTests {

    @Test
    public void te(){
        String s= EncryHelper.enHEX("aaa");
        System.out.println(s);
        String s1= EncryHelper.deHEX(s);
        System.out.println(s1);
    }
//	String filepath = "/Users/zhangtao";
//	String publickeypath =filepath+ "/publicKey.keystore";
//	String privatekeypath =filepath+ "/privateKey.keystore";
//	@Test
//	public void contextLoads() throws Exception {
//		String KEY = "abcdefgabcdefg12";
//		User a=new User();
//		a.account="zhangsan";
//		a.password=KEY;
//		String s= JSON.toJSONString(a);
//		System.out.println("明文：" + s);
//		byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(publickeypath)), s.getBytes());
//		String cipher = Base64Ex.encode(cipherData);
//		System.out.println("公钥加密：" + cipher);
//		String encodeStr = Hex.encodeHexStr(cipher.getBytes());
//		System.out.println("HEX码：" + encodeStr);
//
//		String decodeStr = new String(Hex.decodeHex(encodeStr.toCharArray()));
//		System.out.println("RSA密文：" + cipher);
//		byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(privatekeypath)), Base64Ex.decode(cipher));
//		String restr = new String(res);
//		System.out.println("私钥解密：" + restr);
//
//		User t= JSON.toJavaObject(JSON.parseObject(restr),User.class);
//		System.out.println(t.account+","+t.password);
//	}
//	@Test
//	public void JSAES() throws Exception {
//		String KEY = "abcdefgabcdefg12";
//		String encrypt = "56393636664d3669624846544b4f594539542f4731746d354a374e784b7a6168724d614b69654a707161526e6a6875482f34545645454f4e73474132332b4d4c31766676627535383642796945465a65574c6b65555a78345943542b6643754269503750594d327a7041413d";    String miwen="26+Xk0ArvN0AcbUE18kjl7lk+H8WYivSWCh1SnMfkNHg/udWPLaH7mxLt7egWoJsn1hBH1lmoag2UXPp2QTSwaiQ9azZRuYvVWzb6QAAkQX/7ktN7E7zUqb6fOfv3vtqwDbXvgVSTOGbHIl6pAzVcw==";
//		User a=new User();
//		a.account="zhangsan";
//		a.password="{;[/.,有汉字怎?么样98553";
//		String s= JSON.toJSONString(a);
//		String javaencry= AES2.aesEncrypt(s,KEY);
//		System.out.println("java加密：" + javaencry);
//		String encodeStr = Hex.encodeHexStr(javaencry.getBytes());
//		System.out.println("java生成16进制字符串：" + encodeStr);
//
//		String decodeStr = new String(Hex.decodeHex(encodeStr.toCharArray()));
//		System.out.println("java16进制字符串转字符：" + decodeStr);
//
//		String decrypt = AES2.aesDecrypt(decodeStr, KEY);
//		System.out.println("解密后：" + decrypt);
//
//		User t= JSON.toJavaObject(JSON.parseObject(decrypt),User.class);
//		System.out.println(t.account+","+t.password);
//
//	}
//
//
//	@Test
//	public void genKeyPair() {
//
//		RSAEncrypt.genKeyPair(filepath);
//	}
//
//	@Test
//	public void SHA512() {
//		String s = SHAEncrypt.SHA512("1234");
//		System.out.println(s);
//	}
//
//	@Test
//	public void RSA() throws Exception {
//
//		System.out.println("--------------公钥加密私钥解密过程-------------------");
//		String s = SHAEncrypt.SHA512("1234567890Abcdefg" + UUID.randomUUID().toString().replaceAll("-", ""));
//		for (int i = 0; i < 10; i++) {
//			s = SHAEncrypt.SHA512(s);
//		}
//		System.out.println(s);
//
////        String plainText="ihep_公钥加密私钥解密ihep_公钥加密私钥解密ihep_公钥加密私钥解密ihep_公钥加密私钥解密";
//		String plainText = UUID.randomUUID().toString().replaceAll("-", "");
//		//公钥加密过程
//		byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), plainText.getBytes());
////        String cipher="i13gNM3Cp5YJx5h6gjRW1cqXRvCBLqbna3Zv6iAvVg8S2ra0Xx2QjSP5cpMB6aH3QfISimZxi+8NKM7nNJa9CGVQpiTyGp4kQZMkijYqDQD4lHjcmUSaS6fxQQc02zeGTVbb882kmC2YXbk8WUlMFvaJwogmBePbjjmf/MfOXg4=";
//		String cipher = Base64Ex.encode(cipherData);
//
//		System.out.println(cipher);
//		String s1 = RSAEncrypt.byteArrayToString(cipherData);
//		System.out.println(s1);
//		String s2 = RSAEncrypt.StringToHex(s1);
//		System.out.println(s2);
//
//		//私钥解密过程
//		byte[] res = RSAEncrypt.decrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), Base64Ex.decode(cipher));
//		String restr = new String(res);
//		System.out.println("原文：" + plainText);
//		System.out.println("加密：" + cipher);
//		System.out.println("解密：" + restr);
//		System.out.println();
//
//		System.out.println("--------------私钥加密公钥解密过程-------------------");
//		plainText = "ihep_私钥加密公钥解密";
//		//私钥加密过程
//		cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(RSAEncrypt.loadPrivateKeyByFile(filepath)), plainText.getBytes());
//		cipher = Base64Ex.encode(cipherData);
//		//公钥解密过程
//		res = RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), Base64Ex.decode(cipher));
//		restr = new String(res);
//		System.out.println("原文：" + plainText);
//		System.out.println("加密：" + cipher);
//		System.out.println("解密：" + restr);
//		System.out.println();
//
//		System.out.println("---------------私钥签名过程------------------");
//		String content = "ihep_这是用于签名的原始数据";
//		String signstr = RSASignature.sign(content, RSAEncrypt.loadPrivateKeyByFile(filepath));
//		System.out.println("签名原串：" + content);
//		System.out.println("签名串：" + signstr);
//		System.out.println();
//
//		System.out.println("---------------公钥校验签名------------------");
//		System.out.println("签名原串：" + content);
//		System.out.println("签名串：" + signstr);
//
//		System.out.println("验签结果：" + RSASignature.doCheck(content, signstr, RSAEncrypt.loadPublicKeyByFile(filepath)));
//		System.out.println();
//	}
//	@Test
//	public void Testjiami()throws Exception{
//		//公钥加密过程
//		String plainText="205CC782C3593D05";
//		byte[] cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPublicKeyByStr(RSAEncrypt.loadPublicKeyByFile(filepath)), plainText.getBytes());
////        String cipher="i13gNM3Cp5YJx5h6gjRW1cqXRvCBLqbna3Zv6iAvVg8S2ra0Xx2QjSP5cpMB6aH3QfISimZxi+8NKM7nNJa9CGVQpiTyGp4kQZMkijYqDQD4lHjcmUSaS6fxQQc02zeGTVbb882kmC2YXbk8WUlMFvaJwogmBePbjjmf/MfOXg4=";
//		String cipher = Base64Ex.encode(cipherData);
//
//		System.out.println(cipher);
//	}
//
//	public String pwd="23446";
//	@Test
//	public void Encrypts() {
//		String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt(5));
//		System.out.println(hashed);
//		if (BCrypt.checkpw(pwd, "$2a$05$eQRb0JjbxCv2g6FsmyiBn.fDANC9qpASO3c7Z5LGh0CDCIttkOUXC"))
//			System.out.println("It matches");
//		else
//			System.out.println("It does not match");
//	}
//	@Test
//	public void hmac() throws Exception {
////        String s=UUID.randomUUID().toString();
////        System.out.println(s);
//
//		String s64= Base64.encodeBase64String(HmacSign.calculateRFC2104HMAC("mypassword", "6236bc2d-5705-4dc5-a4c6-977e41e3195b"));
//		System.out.println(s64);
//
////        String encodeStr = Hex.encodeHexStr(s64.getBytes());
////        System.out.println(encodeStr);
//	}

}
