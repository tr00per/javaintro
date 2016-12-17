package sda.code.intermediate.part2.answers.weather;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.RequestBuilder;

public class AsynchronousHttpClient implements WeatherClient {
	private static AsyncHttpClient client = new DefaultAsyncHttpClient();

	public Optional<String> getWeather(String endpoint, String apiKey, Coordinates coords) {
		return getAsyncWeather(endpoint, apiKey, coords).handle(AsynchronousHttpClient::toOptional).join();
	}

	private static Optional<String> toOptional(String contents, Throwable ex) {
		if (ex != null) {
			ex.printStackTrace();
		}
		return ex == null ? Optional.ofNullable(contents) : Optional.empty();
	}

	public CompletableFuture<String> getAsyncWeather(String endpoint, String apiKey, Coordinates coords) {
		final RequestBuilder request = new RequestBuilder("GET").setUrl(endpoint).addQueryParam("appid", apiKey)
				.addQueryParam("lat", coords.getLatitude()).addQueryParam("lon", coords.getLongitude())
				.addQueryParam("units", "metric").addQueryParam("lang", "pl");
		System.out.println(request.build().toString());
		return client.executeRequest(request).toCompletableFuture().thenApplyAsync(response -> {
			System.out.println(response.getStatusCode());
			return response.getResponseBody(Charset.forName("UTF-8"));
		});
	}
}
