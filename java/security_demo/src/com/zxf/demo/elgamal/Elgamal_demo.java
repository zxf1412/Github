package com.zxf.demo.elgamal;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Elgamal_demo {

	/**
	 * @param args
	 */
	private static String src = "elgamal demo";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bcElgamal();
	}

	private static void bcElgamal(){
		//公钥加密，私钥解密
		Security.addProvider(new BouncyCastleProvider());
		
		try {
			AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("Elgamal");
			algorithmParameterGenerator.init(256);
			AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
			DHParameterSpec dhParameterSpec = (DHParameterSpec)algorithmParameters.getParameterSpec(DHParameterSpec.class);
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Elgamal");
			keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PublicKey elgamalPublicKey = keyPair.getPublic();
			PrivateKey elgamalPrivateKey = keyPair.getPrivate();
			
			//公钥加密，私钥解密-加密
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(elgamalPublicKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("Elgamal");
		    PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		    Cipher cipher = Cipher.getInstance("Elgamal");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("公钥加密，私钥解密-加密:" + Base64.encodeBase64String(result));
			
			//公钥加密，私钥解密-解密
			PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(elgamalPrivateKey.getEncoded());
		    keyFactory = KeyFactory.getInstance("Elgamal");
		    PrivateKey privateKey = keyFactory.generatePrivate(encodedKeySpec);
			cipher = Cipher.getInstance("Elgamal");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			result = cipher.doFinal(result);
			System.out.println("公钥加密，私钥解密-解密:" + new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
