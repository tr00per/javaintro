package sda.code.intermediate.part4.answers.goldmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class LocalDateSeDesTest {

	private LocalDateSeDes sut;

	@Before
	public void setup() {
		sut = new LocalDateSeDes();
	}

	@Test
	public void shoudBeIdentity() throws IOException {
		LocalDate localDate = LocalDate.now();

		LocalDate restoredLocalDate = sut.fromJson(sut.toJson(localDate));

		assertEquals(localDate, restoredLocalDate);
	}

	@Test
	public void shoudHandleNullsWhenWriting() throws IOException {
		assertEquals("null", sut.toJson(null));
	}

	@Test
	public void shoudHandleNullsWhenReading() throws IOException {
		assertNull(sut.fromJson("null"));
	}

}
