package sda.code.intermediate.part2.answers.weather;

import java.math.BigDecimal;

public class Coordinates {
	private final BigDecimal latitude;
	private final BigDecimal longitude;

	public Coordinates(double latitude, double longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}

	public String getLatitude() {
		return latitude.stripTrailingZeros().toPlainString();
	}
	public String getLongitude() {
		return longitude.stripTrailingZeros().toPlainString();
	}
	
}
