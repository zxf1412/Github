package com.zxf.demo.signatureDSA;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;

public class SignatureDSA_demo {
	
	private static String src = "dsa demo";
	
	public static void main(String[] args) {
		jdkDSA();
	}
	
	private static void jdkDSA(){
		try {
			//1.初始化秘钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			DSAPublicKey rsaPublicKey = (DSAPublicKey)keyPair.getPublic();
			DSAPrivateKey rsaPrivateKey = (DSAPrivateKey)keyPair.getPrivate();
			
			//执行签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("DSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withDSA");
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			byte[] result = signature.sign();
			System.out.println(Hex.encodeHexString(result));
			
			//验证数字签名
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("DSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("SHA1withDSA");
			signature.initVerify(publicKey);
			signature.update(src.getBytes());
			boolean bool = signature.verify(result);
			System.out.println(bool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};

}
