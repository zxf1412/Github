package com.zxf.demo.des;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Des_demo {

	/**
	 * @param args
	 */
	
	private static String src = "zxfDES";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkDES();
		bcDES();
	}
	
	public static void jdkDES(){
		try {
			//生成KEY	
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			
			keyGenerator.init(56);
			SecretKey secretKey =keyGenerator.generateKey();
			
			byte[] bytesKey = secretKey.getEncoded();
			//转换KEY
			
			DESKeySpec keySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key key = factory.generateSecret(keySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
	
	public static void bcDES(){
		try {
			Security.addProvider(new BouncyCastleProvider());
			
			
			//生成KEY	
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
			
			keyGenerator.getProvider();
			keyGenerator.init(56);
			SecretKey secretKey =keyGenerator.generateKey();
			
			byte[] bytesKey = secretKey.getEncoded();
			//转换KEY
			
			DESKeySpec keySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key key = factory.generateSecret(keySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("BC des encrypt: " + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println("BC des decrypt: "+new String(result));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
