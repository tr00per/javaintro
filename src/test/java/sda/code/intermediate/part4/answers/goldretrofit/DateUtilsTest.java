package sda.code.intermediate.part4.answers.goldretrofit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void shouldBeIdentityFromLocalDate() {
		LocalDate nowLD = LocalDate.now();
		Date nowD = DateUtils.toDate(nowLD);

		LocalDate restoredLD = DateUtils.toLocalDate(nowD);

		assertEquals(nowLD, restoredLD);
	}

}
