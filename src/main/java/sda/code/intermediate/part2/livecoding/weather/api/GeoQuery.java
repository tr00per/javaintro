package sda.code.intermediate.part2.livecoding.weather.api;

import java.math.BigDecimal;
import java.math.MathContext;

public class GeoQuery {

	private final BigDecimal latitude;
	private final BigDecimal longitude;

	public GeoQuery(double latitude, double longitude) {
		this.latitude = new BigDecimal(latitude, MathContext.DECIMAL64);
		this.longitude = new BigDecimal(longitude, MathContext.DECIMAL64);
	}

	public String getLatitude() {
		return latitude.stripTrailingZeros().toPlainString();
	}

	public String getLongitude() {
		return longitude.stripTrailingZeros().toPlainString();
	}
}
