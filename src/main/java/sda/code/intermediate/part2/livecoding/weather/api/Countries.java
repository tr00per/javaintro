package sda.code.intermediate.part2.livecoding.weather.api;

public enum Countries {
	POLAND("PL");

	private final String countryCode;

	private Countries(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}
}
