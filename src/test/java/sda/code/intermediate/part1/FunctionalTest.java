package sda.code.intermediate.part1;

import org.junit.Before;
import org.junit.Test;
import sda.code.intermediate.part1.exercises.Functional;
import sda.code.intermediate.part1.exercises.data.Item;
import sda.code.intermediate.part1.exercises.data.Product;
import sda.code.intermediate.part1.exercises.data.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FunctionalTest {

    private Functional sut;
    private List<Integer> list;
    private List<Product> products;

    @Before
    public void setUp() throws Exception {
        sut = new Functional();
        list = Arrays.asList(4, 6, 11, 1, 8, 0, -5, 4, -1);
        products = Arrays.asList(
                new Item("TV", new BigDecimal("500"), 10.0),
                new Service("Movie stream", new BigDecimal("10"), 2)
        );
    }

    @Test
    public void testSquares() {
        List<Integer> out = sut.squares(list);
        assertEquals(Arrays.asList(16, 36, 121, 1, 64, 0, 25, 16, 1), out);
    }

    @Test
    public void testEven() {
        List<Integer> out = sut.even(list);
        assertEquals(Arrays.asList(4, 6, 8, 0, 4), out);
    }

    @Test
    public void testCountOdd() {
        long out = sut.countOdd(list);
        assertEquals(4L, out);
    }

    @Test
    public void testSmallest() {
        int out = sut.smallest(list);
        assertEquals(-5, out);
    }

    @Test
    public void testBruttoSum() {
        BigDecimal sum = sut.cartBruttoSum(products);
        assertEquals(new BigDecimal("720.00"), sum);
    }

}
