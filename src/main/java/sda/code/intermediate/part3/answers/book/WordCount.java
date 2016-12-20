package sda.code.intermediate.part3.answers.book;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) throws IOException {
		File book = new File("/home/czajka/data/Shakespeare.txt");
		Scanner sc = new Scanner(book);
		sc.useDelimiter("\\W+");

		Map<String, Integer> words = new HashMap<>();

		while (sc.hasNext()) {
			String token = sc.next().toLowerCase();
			Integer count = words.getOrDefault(token, 0);
			count += 1;
			words.put(token, count);
		}

		sc.close();
		words.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue())
				.forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
	}

}
