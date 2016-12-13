
package sda.code.intermediate.part2.answers.json.gson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class WeatherGson {

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("weather")
    @Expose
    private List<Weather_> weather = null;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    /**
     * 
     * @return
     *     The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * 
     * @param coord
     *     The coord
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     * 
     * @return
     *     The sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * 
     * @param sys
     *     The sys
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     * 
     * @return
     *     The weather
     */
    public List<Weather_> getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(List<Weather_> weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The main
     */
    public Main getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * 
     * @return
     *     The rain
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * 
     * @param rain
     *     The rain
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * 
     * @return
     *     The clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * 
     * @param clouds
     *     The clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * 
     * @return
     *     The dt
     */
    public Integer getDt() {
        return dt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The cod
     */
    public Integer getCod() {
        return cod;
    }

    /**
     * 
     * @param cod
     *     The cod
     */
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(coord).append(sys).append(weather).append(main).append(wind).append(rain).append(clouds).append(dt).append(id).append(name).append(cod).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WeatherGson) == false) {
            return false;
        }
        WeatherGson rhs = ((WeatherGson) other);
        return new EqualsBuilder().append(coord, rhs.coord).append(sys, rhs.sys).append(weather, rhs.weather).append(main, rhs.main).append(wind, rhs.wind).append(rain, rhs.rain).append(clouds, rhs.clouds).append(dt, rhs.dt).append(id, rhs.id).append(name, rhs.name).append(cod, rhs.cod).isEquals();
    }

}
