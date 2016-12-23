package sda.code.intermediate.part2.answers.weather;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;

/**
 * Sources:
 */
public class AsynchronousHttpClient implements WeatherClient {
	private static final int HTTP_OK = 200;
	private static AsyncHttpClient client = new DefaultAsyncHttpClient();

	public Optional<String> getWeather(String endpoint, String apiKey, Coordinates coords) {
		return getAsyncWeather(endpoint, apiKey, coords).handle(AsynchronousHttpClient::toOptional).join();
	}

	private static Optional<String> toOptional(String contents, Throwable ex) {
		if (ex != null) {
			// To nie jest prawdziwa obsługa wyjątku, tylko mała pomoc przy
			// szybkim ustaleniu, co dzieje się w naszym krótkim programie.
			System.out.println("There was an exception");
			ex.printStackTrace();
			System.out.println("End of stack trace");
		}
		return ex == null ? Optional.ofNullable(contents) : Optional.empty();
	}

	public CompletableFuture<String> getAsyncWeather(String endpoint, String apiKey, Coordinates coords) {
		final RequestBuilder request = new RequestBuilder("GET").setUrl(endpoint).addQueryParam("appid", apiKey)
				.addQueryParam("lat", coords.getLatitude()).addQueryParam("lon", coords.getLongitude())
				.addQueryParam("units", "metric").addQueryParam("lang", "pl");
		System.out.println(request.build().toString());
		return client.executeRequest(request).toCompletableFuture()
				.thenComposeAsync(AsynchronousHttpClient::filterHttpOk)
				.thenApplyAsync(response -> response.getResponseBody(Charset.forName("UTF-8")));
	}

	private static CompletableFuture<Response> filterHttpOk(Response response) {
		System.out.println(response.getStatusCode());
		if (response.getStatusCode() != HTTP_OK) {
			throw new WrongResponseCode();
		} else {
			return CompletableFuture.completedFuture(response);
		}
	}

	public static class WrongResponseCode extends RuntimeException {
		private static final long serialVersionUID = -4888076696766369663L;
	}
}
