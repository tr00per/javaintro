package sda.code.intermediate.part3.answers.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sda.code.intermediate.part1.answers.patterns.Settings;
import sda.code.intermediate.part2.answers.json.gson.WeatherDetails;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;
import sda.code.intermediate.part2.livecoding.weather.api.CityQuery;
import sda.code.intermediate.part2.livecoding.weather.api.Countries;
import sda.code.intermediate.part2.livecoding.weather.api.GeoQuery;
import sda.code.intermediate.part3.RichPrint;

import java.io.IOException;

public class Reactive {

    public static void main(String[] args) throws IOException {
        final String baseUrl = Settings.CONFIG.getString("weather.baseurl");
        final String key = Settings.CONFIG.getString("weather.apikey");
        final OpenWeatherService service = createWeatherService(baseUrl);

        CurrentWeather weather = new CurrentWeather(service, key);
        weather.byCity(new CityQuery("Lodz", Countries.POLAND))
//				.observeOn(Schedulers.io())
//				.subscribeOn(Schedulers.computation())
                .subscribe(new WeatherObserver());
        weather.byGeoQuery(new GeoQuery(43.95, 4.81))
//				.observeOn(Schedulers.io())
//				.subscribeOn(Schedulers.computation())
                .subscribe(new WeatherObserver());

//		System.in.read();
    }

    private static OpenWeatherService createWeatherService(String endpoint) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(endpoint).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();

        return retrofit.create(OpenWeatherService.class);
    }

    private static class WeatherObserver implements Observer<WeatherGson> {
        @Override
        public void onComplete() {
            RichPrint.println("Completed!");
        }

        @Override
        public void onError(Throwable e) {
            RichPrint.println("Error!");
            RichPrint.println(e.getMessage());
        }

        @Override
        public void onNext(WeatherGson weather) {
            RichPrint.println("Next!");

            if (weather.getName() != null) {
                System.out.println(weather.getName());
            }
            if (weather.getWeather() != null) {
                for (WeatherDetails details : weather.getWeather()) {
                    if (details != null) {
                        System.out.println(details.getDescription());
                    }
                }
            }
            if (weather.getMain() != null) {
                System.out.println(weather.getMain().getTemp());
            }
        }

        @Override
        public void onSubscribe(@NonNull Disposable d) {
        }
    }

}
