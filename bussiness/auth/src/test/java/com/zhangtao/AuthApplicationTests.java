package com.zhangtao;

import com.zhangtao.common.Hex;
import com.zhangtao.util.Snowflake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthApplicationTests {

	@Test
	public void contextLoads() {
		String pwd = "$2a$10$op1pgA2cZTkUiji4aL37OeCaAI1ol8cn.PmjqqPMI5jd0it8IOZmm";

		long b= Snowflake.nextId();
		int k = Math.abs((int) b >> 32) % 108;
		if (k == 0) {
			k = k + 18;
		}

		char[] charArray = pwd.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) (charArray[i] ^ k);
		}
		String sa = UUID.randomUUID().toString().replace("-", "").substring(0,8);

		String s = new String(charArray);
		System.out.println(s);
		String stren =sa+ Hex.encodeHexStr(s);
		System.out.println(stren);


		String s1 = new String(Hex.decodeHex(stren.substring(8)));
		System.out.println(s1);
		char[] charArray1 = s1.toCharArray();
		for (int i = 0; i < charArray1.length; i++) {
			charArray1[i] = (char) (charArray1[i] ^ k);
		}
		String decodeStr = new String(charArray1);
		System.out.println(decodeStr);

		boolean bb = Objects.equals(pwd, decodeStr);
		System.out.println(bb);
	}

}
