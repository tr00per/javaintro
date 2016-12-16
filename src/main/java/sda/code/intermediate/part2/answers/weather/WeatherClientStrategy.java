package sda.code.intermediate.part2.answers.weather;

public class WeatherClientStrategy {
	public static WeatherClient getClient(String kind) {
		switch (kind.toLowerCase()) {
		case "sync":
			return new SynchronousHttpClient();
		case "async":
			return new AsynchronousHttpClient();
		}
		throw new IllegalArgumentException("Unknown client implementation");
	}
}
