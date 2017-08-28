package sda.code.intermediate.part4.exercises.gameoflife;

public class GameOfLife {
    public static void main(String[] args) {
        // Ustal rozmiary świata gry
        // Losowanie stanu początkowego, dla każdej komórki
        // Pętla świata (nieskończona albo z dużą ilością powtórzeń, >100)
        // - Wyświetl stan świata na konsoli
        //   (użyj spacji do reprezentowania pustej komórki i '@' albo '#' dla pełnej
        // - Stwórz nowy, pusty stan dla następnego kroku
        // - Dla każdej komórki z poprzedniego kroku sprawdź sąsiedztwo
        //   (8 sąsiadów, komórki "poza światem" traktuj jako puste)
        // - Na podstawie sąsiedztwa zdecyduj, czy dana lokalizacja w nowym kroku będzie pusta czy pełna
        //   (klasyczne zasady to: B3/S23)
        // - Ustaw nowy stan świata jako bieżący

        // Co dalej?
        // - Logikę decydującą o nowym stanie wynieś do osobnej funkcji, stwórz drugą metodę,
        //   która będzie implementować inny zestaw reguł (np. B36/S23, B3678/S34678)
        // - Dodawaj więcej abstrakcji... (zobacz aplikację w pakiecie answers, żeby zobaczyć
        //   wersję z już zbyt wielką ilością abstrakcji)
    }
}
