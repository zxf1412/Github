package com.zxf.demo.rsa;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;

public class RSA_demo {

	/**
	 * @param args
	 */
	
	private static String src = "zxf demo rsa";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkRSA();
	}

	private static void jdkRSA(){
		
		try {
			//初始化秘钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
			
			//私钥加密，公钥解密-加密
			PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(encodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("私钥加密，公钥解密-加密:" + Base64.encodeBase64String(result));
			
			//私钥加密，公钥解密-解密
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			result = cipher.doFinal(result);
			System.out.println("私钥加密，公钥解密-解密:" + new String(result));
			
			//公钥加密，私钥解密-加密
			x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
		    publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			result = cipher.doFinal(src.getBytes());
			System.out.println("公钥加密，私钥解密-加密:" + Base64.encodeBase64String(result));
			
			//公钥加密，私钥解密-解密
		    encodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
		    keyFactory = KeyFactory.getInstance("RSA");
		    privateKey = keyFactory.generatePrivate(encodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			result = cipher.doFinal(result);
			System.out.println("公钥加密，私钥解密-解密:" + new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
