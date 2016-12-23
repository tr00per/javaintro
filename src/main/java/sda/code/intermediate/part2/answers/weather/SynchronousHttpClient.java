package sda.code.intermediate.part2.answers.weather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

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

	public Optional<String> getWeather(String endpoint, String apiKey, Coordinates coords) {
		HttpGet httpGet = null;
		try {
			URIBuilder builder = new URIBuilder(endpoint).addParameter("appid", apiKey)
					.addParameter("lat", coords.getLatitude()).addParameter("lon", coords.getLongitude())
					.addParameter("units", "metric").addParameter("lang", "pl");
			httpGet = new HttpGet(builder.build());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			return Optional.empty();
		}

		try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();

			final String contents = EntityUtils.toString(entity, "UTF-8");
			return Optional.ofNullable(contents);
		} catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
