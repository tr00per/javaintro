package sda.code.intermediate.part2.answers.weather;

import java.util.Optional;

import com.google.gson.Gson;

import sda.code.intermediate.part1.answers.patterns.Settings;
import sda.code.intermediate.part2.answers.json.gson.WeatherGson;

public class WeatherApp {

	public static void main(String[] args) {
		System.out.println("The Weather App");

		final String endpoint = Settings.CONFIG.getString("weather.endpoint");
		final String apiKey = Settings.CONFIG.getString("weather.apikey");

		final Coordinates coords = new Coordinates(51.783333, 19.466667);

		WeatherClient client = WeatherClientStrategy.getClient("sync");

		Optional<String> forecast = client.getWeather(endpoint, apiKey, coords);

		forecast.ifPresent(WeatherApp::convertAndDisplay);
	}

	private static void convertAndDisplay(String forecast) {
		WeatherGson weather = new Gson().fromJson(forecast, WeatherGson.class);
		System.out.println(weather.getName());
		System.out.println(weather.getMain().getTemp());
		System.out.println(weather.getWeather().get(0).getDescription());
	}
}
