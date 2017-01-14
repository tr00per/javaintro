package sda.code.intermediate.part4.answers.goldretrofit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

public class SummaryWriterTest {

	@Test
	public void shouldConvertDates() {
		LocalDate nowLD = LocalDate.now();
		Date nowD = SummaryWriter.toDate(nowLD);

		LocalDate restoredLD = toLocalDate(nowD);

		assertEquals(nowLD, restoredLD);
	}

	private LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}
