package sda.code.intermediate.part3.exercises.rx;

import java.io.IOException;

import sda.code.intermediate.part1.answers.patterns.Settings;
import sda.code.intermediate.part2.livecoding.weather.api.CityQuery;
import sda.code.intermediate.part2.livecoding.weather.api.Countries;
import sda.code.intermediate.part2.livecoding.weather.api.GeoQuery;

public class Reactive {

	public static void main(String[] args) throws IOException {
		final String baseUrl = Settings.CONFIG.getString("weather.baseurl");
		final String key = Settings.CONFIG.getString("weather.apikey");

		final CityQuery cityQuery = new CityQuery("Lodz", Countries.POLAND);
		final GeoQuery geoQuery = new GeoQuery(43.95, 4.81);

		// 1. Użyć Retrofita i Gsona, by pobrać prognozę synchronicznie
		// (retrofit, gson, converter-gson)
		// 2. Użyć Retrofita, by wykonać asynchroniczne zapytanie
		// 3. Włączyć integrację z RxJavą (rxjava, adapter-rxjava)
		// 4. Użyć Schedulera RxJavy, by wykonać asynchroniczne zapytanie
	}

	// Przydatnik do wyświetlenia komunikatu razem z nazwą wątku
	public static void println(Object obj) {
		if (obj == null) {
			println("null");
		} else {
			println(obj.toString());
		}
	}

	private static void println(String msg) {
		System.err.println(Thread.currentThread().getName() + ": " + msg);
	}
}
