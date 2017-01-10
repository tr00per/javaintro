package sda.code.intermediate.part2.answers.weather;

import java.util.concurrent.CompletableFuture;

import com.google.gson.Gson;

import sda.code.intermediate.part1.answers.patterns.Settings;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;

public class WeatherApp {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("The Weather App");

		final String endpoint = Settings.CONFIG.getString("weather.endpoint");
		final String apiKey = Settings.CONFIG.getString("weather.apikey");

		final Coordinates coords = new Coordinates(51.783333, 19.466667);

		WeatherClient client = WeatherClientStrategy.getClient("sync");

		for (int i = 0; i < 5; ++i) {
			CompletableFuture<String> forecast = client.getWeather(endpoint, apiKey, coords);
			forecast.whenComplete(WeatherApp::convertAndDisplay);
		}
		System.err.println("Finished!");
	}

	private static void convertAndDisplay(String forecast, Throwable ex) {
		if (ex != null) {
			System.err.println("Downloading weather failed!");
			ex.printStackTrace();
			return;
		}

		WeatherGson weather = new Gson().fromJson(forecast, WeatherGson.class);
		System.out.println(weather.getName());
		System.out.println(weather.getMain().getTemp());
		System.out.println(weather.getWeather().get(0).getDescription());
	}
}
