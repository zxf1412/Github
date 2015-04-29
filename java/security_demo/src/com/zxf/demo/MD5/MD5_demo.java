package com.zxf.demo.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5_demo {

	/**
	 * @param args
	 */
	
	private static String src = "zxf MD5 demo";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkMD5(src);
	}
	
	private static void jdkMD5(String msg){
		try {
			MessageDigest MD = MessageDigest.getInstance("MD5");
			byte[] mdBytes = MD.digest(msg.getBytes());
			System.out.println("jdk MD5: "+Hex.encodeHexString(mdBytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
