package sda.code.intermediate.part3.answers.rx;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.schedulers.Schedulers;
import sda.code.intermediate.part1.answers.patterns.Settings;
import sda.code.intermediate.part2.answers.json.gson.WeatherDetails;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;
import sda.code.intermediate.part2.livecoding.weather.api.CityQuery;
import sda.code.intermediate.part2.livecoding.weather.api.Countries;
import sda.code.intermediate.part2.livecoding.weather.api.GeoQuery;
import sda.code.intermediate.part3.ThreadUtils;

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
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

		return retrofit.create(OpenWeatherService.class);
	}

	private static class WeatherObserver implements Observer<WeatherGson> {
		@Override
		public void onCompleted() {
			ThreadUtils.println("Completed!");
		}

		@Override
		public void onError(Throwable e) {
			ThreadUtils.println("Error!");
			ThreadUtils.println(e.getMessage());
		}

		@Override
		public void onNext(WeatherGson weather) {
			ThreadUtils.println("Next!");

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
	}

}
