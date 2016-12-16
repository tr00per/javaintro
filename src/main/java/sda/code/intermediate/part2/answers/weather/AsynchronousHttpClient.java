package sda.code.intermediate.part2.answers.weather;

import java.util.Optional;

public class AsynchronousHttpClient implements WeatherClient {

	public Optional<String> getWeather(String endpoint, String apiKey, Coordinates coords) {
		return Optional.empty();
	}

}
