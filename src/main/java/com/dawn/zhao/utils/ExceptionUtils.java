package com.dawn.zhao.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExceptionUtils {
	
	public static String printDetailException(Exception e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		e.printStackTrace(new PrintStream(baos));  
		String exception = baos.toString();  
		return exception;
	}
	
	public static void main(String[] args) {
		try {
			int i= 1/0;
		} catch (Exception e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			e.printStackTrace(new PrintStream(baos));  
			String exception = baos.toString();  
			System.out.println("baos:" + exception); 
		}
	}
	
}
