package com.zxf.demo.SHA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class SHA_demo {

	/**
	 * @param args
	 */
	
	private static String src = "zxf SHA demo";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdkSHA();
	}
	
	private static void jdkSHA(){
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA");
			byte[] shaBytes = digest.digest(src.getBytes());
			System.out.println(Hex.encodeHexString(shaBytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
