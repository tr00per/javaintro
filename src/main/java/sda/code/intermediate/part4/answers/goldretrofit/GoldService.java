package sda.code.intermediate.part4.answers.goldretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import sda.code.intermediate.part4.answers.goldmodel.GoldPrice;

public interface GoldService {
	public static int LONGEST_PERIOD_IN_DAYS = 93;

	@GET("cenyzlota/last/{days}")
	@Headers("Accept: application/json")
	Call<List<GoldPrice>> byDays(@Path("days") Integer duration);

	@GET("cenyzlota/{from}/{to}")
	@Headers("Accept: application/json")
	Call<List<GoldPrice>> byDates(@Path("from") String startDate, @Path("to") String endDate);

}
