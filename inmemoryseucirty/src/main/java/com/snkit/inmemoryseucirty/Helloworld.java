package com.snkit.inmemoryseucirty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Helloworld {

	public static void main(String[] args) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		
		System.out.println(encode.encode("demo"));
		
		String str = encode.encode("demo");
		
		
		
		System.out.println(encode.matches("demo", str));
		

	}

}
