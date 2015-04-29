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
		//��ʼ����
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = secureRandom.generateSeed(8);
		
		//���������
		String password = "pde demo";
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			Key key = factory.generateSecret(keySpec);
			
			//����
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key,parameterSpec);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println(Base64.encodeBase64String(result));
			
			//����
			cipher.init(Cipher.DECRYPT_MODE, key,parameterSpec);
			result = cipher.doFinal(result);
			System.out.println(new String(result));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
