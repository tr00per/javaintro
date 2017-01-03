package sda.code.intermediate.part2.answers.weather;

import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;

/**
 * Sources: https://github.com/AsyncHttpClient/async-http-client
 */
public class AsynchronousHttpClient implements WeatherClient {
	private static final int HTTP_OK = 200;
	private static AsyncHttpClient client = new DefaultAsyncHttpClient();

	public CompletableFuture<String> getWeather(String endpoint, String apiKey, Coordinates coords) {
		final RequestBuilder request = new RequestBuilder("GET").setUrl(endpoint).addQueryParam("appid", apiKey)
				.addQueryParam("lat", coords.getLatitude()).addQueryParam("lon", coords.getLongitude())
				.addQueryParam("units", "metric").addQueryParam("lang", "pl");
		// System.out.println(request.build().toString());
		return client.executeRequest(request).toCompletableFuture()
				.thenComposeAsync(AsynchronousHttpClient::filterHttpOk)
				.thenApplyAsync(response -> response.getResponseBody(Charset.forName("UTF-8")));
	}

	private static CompletableFuture<Response> filterHttpOk(Response response) {
		System.out.println(response.getStatusCode());
		if (response.getStatusCode() != HTTP_OK) {
			CompletableFuture<Response> failure = new CompletableFuture<>();
			failure.completeExceptionally(new WrongResponseCode());
			return failure;
		} else {
			return CompletableFuture.completedFuture(response);
		}
	}
}
