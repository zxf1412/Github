package com.zxf.demo.hmac;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

public class Hmac_demo {

	/**
	 * @param args
	 */
	
	private static String src = "zxf demo mac";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 jdkhmac();
		 bchmac();
	}
	
	private static void jdkhmac(){
			try {
				KeyGenerator generator = KeyGenerator.getInstance("HmacMD5");//��ʼ��KeyGenerator
				SecretKey secretKey = generator.generateKey();//������Կ
				byte[] keybytes = secretKey.getEncoded();//�����Կ
				keybytes = Hex.decodeHex(new char[]{'a','a','a','a'});//�Զ�����Կ
				
				SecretKey restorekey = new SecretKeySpec(keybytes, "HmacMD5");//��ԭ��Կ
				Mac mac = Mac.getInstance(restorekey.getAlgorithm());//ʵ��Hmac
				mac.init(restorekey);//��ʼ��mac
				byte[] hmacMD5Bytes = mac.doFinal(src.getBytes());//ִ��ժҪ
				System.out.println("hmacMD5Bytes:" + Hex.encodeHexString(hmacMD5Bytes));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private static void bchmac(){
		try {
		  HMac hMac = new HMac(new MD5Digest());
		  hMac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaa")));
		  hMac.update(src.getBytes(),0,src.getBytes().length);
		  byte[] hmacMD5Bytes = new byte[hMac.getMacSize()];//ִ��ժҪ
		  hMac.doFinal(hmacMD5Bytes, 0);
		  System.out.println("bc:"+org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes));
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
	
	
}

}
