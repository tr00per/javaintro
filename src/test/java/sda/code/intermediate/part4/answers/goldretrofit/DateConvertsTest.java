package sda.code.intermediate.part4.answers.goldretrofit;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateConvertsTest {

    @Test
    public void shouldBeIdentityFromLocalDate() {
        LocalDate nowLD = LocalDate.now();
        Date nowD = DateConverts.toDate(nowLD);

        LocalDate restoredLD = DateConverts.toLocalDate(nowD);

        assertEquals(nowLD, restoredLD);
    }

}
