package org.kevin.start;

import org.mindrot.jbcrypt.BCrypt;

public class Test {
	public static void main(String[] args) {
		// 这是加密方式
		String hashed = BCrypt.hashpw("admin", BCrypt.gensalt());
		System.out.println(hashed);

		// 这是解密方式
		if (BCrypt.checkpw("nimda", hashed))
			System.out.println("It matches");
		else
			System.out.println("It does not match");
	}
}
