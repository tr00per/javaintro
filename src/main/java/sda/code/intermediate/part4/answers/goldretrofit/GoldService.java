package sda.code.intermediate.part4.answers.goldretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import sda.code.intermediate.part4.answers.goldmodel.GoldPrice;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface GoldService {
    public static int LONGEST_PERIOD_IN_DAYS = 367;
    public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;

    @GET("cenyzlota/last/{last}")
    @Headers("Accept: application/json")
    Call<List<GoldPrice>> byLastQuotes(@Path("last") Integer lastQuotes);

    @GET("cenyzlota/{from}/{to}")
    @Headers("Accept: application/json")
    Call<List<GoldPrice>> byDates(@Path("from") String startDate, @Path("to") String endDate);

}
