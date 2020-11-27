package br.com.rti.rifareal.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Crypt {

	public static String encrypt( String rawPassword ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode( rawPassword );

		System.out.println( encodedPassword );
		return encodedPassword;
	}

}
