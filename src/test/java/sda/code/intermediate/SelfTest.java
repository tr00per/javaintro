package sda.code.intermediate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Properties;

import org.junit.Test;

public class SelfTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void simpleFileUtilsTest() {
		Properties props = new FileUtils().loadDefaultProperties();
		assertTrue(Boolean.parseBoolean(props.getProperty("success")));
		assertEquals(42, Integer.parseInt(props.getProperty("answer")));
		assertEquals("World", props.getProperty("hello.msg"));
		assertEquals("?", props.getProperty("nonexistent", "?"));
	}

	@Test
	public void simpleSortingUtilsTest() {
		int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 9, 0 };
		Arrays.sort(array);
		assertTrue(new SortingUtils().isSorted(array));
	}
}
