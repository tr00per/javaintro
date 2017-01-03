package sda.code.intermediate.part2.answers.weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Sources: https://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html,
 * https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/index.html
 */
public class SynchronousHttpClient implements WeatherClient {
	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	public CompletableFuture<String> getWeather(String endpoint, String apiKey, Coordinates coords) {
		HttpGet httpGet = null;
		try {
			URIBuilder builder = new URIBuilder(endpoint).addParameter("appid", apiKey)
					.addParameter("lat", coords.getLatitude()).addParameter("lon", coords.getLongitude())
					.addParameter("units", "metric").addParameter("lang", "pl");
			httpGet = new HttpGet(builder.build());
		} catch (URISyntaxException e) {
			CompletableFuture<String> failure = new CompletableFuture<>();
			failure.completeExceptionally(e);
			return failure;
		}

		try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();

			final String contents = EntityUtils.toString(entity, "UTF-8");
			return CompletableFuture.completedFuture(contents);
		} catch (IOException e) {
			CompletableFuture<String> failure = new CompletableFuture<>();
			failure.completeExceptionally(e);
			return failure;
		}
	}

}
