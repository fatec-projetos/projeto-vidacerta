package br.com.vidaCerta.model.utils;

import java.security.MessageDigest;

public class Criptografia {

	public static String criptografar (String texto) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return new String(hexCodes(md.digest(texto.getBytes())));
		} catch (Exception e) {
			return null;
		}
	}
	
	private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;
 
        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 2,
                                    hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
	}
}

