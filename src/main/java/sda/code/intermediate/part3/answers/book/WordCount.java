package sda.code.intermediate.part3.answers.book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
//import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) throws IOException {
		// File book = new File("/home/czajka/data/Shakespeare.txt");
		File book = Paths.get(System.getProperty("user.home"), "data", "Shakespeare.txt").toFile();

		Map<String, Integer> words = new HashMap<>();

		try (Scanner sc = new Scanner(book)) {
			sc.useDelimiter("\\W+");

			gatherStats(sc, words);
		}

//		List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
//		list.sort((e1, e2) -> e2.getValue() - e1.getValue());
//		list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
//		for (Map.Entry<String, Integer> e : list.subList(0, 20)) {
//			System.out.println(e.getKey() + " = " + e.getValue());
//		}

//		words.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).limit(20)
//				.forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));

		words.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).limit(20)
				.forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
	}

	private static void gatherStats(Scanner sc, Map<String, Integer> words) {
		while (sc.hasNext()) {
			String token = sc.next().toLowerCase();
			Integer count = words.getOrDefault(token, 0);
			count += 1;
			words.put(token, count);
		}
	}

}
