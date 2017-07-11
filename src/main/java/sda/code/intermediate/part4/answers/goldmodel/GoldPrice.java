package sda.code.intermediate.part4.answers.goldmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class GoldPrice {

    @SerializedName("data")
    @Expose
    @JsonAdapter(LocalDateSeDes.class)
    private LocalDate date;

    @SerializedName("cena")
    @Expose
    private Double price;

    public GoldPrice() {
    }

    public GoldPrice(LocalDate date, Double price) {
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(date).append(price).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GoldPrice) == false) {
            return false;
        }
        GoldPrice rhs = ((GoldPrice) other);
        return new EqualsBuilder().append(date, rhs.date).append(price, rhs.price).isEquals();
    }

}
