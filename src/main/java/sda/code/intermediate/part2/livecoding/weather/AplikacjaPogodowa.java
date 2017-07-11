package sda.code.intermediate.part2.livecoding.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.http.client.utils.URIBuilder;
import sda.code.intermediate.part2.livecoding.weather.api.CityQuery;
import sda.code.intermediate.part2.livecoding.weather.api.Countries;
import sda.code.intermediate.part2.livecoding.weather.api.GeoQuery;
import sda.code.intermediate.part2.livecoding.weather.modelpogody.Pogoda;
import sda.code.intermediate.part2.livecoding.weather.modelpogody.Weather;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Properties;

public class AplikacjaPogodowa {

    private static final String APPID;
    private static final String ADRES_API;
    private static final String KLIENT;

    static {
        Properties props = new Properties();
        try {
            props.load(AplikacjaPogodowa.class.getResourceAsStream("/pogoda.properties"));
        } catch (IOException e) {
            System.err.println("Ups?");
            e.printStackTrace();
        }
        APPID = props.getProperty("pogoda.api.klucz");
        ADRES_API = props.getProperty("pogoda.api.adres");
        KLIENT = props.getProperty("pogoda.api.klient");
    }

    public static void main(String[] args) {
        System.out.println("===================\nAplikacja pogodowa!\n===================");

        PobieraczkaPogody pobieraczka = Pobieraczki.dlaTypu(KLIENT);

        stwórzUri(new CityQuery("Lodz", Countries.POLAND)).flatMap(pobieraczka::wykonajZapytanie)
                .flatMap(AplikacjaPogodowa::deserializacjaPogody).ifPresent(AplikacjaPogodowa::pokażPogodę);

        stwórzUri(new GeoQuery(43.95, 4.81)).flatMap(pobieraczka::wykonajZapytanie)
                .flatMap(AplikacjaPogodowa::deserializacjaPogody).ifPresent(AplikacjaPogodowa::pokażPogodę);
    }

    private static void pokażPogodę(Pogoda pogoda) {
        if (pogoda.getName() != null) {
            System.out.println(pogoda.getName());
        }
        if (pogoda.getWeather() != null) {
            for (Weather weather : pogoda.getWeather()) {
                if (weather != null) {
                    System.out.println(weather.getDescription());
                }
            }
        }
        if (pogoda.getMain() != null) {
            System.out.println(pogoda.getMain().getTemp());
        }
    }

    private static Optional<Pogoda> deserializacjaPogody(String json) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        try {
            return Optional.ofNullable(gson.fromJson(json, Pogoda.class));
        } catch (JsonSyntaxException e) {
            System.err.println("Nie udało się zdeserializować odpowiedzi");
            e.printStackTrace();
            return Optional.empty();

        }
    }

    private static Optional<URI> stwórzUri(CityQuery cityQuery) {
        URI uri = null;
        try {
            uri = new URIBuilder(ADRES_API).setParameter("q", cityQuery.toString()).setParameter("appid", APPID)
                    .build();
        } catch (URISyntaxException e) {
            System.err.println("Nieprawidłowy format adresu");
            e.printStackTrace();
        }
        return Optional.ofNullable(uri);
    }

    private static Optional<URI> stwórzUri(GeoQuery geo) {
        URI uri = null;
        try {
            uri = new URIBuilder(ADRES_API).setParameter("lat", geo.getLatitude())
                    .setParameter("lon", geo.getLongitude()).setParameter("appid", APPID).build();
        } catch (URISyntaxException e) {
            System.err.println("Nieprawidłowy format adresu");
            e.printStackTrace();
        }
        return Optional.ofNullable(uri);
    }
}
