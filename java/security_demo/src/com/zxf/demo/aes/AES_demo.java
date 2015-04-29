package com.zxf.demo.aes;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sun.crypto.provider.AESKeyGenerator;

public class AES_demo {

	/**
	 * @param args
	 */
	private static String src = "zxf demo aes";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 jdkAES();
		 bcAES();
	}
	
	private static void jdkAES(){
		try {
			//����key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteskey = secretKey.getEncoded();
			
			//ת��key
			Key key = new SecretKeySpec(byteskey,"AES");
			
			//����
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println(Hex.encodeHexString(result));
			
			//����
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println(new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void bcAES(){
		try {
			Security.addProvider(new BouncyCastleProvider());
			
			//����key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES","BC");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] byteskey = secretKey.getEncoded();
			
			//ת��key
			Key key = new SecretKeySpec(byteskey,"AES");
			
			//����
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println(Hex.encodeHexString(result));
			
			//����
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println(new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
