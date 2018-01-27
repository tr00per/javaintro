package sda.code.intermediate.part4.answers.simplegol;

public class SingleFileGameOfLife {
    public static void main(String[] args) throws InterruptedException {
        // Ustal rozmiary świata gry
        boolean world[][] = new boolean[20][80];
        // Losowanie stanu początkowego, dla każdej komórki
        for (int y = 0; y < world.length; ++y) {
            for (int x = 0; x < world[y].length; ++x) {
                world[y][x] = Math.random() < 0.5;
            }
        }

        long iteration = 0;
        // Pętla świata (nieskończona albo z dużą ilością powtórzeń, >100)
        while (true) {
            // - Wyświetl stan świata na konsoli
            //   (użyj spacji do reprezentowania pustej komórki i '@' albo '#' dla pełnej
            System.out.println("-------------------------------------------------- " + iteration++);
            for (int y = 0; y < world.length; ++y) {
                for (int x = 0; x < world[y].length; ++x) {
                    System.out.print(world[y][x] ? '#' : ' ');
                }
                System.out.println();
            }
            // - Stwórz nowy, pusty stan dla następnego kroku
            boolean next[][] = new boolean[20][80];
            // - Dla każdej komórki z poprzedniego kroku sprawdź sąsiedztwo
            //   (8 sąsiadów, komórki "poza światem" traktuj jako puste)
            for (int y = 0; y < world.length; ++y) {
                for (int x = 0; x < world[y].length; ++x) {
                    int neighbours = 0;
                    for (int dy = -1; dy <= 1; ++dy) {
                        for (int dx = -1; dx <= 1; ++dx) {
                            if ((dx != 0 || dy != 0) && x + dx >= 0 && x + dx < world[y].length && y + dy >= 0 && y + dy < world.length && world[y + dy][x + dx]) {
                                neighbours += 1;
                            }
                        }
                    }
                    // - Na podstawie sąsiedztwa zdecyduj, czy dana lokalizacja w nowym kroku będzie pusta czy pełna
                    //   (klasyczne zasady to: B3/S23)
                    if (neighbours == 3) {
                        next[y][x] = true;
                    } else if (neighbours == 2 && world[y][x]) {
                        next[y][x] = true;
                    } else {
                        next[y][x] = false;
                    }
                }
            }
            // - Ustaw nowy stan świata jako bieżący
            world = next;

            Thread.sleep(500);
        }

        // Co dalej?
        // - Logikę decydującą o nowym stanie wynieś do osobnej funkcji, stwórz drugą metodę,
        //   która będzie implementować inny zestaw reguł (np. B36/S23, B3678/S34678)
        // - Dodawaj więcej abstrakcji... (zobacz aplikację w pakiecie answers, żeby zobaczyć
        //   wersję z już zbyt wielką ilością abstrakcji)
    }
}
