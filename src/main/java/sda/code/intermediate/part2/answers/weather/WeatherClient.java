package sda.code.intermediate.part2.answers.weather;

import java.util.concurrent.CompletableFuture;

public interface WeatherClient {
    CompletableFuture<String> getWeather(String endpoint, String apiKey, Coordinates coords);
}
