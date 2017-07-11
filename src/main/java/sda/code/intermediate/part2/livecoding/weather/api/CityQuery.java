package sda.code.intermediate.part2.livecoding.weather.api;

public class CityQuery {

    private final String city;
    private final Countries countryCode;

    public CityQuery(String city) {
        this.city = city;
        this.countryCode = null;
    }

    public CityQuery(String city, Countries countryCode) {
        this.city = city;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(city);

        if (countryCode != null) {
            builder.append(',').append(countryCode.getCountryCode());
        }

        return builder.toString();
    }
}
