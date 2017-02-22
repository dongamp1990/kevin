package org.kevin;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;

import com.sun.corba.se.impl.ior.ByteBuffer;

public class Test {
	public static void main(String[] args) {
		method2();
	}
	 
	public static void method1() {
		InputStream input = null;
		OutputStream out= null; 
		try {
			input = new Resource().getResourceByName("build.sbt");
			int c = 0;
			out = new FileOutputStream("build.sbt2");
			while ((c = input.read()) != -1) {
				out.write(c);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public static void method2() {
		InputStream input = null;
		OutputStream out= null;
		try {
			input = new Resource().getResourceByName("build.sbt");
			byte[] bytes = new byte[100];			
			out = new FileOutputStream("build.sbt4");
			int c = 0;
			while ((c = input.read(bytes, 0, 100)) != -1) {
				out.write(bytes, 0, c);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
