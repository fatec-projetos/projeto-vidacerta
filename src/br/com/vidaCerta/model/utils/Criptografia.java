package br.com.vidaCerta.model.utils;

import java.security.MessageDigest;

public class Criptografia {

	public static String criptografar (String texto) {
		try {
			byte[] bytesNoTexto = texto.getBytes("UTF-8");
	
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] criptografia = md.digest(bytesNoTexto);
			
			return criptografia.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
}
