package org.kevin;

import java.io.InputStream;

public class Resource {
	public InputStream getResourceByName(String fileName) {
		InputStream inputStream = this.getClass().getResourceAsStream("/resources/" + fileName);
		return inputStream;
	}
}
