package sda.code.intermediate.part2.livecoding.weather;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class SynchornicznaPobieraczkaPogody implements PobieraczkaPogody {

    private static final String DEFAULT_RESPONSE_ENCODING = "UTF-8";
    private static final int HTTP_OK = 200;

    private static CloseableHttpClient httpclient = HttpClients.createDefault();
    private String units;
    private String language;

    public SynchornicznaPobieraczkaPogody(String units, String language) {
        this.units = units;
        this.language = language;
    }

    @Override
    public Optional<String> wykonajZapytanie(URI uri) {
        URI configuredUri = configureUri(uri);
        if (configuredUri == null) {
            return Optional.empty();
        } else {
            System.out.println(configuredUri.toString());
        }
        HttpGet httpGet = new HttpGet(configuredUri);

        String json = null;
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            System.out.println(response.getStatusLine());
            if (response.getStatusLine().getStatusCode() != HTTP_OK) {
                System.err.println("Niespodziewany kod odpowiedzi: " + response.getStatusLine().getStatusCode());
                return Optional.empty();
            }
            HttpEntity entity = response.getEntity();
            json = EntityUtils.toString(entity, DEFAULT_RESPONSE_ENCODING);
        } catch (IOException e) {
            System.err.println("Błąd odczytu z serwera");
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.ofNullable(json);
    }

    private URI configureUri(URI uri) {
        try {
            return new URIBuilder(uri).setParameter("units", units).setParameter("lang", language).build();
        } catch (URISyntaxException e) {
            System.err.println("Nieprawidłowy format adresu?!");
            e.printStackTrace();
            return null;
        }
    }

}
