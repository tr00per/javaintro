package sda.code.intermediate.part2.answers.weather;

import java.util.Optional;

public interface WeatherClient {
	Optional<String> getWeather(String endpoint, String apiKey, Coordinates coords);
}
