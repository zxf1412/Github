package com.zxf.demo.pbe;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.asn1.pkcs.PBEParameter;


public class PBE_deom {

	/**
	 * @param args
	 * 
	 * 
	 */
	private static String src = "zxfPBE";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkPBE();
	}
	
	private static void jdkPBE(){
		//初始化盐
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = secureRandom.generateSeed(8);
		
		//口令与加密
		String password = "pde demo";
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			Key key = factory.generateSecret(keySpec);
			
			//加密
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key,parameterSpec);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println(Base64.encodeBase64String(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, key,parameterSpec);
			result = cipher.doFinal(result);
			System.out.println(new String(result));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
