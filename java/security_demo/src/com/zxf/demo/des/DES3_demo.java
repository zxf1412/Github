package com.zxf.demo.des;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;

public class DES3_demo {
	
	private static String src = "zxfDES";
	
	public static void main(String[] args) {
		jdk3DES();
	}
	
	public static void jdk3DES(){
		
		try {
			//生成KEY	
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			
			//keyGenerator.init(168);
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey =keyGenerator.generateKey();
			
			byte[] bytesKey = secretKey.getEncoded();
			//转换KEY
			
			DESedeKeySpec keySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			Key key = factory.generateSecret(keySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk des encrypt: " + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println("jdk des decrypt: "+new String(result));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
