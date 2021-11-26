package sda.code.intermediate.part3.answers.rx;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;

import java.util.Map;

public interface OpenWeatherService {

    @GET("data/2.5/weather")
    Observable<WeatherGson> currentWeather(@QueryMap Map<String, String> query);

    @GET("data/2.5/weather")
    Observable<WeatherGson> currentWeather(@Query("appid") String key, @Query("q") String query);

    @GET("data/2.5/weather")
    Observable<WeatherGson> currentWeather(@Query("appid") String key, @Query("lat") String lat, @Query("lon") String lon);

}
