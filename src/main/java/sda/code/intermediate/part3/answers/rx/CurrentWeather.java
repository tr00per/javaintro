package sda.code.intermediate.part3.answers.rx;

import rx.Observable;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;
import sda.code.intermediate.part2.livecoding.weather.api.CityQuery;
import sda.code.intermediate.part2.livecoding.weather.api.GeoQuery;

import java.util.HashMap;
import java.util.Map;

public class CurrentWeather {
    private OpenWeatherService service;
    private String key;

    public CurrentWeather(OpenWeatherService service, String key) {
        this.service = service;
        this.key = key;
    }

    public Observable<WeatherGson> byCity(CityQuery cityQuery) {
        final Map<String, String> params = new HashMap<>();
        params.put("appid", key);
        params.put("q", cityQuery.toString());
        return service.currentWeather(params);
        // return service.currentWeather(key, cityQuery.toString());
    }

    public Observable<WeatherGson> byGeoQuery(GeoQuery geoQuery) {
        final Map<String, String> params = new HashMap<>();
        params.put("appid", key);
        params.put("lat", geoQuery.getLatitude());
        params.put("lon", geoQuery.getLongitude());
        return service.currentWeather(params);
        // return service.currentWeather(key, geoQuery.getLatitude(),
        // geoQuery.getLongitude());
    }

}
