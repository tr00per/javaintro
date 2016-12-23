package sda.code.intermediate.part3.answers.book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Program zlicza wystąpienia słów w zadanej ksiżące i wyświetla 20
 * najpopularniejszych słów razem z przypisanymi wartościami.
 */
public class WordCount {

	public static void main(String[] args) throws IOException {
		// Ustal ścieżkę do pliku
		File book = Paths.get(System.getProperty("user.home"), "data", "Shakespeare.txt").toFile();

		// Podstawową strukturą będzie mapa String na Integer. W szczególności
		// HashMap oferuje przez większość czasu stały czas dostępu do
		// elementów.
		Map<String, Integer> words = new HashMap<>();

		// Używamy Scannera do przeglądania zawartości pliku, ponieważ zajmie
		// się za nas dzieleniem pliku na pojedyncze słowa.
		// Scanner reprezentuje zasób, który trzeba zamknąć po zakończeniu
		// używania, można to zrobić umieszczając odczytywanie z niego w bloku
		// try i wywołanie .close() na nim w bloku finally. Od Javy 7 lepiej
		// użyć try-with-resource, które zajmie się zamknięciem zasobu za nas.
		try (Scanner sc = new Scanner(book)) {
			// Plik będzie podzielony po znakach nie należących do zbioru
			// [_a-zA-Z0-9] (\W), przynajmniej jedno wystąpienie (+)
			sc.useDelimiter("\\W+");

			gatherStats(sc, words);
		}

		// Poniżej mamy 4 sposoby wykonania tej samej cznności, czyli
		// wyświetlenia 20 najpopularniejszych słów w dokumencie.

		{
			// Styl imperatywny z własną funkcją porównującą
			// Przepisujemy zbiór wartości z mapy do listy, bo ani mapy, ani
			// zbioru,
			// nie można posortować według swoch kryteriów.
			List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
			list.sort((e1, e2) -> e2.getValue() - e1.getValue());
			for (Map.Entry<String, Integer> e : list.subList(0, 20)) {
				System.out.println(e.getKey() + " = " + e.getValue());
			}
		}

		// Części imperatywne zamknięte są w swoich pod-blokach, żeby ograniczyć
		// zasięg widoczności zmiennych tymczasowych.
		{
			// Styl imperatywny z z użyciem Comparatora dostępnego dla wpisów
			// mapy
			List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
			list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
			for (Map.Entry<String, Integer> e : list.subList(0, 20)) {
				System.out.println(e.getKey() + " = " + e.getValue());
			}
		}

		// Styl funkcyjny z własną funkcją porównującą
		words.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).limit(20)
				.forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));

		// Styl funkcyjny z użyciem Comparatora dostępnego dla wpisów mapy
		words.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).limit(20)
				.forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
	}

	private static void gatherStats(Iterator<String> sc, Map<String, Integer> words) {
		// Żeby użyć iteratora każde wywołanie .next() trzeba poprzedzić
		// wywołaniem .hasNext()
		while (sc.hasNext()) {
			// Musi być tylko jedno wywołanie .next() na przebieg pętli. .next()
			// przesuwa iterator.
			// Dokładamy .toLowerCase(), żeby wszystkie warianty słów się
			// połączyły
			String token = sc.next().toLowerCase();
			// Pobieramy bieżącą wartość przypisaną słowu. Jeśli w mapie nie
			// będzie już wartośći dla danego słowa, to zwracamy 0
			Integer count = words.getOrDefault(token, 0);
			// Zwiększamy ilość wystąpień o 1
			count += 1;
			// Umieszczamy nową wartość w skojarzeniu z danym słowem. Stara
			// wartość, jeśli istniała, jest zastępowana.
			words.put(token, count);
		}
	}

}
