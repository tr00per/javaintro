package sda.code.intermediate.part4.answers.goldmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.http.ParseException;
import org.junit.Before;
import org.junit.Test;

public class LocalDateSeDesTest {

	private LocalDateSeDes sut;

	@Before
	public void setup() {
		sut = new LocalDateSeDes();
	}

	@Test
	public void shoudBeIdentityFromLocalDate() throws IOException {
		LocalDate localDate = LocalDate.now();

		LocalDate restoredLocalDate = sut.fromJson(sut.toJson(localDate));

		assertEquals(localDate, restoredLocalDate);
	}

	@Test
	public void shoudBeIdentityFromJson() throws IOException {
		String localDate = "\"2017-01-01\"";

		String restoredLocalDate = sut.toJson(sut.fromJson(localDate));

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

	@Test(expected = ParseException.class)
	public void shoudThrowWhenGivenANumber() throws IOException {
		assertNull(sut.fromJson("1"));
	}

	@Test(expected = DateTimeParseException.class)
	public void shoudThrowWhenGivenAMalformedString() throws IOException {
		assertNull(sut.fromJson("\"abc\""));
	}

}
