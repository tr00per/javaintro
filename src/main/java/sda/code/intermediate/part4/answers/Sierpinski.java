package sda.code.intermediate.part4.answers;

public class Sierpinski {
    private static void carpet(boolean[][] pattern, int size, int x, int y) {
        if (size == 1) {
            pattern[y][x] = true;
            return;
        } else if (size % 3 != 0) {
            throw new IllegalArgumentException("Size is not a multiple of 3: " + size);
        }

        carpet(pattern, size / 3, x + 0 * size / 3, y + 0 * size / 3);
        carpet(pattern, size / 3, x + 1 * size / 3, y + 0 * size / 3);
        carpet(pattern, size / 3, x + 2 * size / 3, y + 0 * size / 3);

        carpet(pattern, size / 3, x + 0 * size / 3, y + 1 * size / 3);
//        carpet(pattern, size / 3, x + 1 * size / 3, y + 1 * size / 3);
        carpet(pattern, size / 3, x + 2 * size / 3, y + 1 * size / 3);

        carpet(pattern, size / 3, x + 0 * size / 3, y + 2 * size / 3);
        carpet(pattern, size / 3, x + 1 * size / 3, y + 2 * size / 3);
        carpet(pattern, size / 3, x + 2 * size / 3, y + 2 * size / 3);
    }

    public static void main(String[] args) {
        boolean[][] świat = new boolean[81][81];
        carpet(świat, 81, 0, 0);
        show(świat);
    }

    private static void show(boolean[][] pattern) {
        for (int y = 0; y < pattern.length; ++y) {
            for (int x = 0; x < pattern[y].length; ++x) {
                System.out.print(pattern[y][x] ? '#' : ' ');
            }
            System.out.println();
        }
    }
}
