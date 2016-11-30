package sda.code.intermediate.part1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import sda.code.intermediate.part1.exercises.Algorithms;

@RunWith(JUnitParamsRunner.class)
public class AlgorithmCreateArrayTest {

	Algorithms sut;

	@Before
	public void setUp() throws Exception {
		sut = new Algorithms();
	}

	@Test
	@Parameters({ "0", "1", "10", "1000" })
	public void testCreateRandomArray(int length) {
		int[] array = sut.createRandomArray(length);
		assertEquals(length, array.length);
	}

	@Test(expected = IllegalArgumentException.class)
	public void boundaryTestCreateRandomArray() {
		sut.createRandomArray(-1);
	}
}
