package com.kafkaone.FileSourceproducer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class FileSourceGenerator {

	public static void main(String[] args) throws IOException, InterruptedException {
		Path filePath = Paths.get("/usr/local/kafka_2.13-2.6.0/test.txt");
		int i = 1;
		while (true) {
			if (i / 1000 == 0) {
				Thread.sleep(1000);
			}
			Files.write(filePath, getAlphaNumericString(20).getBytes(), StandardOpenOption.APPEND);
		}

	}

	static String getAlphaNumericString(int n) {
		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);
		String randomString = new String(array, Charset.forName("UTF-8"));
		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();
		// remove all spacial char
		String AlphaNumericString = randomString.replaceAll("[^A-Za-z0-9]", "");
		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < AlphaNumericString.length(); k++) {

			if (Character.isLetter(AlphaNumericString.charAt(k)) && (n > 0)
					|| Character.isDigit(AlphaNumericString.charAt(k)) && (n > 0)) {
				r.append(AlphaNumericString.charAt(k));
				n--;
			}
		}
		// return the resultant string
		return r.toString()+"\n";
	}

}
