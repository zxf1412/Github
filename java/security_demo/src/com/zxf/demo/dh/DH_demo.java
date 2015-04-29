package com.zxf.demo.dh;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;



public class DH_demo {

	/**
	 * @param args
	 */
	
	private static String src= "zxf dh test";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkDH();
	}
	
	private static void jdkDH(){
		try {
			//发送方初始化秘钥
			KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
			senderKeyPairGenerator.initialize(512);
			KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
			byte[] senderPublicEnc = senderKeyPair.getPublic().getEncoded();//发送方公钥，发送给接收方
			
			//接收方初始化秘钥
			KeyFactory recieverKeyFactory = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicEnc);
			PublicKey recieverPublicKey = recieverKeyFactory.generatePublic(x509EncodedKeySpec);
			DHParameterSpec dhParameterSpec = ((DHPublicKey)recieverPublicKey).getParams();
			KeyPairGenerator recieverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
			recieverKeyPairGenerator.initialize(dhParameterSpec);
			KeyPair recieverKeyPair = recieverKeyPairGenerator.generateKeyPair();
			PrivateKey recieverPrivateKey = recieverKeyPair.getPrivate();
			byte[] recieverPublicEnc = recieverKeyPair.getPublic().getEncoded();
			
			//秘钥构建
			KeyAgreement recieverKeyAgreement = KeyAgreement.getInstance("DH");
			recieverKeyAgreement.init(recieverPrivateKey);
			recieverKeyAgreement.doPhase(recieverPublicKey, true);
			SecretKey reciverDESKey = recieverKeyAgreement.generateSecret("DES");
			
			KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
			x509EncodedKeySpec = new X509EncodedKeySpec(recieverPublicEnc);
			PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
			KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
			senderKeyAgreement.init(senderKeyPair.getPrivate());
			senderKeyAgreement.doPhase(senderPublicKey, true);
			SecretKey senderDESKey = senderKeyAgreement.generateSecret("DES");
			
			if(Objects.equals(reciverDESKey, senderDESKey)){
				
				System.out.println("双方秘钥完成");
			}
			
			//加密
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, senderDESKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println(Base64.encodeBase64String(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, reciverDESKey);
			result = cipher.doFinal(result);
			System.out.println(new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
