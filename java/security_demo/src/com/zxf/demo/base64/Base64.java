package com.zxf.demo.base64;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {

	/**
	 * @param args
	 */
	private static String src = "zxf base64 demo";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		base64jdk();
		base64bc();
		base64cc();
	}
	
	private static void base64jdk(){
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			String jdkencoder = encoder.encode(src.getBytes());
			System.out.println("jdkencoder: "+jdkencoder);
			
			BASE64Decoder decoder = new BASE64Decoder();
			System.out.println("jdkdeencoder: "+new String(decoder.decodeBuffer(jdkencoder)));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void base64bc(){
		byte[] encoder = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
		System.out.println("bcencoder: "+new String(encoder));
		byte[] decoder = org.bouncycastle.util.encoders.Base64.decode(encoder);
		System.out.println("bcdecoder: "+new String(decoder));
	}
	
	private static void base64cc(){
		byte[] encoder =org.apache.commons.codec.binary.Base64.encodeBase64(src.getBytes());
		System.out.println("ccencoder: "+new String(encoder));
		byte[] decoder = org.apache.commons.codec.binary.Base64.decodeBase64(encoder);
		System.out.println("ccdecoder: "+new String(decoder));
	}

}
